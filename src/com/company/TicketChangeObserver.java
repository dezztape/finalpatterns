package com.company;

public class TicketChangeObserver implements Observer {
    private Ticket ticket;

    public TicketChangeObserver(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void update() {
        // Логика реакции на изменение билета
        System.out.println("Ticket has been changed. Reacting to the change...");
    }
}
