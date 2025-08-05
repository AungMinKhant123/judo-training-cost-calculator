package com.judo.controller;

import com.judo.model.Athlete;
import com.judo.model.TrainingPlan;

import java.util.ArrayList;

public interface Calculator {
    double calculatePlanCost(TrainingPlan trainingPlan);
    double calculatePrivateCost(int noOfPrivateHours);
    double calculateCompetitionEntryFee(int noOfCompetitions);
    double calculateTotalCostForOneAthlete(double planCost, double privateCost, double competitionEntryFee);
    double calculateTotalCostForOneAthlete(Athlete athlete);
    double calculateTotalCostForAllAthlete (ArrayList<Athlete> athletes);

}
