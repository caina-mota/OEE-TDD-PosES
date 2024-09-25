package main.java.metrics;

public class Oee {
    // Declaração das variáveis de instância
    private Availability availability;
    private Quality quality;
    private Performance performance;
    private double oee;

    // Construtor
    public Oee(Availability availability, Quality quality, Performance performance) {
        if (availability == null || quality == null || performance == null){
            throw new IllegalArgumentException("Objetos de disponibilidade, qualidade ou performance não podem ser nulos");
        }

        this.availability = availability;
        this.quality = quality;
        this.performance = performance;
    }

    // Getters e Setters
    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        if (availability == null) {
            throw new IllegalArgumentException("Objeto de disponibilidade não pode ser nulo");
        }
        this.availability = availability;
        calculateOEE();
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        if (quality == null) {
            throw new IllegalArgumentException("Objeto de qualidade não pode ser nulo");
        }
        this.quality = quality;
        calculateOEE();
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        if (performance == null) {
            throw new IllegalArgumentException("Objeto de performance não pode ser nulo");
        }
        this.performance = performance;
        calculateOEE();
    }

    public double getOee() {
        if (oee <= 0) {
            calculateOEE();
        }
        return oee;
    }

    public double getOeePercentage() {
        return getOee() * 100.0;
    }

    // Cálculo do OEE
    public double calculateOEE() {
        this.oee = availability.getAvailability() * performance.getPerformance() * quality.getQuality();
        return this.oee;
    }
}

//    // Método main para rodar o programa
//    public static void main(String[] args) {
//        // Criando instâncias das classes Availability, Quality e Performance
//        Availability availability = new Availability(100, 90); // Exemplo de valores
//        Quality quality = new Quality(100, 10); // Exemplo de valores
//        Performance performance = new Performance(100, 80); // Exemplo de valores
//
//        // Criando uma instância da classe Oee
//        Oee oee = new Oee(availability, quality, performance);
//
//        // Calculando e exibindo o valor do OEE
//        double result = oee.calculateOEE();
//        System.out.println("OEE Calculado: " + result);
//    }