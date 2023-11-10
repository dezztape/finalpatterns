package FactoryMethod;

import com.company.Ticket;

public class VipTicket extends Ticket {
    public VipTicket(int id, String movie, String date, String time, double price) {
        super(id, movie, date, time, price);
    }
}