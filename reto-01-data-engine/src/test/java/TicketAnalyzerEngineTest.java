
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicketAnalyzerEngineTest {

    private final TicketAnalyzerEngine engine = new TicketAnalyzerEngine();

    @Test
    void shouldClassifyCriticalTicketAsFallaMasiva() {
        // Arrange (Preparar)
        Ticket criticalTicket = new Ticket("TCK-01", "Servidor caído", "AWS", Ticket.Urgency.CRITICAL);

        // Act (Actuar)
        AnalyzedTicket result = engine.classifyTicket(criticalTicket);

        // Assert (Afirmar)
        assertEquals("Falla Masiva", result.category());
        assertTrue(result.requiresImmediateAction());
    }

    @Test
    void shouldClassifyImplementationAsRequerimiento() {
        // Arrange (Preparar)
        Ticket configTicket = new Ticket("TCK-02", "Necesito implementar un nuevo modulo", "web", Ticket.Urgency.MEDIUM);

        // Act (Actuar)
        AnalyzedTicket result = engine.classifyTicket(configTicket);

        // Assert (Afirmar)
        assertEquals("Requerimiento", result.category());
        assertFalse(result.requiresImmediateAction());
    }


    @Test
    void shouldClassifyImplementationAsGeneral() {
        // Arrange (Preparar)
        Ticket generalTicket = new Ticket("TCK-003", "No recuerdo mi contraseña", "App", Ticket.Urgency.LOW);

        // Act (Actuar)
        AnalyzedTicket result = engine.classifyTicket(generalTicket);

        // Assert (Afirmar)
        assertEquals("General", result.category());
        assertFalse(result.requiresImmediateAction());
    }
}
