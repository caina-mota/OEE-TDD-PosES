package main.java.metrics;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OeeTest {
    @Test
    public void oeeCalcTest(){
        Oee oee = new Oee();
        double oeeRes = oee.CalcOEE(0.88, 0.77, 0.92);

        assertEquals(0.623392,oeeRes,0.001);
    }
}