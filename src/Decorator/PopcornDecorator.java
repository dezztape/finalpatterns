package Decorator;

import Decorator.TicketDecorator;
import com.company.Ticket;

public class PopcornDecorator implements TicketDecorator {
    private Ticket ticket;

    public PopcornDecorator(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void addOption() {
        System.out.println("Added Popcorn to the ticket.");
    }
}
