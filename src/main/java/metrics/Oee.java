package main.java.metrics;

public class Oee {
    // Declaração das variáveis de instância
    private Availability availability;
    private Quality quality;
    private Performance performance;

    // Construtor
    public Oee(Availability availability, Quality quality, Performance performance) {
        this.availability = availability;
        this.quality = quality;
        this.performance = performance;
    }

    // Cálculo do OEE
    public double calculateOEE() {
        return (availability.calculateAvailability() * quality.calculateQuality() * performance.calculatePerformance()) / 100;
    }

    // Método main para rodar o programa
    public static void main(String[] args) {
        // Criando instâncias das classes Availability, Quality e Performance
        Availability availability = new Availability(100, 90); // Exemplo de valores
        Quality quality = new Quality(100, 10); // Exemplo de valores
        Performance performance = new Performance(100, 80); // Exemplo de valores

        // Criando uma instância da classe Oee
        Oee oee = new Oee(availability, quality, performance);

        // Calculando e exibindo o valor do OEE
        double result = oee.calculateOEE();
        System.out.println("OEE Calculado: " + result);
    }
}



/*public class Oee {

    public static void main(String[] args) {

        // Testando a classe Quality
        int producedParts = 1000;  // peças produzdas
        int rejectedParts = 50;    // peças rejeitadas

        Quality quality = new Quality(producedParts, rejectedParts);
        double qualityResult = quality.calculateQuality();
        System.out.println("Quality: " + qualityResult + "%");

        // Testando a classe Performance
        double expectedTime = 500.0;  // Tempo esperado em horas, por exemplo
        double productionTime = 450.0;  // Tempo de produção real

        Performance performance = new Performance(expectedTime, productionTime);
        double performanceResult = performance.calculatePerformance();
        System.out.println("Performance: " + performanceResult + "%");
    }
}*/