package FactoryMethod;

import com.company.Ticket;

public class StandardTicketFactory implements TicketFactory {
    @Override
    public Ticket createTicket(int id, String movie, String date, String time, double price) {
        return new StandardTicket(id, movie, date, time, price);
    }
}