package metrics;

public class Oee {
    public double CalcOEE(double quality, double performance, double availability){

        return quality * performance * availability;
        
    }
}
