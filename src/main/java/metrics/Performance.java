package main.java.metrics;

public class Performance {
    private double expectedTime;
    private double productionTime;

    // Construtor
    public Performance(double expectedTime, double productionTime) {
        this.expectedTime = expectedTime;
        this.productionTime = productionTime;
    }

    // Getters e Setters
    public double getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(double expectedTime) {
        this.expectedTime = expectedTime;
    }

    public double getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(double productionTime) {
        this.productionTime = productionTime;
    }

    // Cálculo da Performance
    public double calculatePerformance() {
        return (productionTime / expectedTime) * 100;
    }
}
