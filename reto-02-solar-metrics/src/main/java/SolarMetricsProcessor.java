import java.math.BigDecimal;
import java.math.RoundingMode;

public class SolarMetricsProcessor {

    /**
     * RETO A: Cálculo de Energía Total y Promedio
     * El inversor envía las lecturas en un array de primitivos "double".
     * Debes convertir de forma Segura esos valores a BigDecimal y sumarlos.
     */

    public BigDecimal calculateTotalEnergy(double[] rawReadings) {
        // Inicializamos el total en CERO usando la constante de BigDecimal
        BigDecimal total = BigDecimal.ZERO;

        // Bucle clásico iterando sobre el array primitivo
        for (int i = 0; i < rawReadings.length; i++) {
            // Regla de Oro: Nunca usar new BigDecimal(double), Siempre usar BigDecimal.valueOf(Double)
            // o convertir el double a String primero para evitar decimales fantasma.
            BigDecimal safeValue = BigDecimal.valueOf(rawReadings[i]);


            // Los BigDecimal son inmutables. Debes reasignar el resultado
            total = total.add(safeValue);
        }

        return total;
    }

    /**
     * CALCULA El promedio de generación redondeando a 2 decimales
     */

    public BigDecimal calculateAverage(double[] rawReadings) {
        if (rawReadings.length == 0) return BigDecimal.ZERO;

        BigDecimal total = calculateTotalEnergy(rawReadings);
        BigDecimal count = BigDecimal.valueOf(rawReadings.length);

        // Al dividir BigDecimal, ES OBLIGATORIO definir la escala (Cuantos decimales)
        // y el modo de redondedo, de lo contrario lanzará AritmeticException si el numero es periodico (ej, 1/3)
        return total.divide(count, 2, RoundingMode.HALF_UP);
    }


    /**
     * RETO B: Detecci[on de Anomalias (Caidas de tensión)
     */
    public int countDropsBelowThreshols(double[] rawReadings, double threshold) {
        int anomalyCount = 0;
        BigDecimal bdThreshold = BigDecimal.valueOf(threshold);

        // Usamos un bucle for-each (enhanced for loop) por legibilidad
        for (double reading : rawReadings) {
            BigDecimal bdReading = BigDecimal.valueOf(reading);

            // Para comparar BigDecimal no se usa == o < Se, utiliza compareTo()
            // Devuelve -1 si es menor, 0 si es igual , 1 si es mayor
            if (bdReading.compareTo(bdThreshold) < 0) {
                anomalyCount++;
            }
        }

        return anomalyCount;
    }
}
