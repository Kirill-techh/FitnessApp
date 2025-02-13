package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private final JTextField genderField;
    private final JTextField ageField;
    private final JTextField heightField;
    private final JTextField weightField;
    private final JTextField bodyTypeField;
    private final JTextField workoutsPerWeekField;
    private final JTextArea programArea;

    private final UserService userService;
    private final TrainingService trainingService;
    private final WorkoutService workoutService;

    public MainFrame() {
        // Инициализация сервисов
        DatabaseService dbService = new DatabaseService("fitness.db");
        userService = new UserService(dbService.getConnection());
        trainingService = new TrainingService();
        workoutService = new WorkoutService(dbService.getConnection());

        // Настройка окна
        setTitle("Фитнес-приложение");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Панель для ввода данных
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(new JLabel("Пол:"));
        genderField = new JTextField();
        inputPanel.add(genderField);

        inputPanel.add(new JLabel("Возраст:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Рост (см):"));
        heightField = new JTextField();
        inputPanel.add(heightField);

        inputPanel.add(new JLabel("Вес (кг):"));
        weightField = new JTextField();
        inputPanel.add(weightField);

        inputPanel.add(new JLabel("Тип телосложения:"));
        bodyTypeField = new JTextField();
        inputPanel.add(bodyTypeField);

        inputPanel.add(new JLabel("Тренировок в неделю:"));
        workoutsPerWeekField = new JTextField();
        inputPanel.add(workoutsPerWeekField);

        add(inputPanel, BorderLayout.NORTH);

        // Кнопка для получения программы тренировок
        JButton getProgramButton = new JButton("Получить программу");
        getProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTrainingProgram();
            }
        });
        add(getProgramButton, BorderLayout.CENTER);

        // Область для отображения программы тренировок
        programArea = new JTextArea();
        programArea.setEditable(false);
        add(new JScrollPane(programArea), BorderLayout.SOUTH);
    }

    private void getTrainingProgram() {
        try {
            String gender = genderField.getText();
            int age = Integer.parseInt(ageField.getText());
            int height = Integer.parseInt(heightField.getText());
            int weight = Integer.parseInt(weightField.getText());
            String bodyType = bodyTypeField.getText();
            int workoutsPerWeek = Integer.parseInt(workoutsPerWeekField.getText());

            // Регистрация пользователя
            int userId = userService.registerUser(gender, age, height, weight, bodyType);

            // Получение программы тренировок
            String program = trainingService.getTrainingProgram(bodyType, workoutsPerWeek);
            programArea.setText(program);

            // Сохранение результата тренировки
            String result = JOptionPane.showInputDialog(this, "Введите результат тренировки:");
            if (result != null && !result.isEmpty()) {
                workoutService.saveWorkoutResult(userId, result);
                JOptionPane.showMessageDialog(this, "Результат тренировки сохранен!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ошибка ввода данных. Проверьте правильность введенных значений.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
