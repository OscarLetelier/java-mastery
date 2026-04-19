public record Ticket (
    String id,
    String description,
    String source,
    Urgency urgency

) {
    // Definimos el dominio de la urgencia para no depender de un String Suelto
    public enum Urgency {
        LOW, MEDIUM, HIGH, CRITICAL
    }
}
