package FactoryMethod;

import com.company.Ticket;

public interface TicketFactory {
    Ticket createTicket(int id, String movie, String date, String time, double price);
}