package main.java.metrics;

import main.java.exceptions.AvailabilityMetricException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class AvailabilityTest {
    private double delta;

    @Before
    public void setup() {
        delta = 0.01;
    }

    @Test
    public void with3600PlannedTime600SetupTimeExpect83Point33PercentAvailability() throws AvailabilityMetricException {
        int plannedTime = 3600;  // 1 hora
        int setupTime = 600;     // 10 minutos de setup/perdas
        int productionTime = 3000; // Tempo de produção deve ser 3600 - 600

        Availability availability = new Availability(plannedTime, setupTime);

        double expectedAvailabilityPercent = 83.33;
        double expectedAvailability = 0.8333;

        assertEquals(expectedAvailabilityPercent, availability.getAvailabilityPercentage(), delta);
        assertEquals(expectedAvailability, availability.getAvailability(), delta);
        assertEquals(productionTime, availability.getProductionTime());
    }

    @Test
    public void shouldThrowExceptionForIncorrectSetupTimeCalculation() {
        int plannedTime = 3600;
        int setupTime = 600;
        Availability availability = new Availability(plannedTime, setupTime);

        // Configurando um tempo de produção incorreto
        availability.setProductionTime(3200);

        assertThrows(AvailabilityMetricException.class, availability::calculateAvailability);
    }

    @Test
    public void shouldNotPermitCreationOfAvailabilityObjectWithNegativeOrInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> new Availability(3600, -1));  // setupTime negativo
        assertThrows(IllegalArgumentException.class, () -> new Availability(-3600, 600)); // plannedTime negativo
        assertThrows(IllegalArgumentException.class, () -> new Availability(3600, 4000)); // setupTime maior que o plannedTime
    }

    @Test
    public void shouldThrowExceptionWhenPlannedTimeIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new Availability(0, 0));
    }
}
