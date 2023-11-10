package Decorator;

import Decorator.TicketDecorator;
import com.company.Ticket;

public class ThreeDDecorator implements TicketDecorator {
    private Ticket ticket;

    public ThreeDDecorator(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void addOption() {
        System.out.println("Upgraded to 3D.");
    }
}
