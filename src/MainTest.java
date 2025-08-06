import com.judo.controller.AthleteCostCalculator;
import com.judo.model.Athlete;
import com.judo.model.TrainingPlan;

import org.junit.jupiter.api.Test;

import static com.judo.model.TrainingPlan.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final AthleteCostCalculator calculator = new AthleteCostCalculator();

    @Test
    public void testBeginnerCannotEnterCompetitions() {
        Athlete athlete = new Athlete();
        athlete.setTrainingPlan(BEGINNER);
        athlete.setNoOfCompetitions(2); // should be ignored
        assertEquals(0, athlete.getNoOfCompetitions(), "Beginner should not have competitions");
    }

    @Test
    public void testValidIntermediateCostCalculation() {
        Athlete athlete = new Athlete("James", INTERMEDIATE, 88, "", 1, 3);
        double total = calculator.calculateTotalCostForOneAthlete(athlete);
        double expected = 30.0 * 4 + 9 * 3 * 4 + 22.0 * 1;
        assertEquals(expected, total, 0.01);
    }

    @Test
    public void testPrivateHoursAreCapped() {
        Athlete athlete = new Athlete();
        athlete.setNoOfPrivateHours(10); // Max is 5
        assertEquals(5, athlete.getNoOfPrivateHours(), "Private hours should be capped at 5");
    }

    @Test
    public void testWeightCategoryAssignment() {
        Athlete athlete = new Athlete();
        athlete.setWeight(85);
        athlete.setWeightCategory();
        assertEquals("Middleweight", athlete.getWeightCategory());
    }

    @Test
    public void testCompetitionFeeCalculation() {
        double fee = calculator.calculateCompetitionEntryFee(2);
        assertEquals(44.0, fee, 0.001);
    }

    @Test
    public void testPlanCost() {
        double cost = calculator.calculatePlanCost(ELITE);
        assertEquals(35.0 * 4, cost, 0.01);
    }

    @Test
    public void testTotalCostForMultipleAthletes() {
        Athlete a1 = new Athlete("A", BEGINNER, 70, "", 0, 1);
        Athlete a2 = new Athlete("B", INTERMEDIATE, 80, "", 2, 3);
        double total = calculator.calculateTotalCostForAllAthlete(new java.util.ArrayList<>(java.util.Arrays.asList(a1, a2)));

        double expected = calculator.calculateTotalCostForOneAthlete(a1) + calculator.calculateTotalCostForOneAthlete(a2);
        assertEquals(expected, total, 0.01);
    }
}