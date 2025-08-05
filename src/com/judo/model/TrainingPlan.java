package com.judo.model;

public enum TrainingPlan {

    BEGINNER("Beginner", 25.00),
    INTERMEDIATE("Intermediate", 30.00),
    ELITE("Elite", 35.00);

    private final String name;
    private final double weeklyFee;

    TrainingPlan(String name, double weeklyFee) {
        this.name = name;
        this.weeklyFee = weeklyFee;
    }

    public String getName() {
        return name;
    }

    public double getWeeklyFee() {
        return weeklyFee;
    }
}
