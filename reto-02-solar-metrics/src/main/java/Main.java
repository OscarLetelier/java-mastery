import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        SolarMetricsProcessor processor = new SolarMetricsProcessor();

        // Array primitivo simulando datos de un sensor durante 8 horas (De 9 AM a 4 PM)
        double[] dailyReading = {1.2, 2.5, 3.1, 3.5, 2.8, 1.0, 0.4};

        System.out.println(" Reporte de Generacion Solar");

        BigDecimal total = processor.calculateTotalEnergy(dailyReading);
        System.out.println("Total de energia: " + total + "kW");

        BigDecimal average = processor.calculateAverage(dailyReading);
        System.out.println("Promedio por hora: " + average + "kW");

        // Consideramos anomalia si la generacion cae por debajo de 1.0 kW en horas punta
        int drops = processor.countDropsBelowThreshols(dailyReading, 1.0);
        System.out.println("Caida Critica detectada: " + drops);

        // DEMOSTRACION DE PORQUE USAMOS BIGDECIMAL
        System.out.println("El Peligro de Double");
        double badTotal = 0.0;
        for (double reading : dailyReading) {
            badTotal += reading;
        }
        System.out.println("Total con double: " + badTotal + "kW decimales");
        System.out.println("Total con BigDecimal: " + total + "kW Precision");
    }
}
