package main.java.metrics;

import main.java.exceptions.PerformanceMetricException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PerformanceTest {
    private double delta;

    @Before
    public void setup() {
        delta = 0.01;
    }

    @Test
    public void with3600TheoricTime600DowntimeExpect83Point33PercentPerformance() {
        int theoreticalTime = 3600;  // Tempo teórico de 1 hora (3600 segundos)
        int productionDowntime = 600;  // Tempo de parada de 10 minutos (600 segundos)
        int realProductionTime = 3000;  // Tempo real de produção esperado: 3600 - 600

        Performance performance = new Performance(theoreticalTime, productionDowntime);

        double expectedPerformancePercent = 83.33;
        double expectedPerformance = 0.8333;

        assertEquals(expectedPerformancePercent, performance.getPerformancePercentage(), delta);
        assertEquals(expectedPerformance, performance.getPerformance(), delta);
        assertEquals(realProductionTime, performance.getRealProductionTime());
    }

    @Test
    public void shouldThrowExceptionForIncorrectRealProductionTimeCalculation() {
        int theoricTime = 3600;
        int productionDowntime = 600;
        Performance performance = new Performance(theoricTime, productionDowntime);

        // Configurando um tempo real de produção incorreto
        performance.setRealProductionTime(3200);  // Deve ser 3000

        assertThrows(PerformanceMetricException.class, performance::calculatePerformance);
    }

    @Test
    public void shouldNotPermitCreationOfPerformanceObjectWithNegativeOrInvalidInputs() {
        // productionDowntime não pode ser maior que theoricTime
        assertThrows(IllegalArgumentException.class, () -> new Performance(3600, -1));  // downtime negativo
        assertThrows(IllegalArgumentException.class, () -> new Performance(-3600, 600));  // tempo teórico negativo
        assertThrows(IllegalArgumentException.class, () -> new Performance(3600, 4000));  // downtime maior que theoricTime
    }

    @Test
    public void shouldThrowExceptionWhenTheoreticalTimeIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new Performance(0, 0));
    }
}
