import com.judo.controller.AthleteCostCalculator;
import com.judo.controller.Calculator;
import com.judo.model.Athlete;
import com.judo.model.TrainingPlan;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static ArrayList<Athlete> athletes = new ArrayList<>();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int menuChoice = 0;
        do {

            displayMenu();
            try {
                menuChoice = input.nextInt();

            } catch (InputMismatchException e) {

            }
            input.nextLine();

            switch (menuChoice) {
                case 1:
                    addAthlete();
                    break;

                case 2:
                    if (athletes.isEmpty()) {
                        System.out.println("No athletes registered yet. Please add an athlete first.");
                        break;
                    }
                    System.out.print("Enter athlete's name to search: ");
                    String name = input.nextLine();
                    int index = searchAthlete(name);
                    if (index >= 0) {
                        displayDetail(athletes.get(index));
                    } else {
                        System.out.println("There is no athlete with the name: " + name);
                    }
                    break;

                case 3:
                    if (athletes.isEmpty()) {
                        System.out.println("No athletes registered yet. Please add an athlete first.");
                        break;
                    }
                    displayTotalCostForAllAthlete();
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Invalid input! Please try again.");
            }

        } while (menuChoice != 4);

    }

    public static void displayMenu() {
        System.out.println("\n--- Judo Training Cost Calculator ---");
        System.out.println("1. Add New Athlete");
        System.out.println("2. Search Athlete");
        System.out.println("3. Calculate Cost for All Athletes");
        System.out.println("4. Exit Program");
        System.out.print("Enter your choice: ");
    }

    public static void addAthlete() {

        Scanner input = new Scanner(System.in);

        Athlete athlete = new Athlete();

        System.out.print("Enter athlete's name: ");
        athlete.setName(input.nextLine());

        while (true) {
            System.out.print("Enter training plan (Beginner, Intermediate, Elite): ");
            String selectedPlan = input.nextLine().trim().toUpperCase();
            try {
                athlete.setTrainingPlan(TrainingPlan.valueOf(selectedPlan));
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid training plan. Please choose from Beginner, Intermediate, or Elite.");
            }
        }

        System.out.print("Enter current weight in kg: ");
        athlete.setWeight(input.nextDouble());

        athlete.setWeightCategory();

        if (athlete.getTrainingPlan() == TrainingPlan.INTERMEDIATE || athlete.getTrainingPlan() == TrainingPlan.ELITE) {
            System.out.print("Enter number of competitions entered this month: ");
            athlete.setNoOfCompetitions(input.nextInt());
        } else {
            System.out.println("Beginner athletes are not eligible for competitions.");
        }

        System.out.print("Enter number of private coaching hours per week (max 5): ");
        int privateHours = input.nextInt();
        if (privateHours > 5) {
            System.out.println("The max hours of the private coaching is 5. So, the private coaching hours will be set as 5.");
        }
        athlete.setNoOfPrivateHours(privateHours);

        athletes.add(athlete);

        System.out.println("\n------------------------------------------------------------------------------------");
        System.out.println("Athlete " + athlete.getName() + " is added successfully.");
        System.out.println("------------------------------------------------------------------------------------");

        displayDetail(athlete);

    }

    public static void displayDetail(Athlete athlete) {
        Calculator calculator = new AthleteCostCalculator();

        System.out.println("\n---------------------------------- Athlete Detail ----------------------------------");

        System.out.printf("%-40s%s%n", "Athlete Name:", athlete.getName());
        System.out.printf("%-40s%s%n", "Training Plan:", athlete.getTrainingPlan().getName());
        System.out.printf("%-40s%.2f kg%n", "Current Weight:", athlete.getWeight());
        System.out.printf("%-40s%s%n", "Weight Category:", athlete.getWeightCategory());
        System.out.printf("%-40s%d%n", "Number of Competitions this Month:", athlete.getNoOfCompetitions());
        System.out.printf("%-40s%d%n", "Number of Private Tuition Hours:", athlete.getNoOfPrivateHours());
        System.out.printf("%-40s$ %.2f%n", "Cost for Training Plan:", calculator.calculatePlanCost(athlete.getTrainingPlan()));
        System.out.printf("%-40s$ %.2f%n", "Competition Entry Fee:", calculator.calculateCompetitionEntryFee(athlete.getNoOfCompetitions()));
        System.out.printf("%-40s$ %.2f%n", "Private Tuition Fee:", calculator.calculatePrivateCost(athlete.getNoOfPrivateHours()));

        System.out.println("------------------------------------------------------------------------------------");

        System.out.printf("%-40s$ %.2f%n", "Total Monthly Cost:", calculator.calculateTotalCostForOneAthlete(athlete));
    }

    public static int searchAthlete(String name) {
        for (int i = 0; i < athletes.size(); i++) {
            if (athletes.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }

        return -1;
    }

    public static void displayTotalCostForAllAthlete() {
        Calculator calculator = new AthleteCostCalculator();
        System.out.println("\n------------------------------------------------------------------------------------");
        System.out.printf("%-40s$ %.2f%n", "Total Monthly Cost for All Athletes:", calculator.calculateTotalCostForAllAthlete(athletes));
        System.out.println("------------------------------------------------------------------------------------");
    }

}
