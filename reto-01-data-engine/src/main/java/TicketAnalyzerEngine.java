import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class TicketAnalyzerEngine {

    /**
     *  RETO A: Clasificación Inteligente usando Pattern Matching
     */

    public AnalyzedTicket classifyTicket(Ticket ticket) {
        // En Java 21, el 'switch' es una expresión que retorna un valor directamente
        // Esto elimina la necesidad de crear variables temporales y mutarlas.
        return switch (ticket) {

            // Guard Clause (when): Extraemos el ticket como 't' y evaluamos una condición inmediatamente
            case Ticket t when t.urgency() == Ticket.Urgency.CRITICAL ->
                new AnalyzedTicket(t, "Falla Masiva", true);

            // Podemos encadenar condiciones logicas evaluando el contenido del mensaje
            case Ticket t when  t.description().toLowerCase().contains("configurar") ||
                                t.description().toLowerCase().contains("implementacion") ->
                new AnalyzedTicket(t, "Requerimiento", false);

            // Caso por defecto (Obligatorio en switch expressions para garantizar que siempre retorne algo)
            case Ticket t ->
                new AnalyzedTicket(t, "General", false);
        };
    }


    /**
     * RETO B: Procesamiento Masivo con Virtual Threads
     */

    public List<AnalyzedTicket> processTicketsMassively(List<Ticket> tickets) {

        // 1. STRUCTURED CONCURRENCY: El bloque try-with-resources garantiza que
        // el Executor espere a que todos loshilos terminen y luego se cierre limpiamentet.
        // newVirtualThreadPerTaskExecutor() crea un nuevo hilo virtual para CADA tarea.

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            // 2. FASE DE DISPERSION (Scatter): Lanzamos todas las clasificaciones en paralelo
            List<CompletableFuture<AnalyzedTicket>> futures = tickets.stream()
                    .map(ticket -> CompletableFuture.supplyAsync(() -> classifyTicket(ticket), executor))
                    .toList();

            // 3. FASE DE RECOLECCION  (Gather): Esperamos los resultados y los filtramos
            return futures.stream()
                    .map(CompletableFuture::join) // Extrae el resultado de cada hilo virtual
                    .filter(AnalyzedTicket::requiresImmediateAction) // Filtramos solo lo urgente
                    .toList();
        }
    }
}
