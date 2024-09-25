package main.java.metrics;

import main.java.exceptions.PerformanceMetricException;

public class Performance {
    private int theoreticalTime; // tempo teórico de produção, pós setup e manutenções
    private int realProductionTime; // tempo real de produção
    private int productionDowntime; // tempo de paradas
    private double performance;

    // Construtor
    public Performance(int theoreticalTime, int productionDowntime) {
        if (theoreticalTime == 0 || productionDowntime < 0 || productionDowntime > theoreticalTime) {
            throw new IllegalArgumentException("Valores inválidos para tempo teórico de produção e tempo de perdas");
        }
        this.theoreticalTime = theoreticalTime;
        this.productionDowntime = productionDowntime;
        this.realProductionTime = theoreticalTime - productionDowntime;
    }

    // Getters e Setters
    public int getTheoreticalTime() {
        return theoreticalTime;
    }

    public void setTheoreticalTime(int theoreticalTime) {
        if (theoreticalTime < 0 || theoreticalTime < productionDowntime) {
            throw new IllegalArgumentException("Tempo teórico não ser menor que zero ou menor que o tempo de paradas");
        }
        this.theoreticalTime = theoreticalTime;
        this.realProductionTime = this.theoreticalTime - productionDowntime;

        calculatePerformance();
    }

    public int getRealProductionTime() {
        return realProductionTime;
    }

    public void setRealProductionTime(int realProductionTime) {
        this.realProductionTime = realProductionTime;
    }

    public int getProductionDowntime() {
        return productionDowntime;
    }

    public void setProductionDowntime(int productionDowntime) {
        if (productionDowntime < 0 || productionDowntime > theoreticalTime) {
            throw new IllegalArgumentException("Tempo de paradas não pode exceder o tempo teórico ou ser menor que zero");
        }
        this.productionDowntime = productionDowntime;
        this.realProductionTime = this.theoreticalTime - productionDowntime;

        calculatePerformance();
    }

    public double getPerformance() {
        if (performance <= 0) {
            calculatePerformance();
        }
        return performance;
    }
    public double getPerformancePercentage() {
        return getPerformance() * 100.0;
    }

    // Cálculo da Performance
    public double calculatePerformance() {
        this.performance = 0;
        if (theoreticalTime == 0) {
            throw new PerformanceMetricException("Nenhum tempo teórico configurado");
        }
        else if (realProductionTime != theoreticalTime - productionDowntime) {
            throw new PerformanceMetricException(String.format("Tempos de produção real, teórico e de paradas configurados de forma errada\nteórico = %d\nparadas = %d\nreal = %d", theoreticalTime, productionDowntime, realProductionTime));
        }
        this.performance = (realProductionTime / (double)theoreticalTime);
        return this.performance;
    }
}
