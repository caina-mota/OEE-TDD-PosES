package main.java.metrics;

public class Quality {
    private int producedParts;
    private int rejectedParts;

    // Construtor
    public Quality(int producedParts, int rejectedParts) {
        this.producedParts = producedParts;
        this.rejectedParts = rejectedParts;
    }

    // Getters e Setters
    public int getProducedParts() {
        return producedParts;
    }

    public void setProducedParts(int producedParts) {
        this.producedParts = producedParts;
    }

    public int getRejectedParts() {
        return rejectedParts;
    }

    public void setRejectedParts(int rejectedParts) {
        this.rejectedParts = rejectedParts;
    }

    // Cálculo da Qualidade
    public double calculateQuality() {
        return ((producedParts - rejectedParts) / (double) producedParts) * 100;
    }
}