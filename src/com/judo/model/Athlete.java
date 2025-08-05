package com.judo.model;

import java.util.ArrayList;

public class Athlete {

    private String name;
    private TrainingPlan trainingPlan;
    private double weight;
    private String weightCategory;
    private int noOfCompetitions;
    private int noOfPrivateHours;
    private final int MAX_PRIVATE_TUITION_HOURS_PER_WEEK = 5;

    public Athlete() {
    }

    public Athlete(String name, TrainingPlan trainingPlan, double weight, String weightCategory, int noOfCompetitions, int noOfPrivateHours) {
        this.name = name;
        this.trainingPlan = trainingPlan;
        this.weight = weight;
        this.weightCategory = weightCategory;
        this.noOfCompetitions = noOfCompetitions;
        this.noOfPrivateHours = noOfPrivateHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TrainingPlan getTrainingPlan() {
        return trainingPlan;
    }

    public void setTrainingPlan(TrainingPlan trainingPlan) {
        this.trainingPlan = trainingPlan;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getWeightCategory() {
        return weightCategory;
    }

    public void setWeightCategory() {
        if (weight > 100) {
            weightCategory = "Heavyweight";
        } else if (weight > 90) {
            weightCategory = "Light-Heavyweight";
        } else if (weight > 81) {
            weightCategory = "Middleweight";
        } else if (weight > 73) {
            weightCategory = "Light-Middleweight";
        } else if (weight > 66) {
            weightCategory = "Lightweight";
        } else {
            weightCategory = "Flyweight";
        }
    }

    public int getNoOfCompetitions() {
        return noOfCompetitions;
    }

    public void setNoOfCompetitions(int noOfCompetitions) {
        this.noOfCompetitions = noOfCompetitions;
    }

    public int getNoOfPrivateHours() {
        return noOfPrivateHours;
    }

    public void setNoOfPrivateHours(int noOfPrivateHours) {
        this.noOfPrivateHours = Math.min(noOfPrivateHours, MAX_PRIVATE_TUITION_HOURS_PER_WEEK);
    }
}
