import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        TicketAnalyzerEngine engine = new TicketAnalyzerEngine();

        System.out.println("Generando 100,000 tickets de prueba...");
        List<Ticket> massiveTicketList = IntStream.range(0, 100000)
                .mapToObj(i -> {
                    if (i % 1000 == 0) { // 1 de cada 1000 será crítico
                        return new Ticket("TCK-" + i, "El servidor principal no responde", "Monitoreo", Ticket.Urgency.CRITICAL);
                    } else {
                        return new Ticket("TCK-" + i, "Duda de uso normal", "App Móvil", Ticket.Urgency.LOW);
                    }
                })
                .toList();

        System.out.println("Procesando masivamente con Virtual Threads...");
        long startTime = System.currentTimeMillis();

        List<AnalyzedTicket> criticalTickets = engine.processTicketsMassively(massiveTicketList);

        long endTime = System.currentTimeMillis();

        System.out.println("Procesamiento de 100,000 tickets completado en: " + (endTime - startTime) + " ms");
        System.out.println("Tickets críticos encontrados y aislados: " + criticalTickets.size());
    }
}