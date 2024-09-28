package main.java.metrics;

import main.java.exceptions.QualityMetricException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class QualityTest {
    private double delta;
    @Before
    public void setup() {
        delta = 0.001;
    }

    @Test
    public void with1000ProducedParts300BadExpect70Percent() throws QualityMetricException {
        int producedParts = 1000;
        int goodParts = 700;
        int badParts = 300;
        Quality quality = new Quality(producedParts, badParts);

        double qualityPercentage = 70;
        double qualityValue = 0.7;

        assertEquals(qualityPercentage, quality.getQualityPercentage(), delta);
        assertEquals(qualityValue, quality.getQuality(), delta);
        assertEquals(goodParts, quality.getGoodParts());
    }

    @Test
    public void shouldThrowExceptionForInvalidGoodParts() {
        int producedParts = 1000;
        int badParts = 300;
        Quality quality = new Quality(producedParts, badParts);

        quality.setGoodParts(300);

        assertThrows(QualityMetricException.class, quality::calculateQuality);
    }

    @Test
    public void shouldNotPermitCreationOfQualityObjectWithNegativeInputs() {
        assertThrows(IllegalArgumentException.class, () -> new Quality(1000, -1));
        assertThrows(IllegalArgumentException.class, () -> new Quality(-1, 1000));
        assertThrows(IllegalArgumentException.class, () -> new Quality(100, 1000));
    }

    @Test
    public void shouldThrowExceptionWhenProducedPartsIsZero() {
        assertThrows(IllegalArgumentException.class, () -> new Quality(0, 0));
    }
}
