package com.example;

import java.sql.Connection;
import java.util.Scanner;

public class FitnessApp {

    public static void main(String[] args) {
        DatabaseService dbService = new DatabaseService("fitness.db");
        Connection connection = dbService.getConnection();

        UserService userService = new UserService(connection);
        TrainingService trainingService = new TrainingService();
        WorkoutService workoutService = new WorkoutService(connection);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите ваш пол (мужчина/женщина):");
        String gender = scanner.nextLine();

        System.out.println("Введите ваш возраст:");
        int age = scanner.nextInt();

        System.out.println("Введите ваш рост (в см):");
        int height = scanner.nextInt();

        System.out.println("Введите ваш вес (в кг):");
        int weight = scanner.nextInt();

        scanner.nextLine(); // Очистка буфера

        System.out.println("Введите ваш тип телосложения (эктоморф, мезоморф, эндоморф):");
        String bodyType = scanner.nextLine();

        System.out.println("Выберите количество тренировок в неделю (3, 4, 5):");
        int workoutsPerWeek = scanner.nextInt();

        int userId = userService.registerUser(gender, age, height, weight, bodyType);
        if (userId != -1) {
            String program = trainingService.getTrainingProgram(bodyType, workoutsPerWeek);
            System.out.println("Ваша программа тренировок:\n" + program);

            System.out.println("Введите результат вашей тренировки (например, '100 кг жим лежа'):");
            scanner.nextLine(); // Очистка буфера
            String workoutResult = scanner.nextLine();

            System.out.println("Результат вашей тренировки записан");


            workoutService.saveWorkoutResult(userId, workoutResult);
        } else {
            System.out.println("Ошибка при регистрации пользователя.");
        }

        scanner.close();
        dbService.closeConnection();
    }
}
