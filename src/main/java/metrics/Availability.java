package main.java.metrics;

import main.java.exceptions.AvailabilityMetricException;

public class Availability {
    private int plannedTime;  // tempo de planejado em segundos
    private int productionTime;  // tempo de produção em segundos
    private int setupTime;  // tempo de perdas de disponibilidade em segundos
    private double availability;

    // Construtor
    public Availability(int plannedTime, int setupTime) {
        if (plannedTime == 0 || setupTime < 0 || setupTime > plannedTime){
            throw new IllegalArgumentException("Valores inválidos para tempos de planejado e perda");
        }

        this.plannedTime = plannedTime;
        this.setupTime = setupTime;
        this.productionTime = plannedTime - setupTime;
    }

    // Getters e Setters
    public int getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(int plannedTime) {
        if (plannedTime < 0 || plannedTime < setupTime) {
            throw new IllegalArgumentException("Tempo planejado não pode ser menor que zerou ou que o tempo de setup");
        }
        this.plannedTime = plannedTime;
        this.productionTime = this.plannedTime - this.setupTime;

        this.calculateAvailability();
    }

    public int getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(int productionTime) {
        this.productionTime = productionTime;
    }

    public int getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(int setupTime) {
        if (setupTime < 0 || setupTime > plannedTime) {
            throw new IllegalArgumentException("Tempo de setup não pode exceder o tempo planejado ou ser menor que zero");
        }
        this.setupTime = setupTime;
        this.productionTime = this.plannedTime - this.setupTime;

        this.calculateAvailability();
    }

    public double getAvailability() throws AvailabilityMetricException {
        if (availability <= 0) {
            this.calculateAvailability();
        }
        return this.availability;
    }

    public double getAvailabilityPercentage() throws AvailabilityMetricException {
        return getAvailability() * 100.0;
    }

    // Cálculo da Disponibilidade
    public double calculateAvailability() throws AvailabilityMetricException {
        if (setupTime != plannedTime - productionTime) {
            throw new AvailabilityMetricException(String.format("Tempo de perdas maior que a diferença entre o tempo de " +
                    "produção e planejamento"));
        }
        availability = productionTime / (double) plannedTime;
        return availability;
    }
}

