package FactoryMethod;

import com.company.Ticket;

public class StandardTicket extends Ticket {
    public StandardTicket(int id, String movie, String date, String time, double price) {
        super(id, movie, date, time, price);
    }
}