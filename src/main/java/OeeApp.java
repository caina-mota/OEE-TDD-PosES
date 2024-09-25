package main.java;

import main.java.metrics.Availability;
import main.java.metrics.Quality;
import main.java.metrics.Performance;
import main.java.metrics.Oee;
import main.java.exceptions.AvailabilityMetricException;
import main.java.exceptions.QualityMetricException;
import main.java.exceptions.PerformanceMetricException;

public class OeeApp {
    public static void main(String[] args) {
        try {
            // Criando as instâncias das métricas
            Availability availability = new Availability(1000, 200); // Tempo planejado de 1000 e tempo de setup de 200
            Quality quality = new Quality(1000, 50); // 1000 peças produzidas e 50 rejeitadas
            Performance performance = new Performance(800, 100); // Tempo teórico de 800 e tempo de paradas de 100

            // Criando o objeto Oee
            Oee oee = new Oee(availability, quality, performance);

            // Calculando e imprimindo as métricas
            System.out.println("\n### Métricas de OEE ###");
            System.out.printf("Disponibilidade (Availability):                %.2f%%\n", availability.getAvailabilityPercentage());
            System.out.printf("Qualidade (Quality):                           %.2f%%\n", quality.getQualityPercentage());
            System.out.printf("Performance (Performance):                     %.2f%%\n", performance.getPerformancePercentage());
            System.out.printf("\nOEE (Overall Equipment Effectiveness):         %.2f%%\n", oee.getOeePercentage());

        } catch (AvailabilityMetricException | QualityMetricException | PerformanceMetricException e) {
            System.out.println("Erro ao calcular OEE: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
