package main.java.metrics;

import main.java.exceptions.QualityMetricException;

public class Quality {
    private int producedParts;
    private int rejectedParts;
    private int goodParts;
    private double quality;

    // Construtor
    public Quality(int producedParts, int rejectedParts) {
        if (producedParts == 0 || rejectedParts < 0 || rejectedParts > producedParts) {
            throw new IllegalArgumentException("Valores inválidos para peças produzidas e rejeitadas");
        }

        this.producedParts = producedParts;
        this.rejectedParts = rejectedParts;
        this.goodParts = producedParts - rejectedParts;
    }

    // Getters e Setters
    public int getProducedParts() {
        return this.producedParts;
    }

    public void setProducedParts(int producedParts) {
        if (producedParts < 0 || producedParts < rejectedParts) {
            throw new IllegalArgumentException("Peças produzidas não pode ser menor que zero ou menor que a quantidade de peças rejeitadas");
        }
        this.producedParts = producedParts;
        this.goodParts = this.producedParts - this.rejectedParts;

        calculateQuality();
    }

    public int getRejectedParts() {
        return this.rejectedParts;
    }

    public void setRejectedParts(int rejectedParts) {
        if (rejectedParts < 0 || rejectedParts > producedParts) {
            throw new IllegalArgumentException("Peças rejeitadas não pode ser menor que zero ou exceder a quantidade de peças produzidas");
        }
        this.rejectedParts = rejectedParts;
        this.goodParts = this.producedParts - this.rejectedParts;

        calculateQuality();
    }

    public int getGoodParts() {
        return this.goodParts;
    }

    public void setGoodParts(int goodParts) {
        this.goodParts = goodParts;
    }

    public double getQuality() throws QualityMetricException {
        if (quality <= 0) {
            calculateQuality();
        }
        return quality;
    }

    public double getQualityPercentage() throws QualityMetricException {
        return getQuality() * 100.0;
    }

    // Cálculo da Qualidade
    public double calculateQuality() throws QualityMetricException {
        this.quality = 0;
        if (producedParts == 0) {
            throw new QualityMetricException("Peças produzidas configuradas com nenhuma parte");
        }
        else if (goodParts != producedParts - rejectedParts) {
            throw new QualityMetricException(String.format("Peças boas configuradas erroneamente\nproduzidas = %d\nboas = %d\nruins %d", producedParts, goodParts, rejectedParts));
        }

        this.quality = goodParts / (double) producedParts;
        return this.quality;
    }
}