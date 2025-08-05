package com.judo.controller;

import com.judo.model.Athlete;
import com.judo.model.TrainingPlan;

import java.util.ArrayList;

public class AthleteCostCalculator implements Calculator{

    private final double PRIVATE_TUITION_FEE_PER_HOUR = 9.50;
    private final double ENTRY_FEE_PER_COMPETITION =22.00;
    private final int WEEKS_IN_MONTH = 4;

    @Override
    public double calculatePlanCost(TrainingPlan trainingPlan) {
        return trainingPlan.getWeeklyFee() * WEEKS_IN_MONTH;
    }

    @Override
    public double calculatePrivateCost(int noOfPrivateHours) {
        return noOfPrivateHours * PRIVATE_TUITION_FEE_PER_HOUR * WEEKS_IN_MONTH;
    }

    @Override
    public double calculateCompetitionEntryFee(int noOfCompetitions) {
        return noOfCompetitions * ENTRY_FEE_PER_COMPETITION;
    }

    @Override
    public double calculateTotalCostForOneAthlete(double planCost, double privateCost, double competitionEntryFee) {
        return planCost + privateCost + competitionEntryFee;
    }

    @Override
    public double calculateTotalCostForOneAthlete(Athlete athlete) {
        return calculatePlanCost(athlete.getTrainingPlan()) +
                calculatePrivateCost(athlete.getNoOfPrivateHours()) +
                calculateCompetitionEntryFee(athlete.getNoOfCompetitions());
    }

    @Override
    public double calculateTotalCostForAllAthlete(ArrayList<Athlete> athletes) {
        double totalCostForAllAthletes = 0;
        for (Athlete athlete: athletes) {
            totalCostForAllAthletes += calculateTotalCostForOneAthlete(athlete);
        }
        return totalCostForAllAthletes;
    }


}
