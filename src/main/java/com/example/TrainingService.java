package com.example;

public class TrainingService {

    public String getTrainingProgram(String bodyType, int workoutsPerWeek) {
        String program = "";

        if (bodyType.equalsIgnoreCase("эктоморф")) {
            program = "Программа для эктоморфа:\n" +
                    "- День 1: Грудь, трицепс\n" +
                    "- День 2: Спина, бицепс\n" +
                    "- День 3: Ноги, плечи\n";
        } else if (bodyType.equalsIgnoreCase("мезоморф")) {
            program = "Программа для мезоморфа:\n" +
                    "- День 1: Грудь, бицепс\n" +
                    "- День 2: Спина, трицепс\n" +
                    "- День 3: Ноги, плечи\n";
        } else if (bodyType.equalsIgnoreCase("эндоморф")) {
            program = "Программа для эндоморфа:\n" +
                    "- День 1: Кардио, грудь\n" +
                    "- День 2: Кардио, спина\n" +
                    "- День 3: Кардио, ноги\n";
        }

        if (workoutsPerWeek == 4) {
            program += "- День 4: Кардио, пресс\n";
        } else if (workoutsPerWeek == 5) {
            program += "- День 4: Кардио, пресс\n- День 5: Функциональная тренировка\n";
        }

        return program;
    }
}