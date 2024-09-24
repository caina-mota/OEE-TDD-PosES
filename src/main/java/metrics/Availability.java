package main.java.metrics;

public class Availability {
    private double plannedTime;  // tempo de produção
    private double productionTime;  // tempo planejamento

    // Construtor
    public Availability(double plannedTime, double productionTime) {
        this.plannedTime = plannedTime;
        this.productionTime = productionTime;
    }

    // Getters e Setters
    public double getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(double plannedTime) {
        this.plannedTime = plannedTime;
    }

    public double getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(double productionTime) {
        this.productionTime = productionTime;
    }

    // Cálculo da Disponibilidade
    public double calculateAvailability() {
        return (productionTime / plannedTime) * 100;
    }
}

