package FactoryMethod;

import com.company.Ticket;

public class VipTicketFactory implements TicketFactory {
    @Override
    public Ticket createTicket(int id, String movie, String date, String time, double price) {
        return new VipTicket(id, movie, date, time, price);
    }
}