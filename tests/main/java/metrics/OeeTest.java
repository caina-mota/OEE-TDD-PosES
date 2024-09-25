package main.java.metrics;

import main.java.exceptions.QualityMetricException;
import main.java.exceptions.PerformanceMetricException;
import main.java.exceptions.AvailabilityMetricException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
public class OeeTest {

    private Availability availability;
    private Quality quality;
    private Performance performance;
    private double delta;

    @Before
    public void setup() {
        // Configura o delta para comparação de valores aproximados
        delta = 0.001;

        // Instancia objetos reais de Availability, Quality e Performance
        availability = new Availability(1000, 200); // Tempo planejado de 1000 e tempo de setup de 200
        quality = new Quality(1000, 100); // 1000 peças produzidas e 100 rejeitadas
        performance = new Performance(800, 100); // Tempo teórico de 800 e tempo de paradas de 100
    }

    @Test
    public void shouldCalculateOeeCorrectly() throws AvailabilityMetricException, QualityMetricException, PerformanceMetricException {
        Oee oee = new Oee(availability, quality, performance);

        double expectedAvailability = availability.getAvailability(); // 0.8
        double expectedQuality = quality.getQuality(); // 0.9
        double expectedPerformance = performance.getPerformance(); // 0.875

        double expectedOee = expectedAvailability * expectedQuality * expectedPerformance; // 0.8 * 0.9 * 0.875
        double expectedOeePercentage = expectedOee * 100; // 63.0%

        assertEquals(expectedOee, oee.getOee(), delta);
        assertEquals(expectedOeePercentage, oee.getOeePercentage(), delta);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForNullInputInConstructor() {
        // Verifica se IllegalArgumentException é lançada para Quality nulo
        assertThrows(IllegalArgumentException.class, () -> new Oee(availability, null, performance));
        // Verifica se IllegalArgumentException é lançada para Performance nulo
        assertThrows(IllegalArgumentException.class, () -> new Oee(availability, quality, null));
        // Verifica se IllegalArgumentException é lançada para Availability nulo
        assertThrows(IllegalArgumentException.class, () -> new Oee(null, quality, performance));
    }

    @Test
    public void shouldThrowExceptionForZeroValuesInOeeCalculation() throws AvailabilityMetricException, QualityMetricException, PerformanceMetricException {
        // Criando objetos com valores que resultarão em OEE zero
        Availability zeroAvailability = new Availability(1000, 1000); // Disponibilidade 0
        Quality zeroQuality = new Quality(1000, 1000); // Qualidade 0
        Performance zeroPerformance = new Performance(1000, 1000); // Performance 0

        Oee oee = new Oee(zeroAvailability, zeroQuality, zeroPerformance);

        assertEquals(0.0, oee.calculateOEE(), delta);
    }

    @Test
    public void shouldCalculateOeeWhenReassigned() throws AvailabilityMetricException, QualityMetricException, PerformanceMetricException {
        Oee oee = new Oee(availability, quality, performance);

        double expectedOee = availability.getAvailability() * quality.getQuality() * performance.getPerformance();
        assertEquals(expectedOee, oee.getOee(), delta);

        // Reatribuindo novos objetos e recalculando o OEE
        Availability newAvailability = new Availability(900, 100); // 0.888
        oee.setAvailability(newAvailability);

        double newExpectedOee = newAvailability.getAvailability() * quality.getQuality() * performance.getPerformance();
        assertEquals(newExpectedOee, oee.getOee(), delta);
    }
}