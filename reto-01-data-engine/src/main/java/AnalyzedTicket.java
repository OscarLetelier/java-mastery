public record AnalyzedTicket (
        Ticket originalTicket,
        String category,
        boolean requiresImmediateAction
) {}
