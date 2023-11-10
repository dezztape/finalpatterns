package com.company;

import FactoryMethod.StandardTicketFactory;
import FactoryMethod.TicketFactory;
import FactoryMethod.VipTicketFactory;
import Strategy.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

public class Ticket implements Subject{
    private int id;
    private String movie;
    private String date;
    private String time;
    private double price;

    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void registerObserver(Observer observer) {

    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }


    public Ticket(int id, String movie, String date, String time, double price) {
        this.id = id;
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }
    public void setMovie(String movie) { this.movie = movie; }
    public String getMovie() { return movie; }
    public void setDate(String date) { this.date = date; }
    public String getDate() { return date; }
    public void setTime(String time) { this.time = time; }
    public String getTime() { return time; }
    public void setPrice(double price) { this.price = price; }
    public double getPrice() { return price; }
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void payForTicket() {
        if (paymentStrategy != null) {
            paymentStrategy.pay(price);
        } else {
            System.out.println("Payment strategy not set.");
        }
    }
    public void somethingChanged() {

        notifyObservers();
    }
    public static TicketFactory getTicketFactory(boolean isVip) {
        if (isVip) {
            return new VipTicketFactory();
        } else {
            return new StandardTicketFactory();
        }
    }
}
