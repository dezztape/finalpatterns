package com.company;


public class User {
    private String name;
    private int age;
    private double balance;

    public User(String name, int age, double balance) {
        this.name = name;
        this.age = age;
        this.balance = balance;

    }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setAge(int age) { this.age = age; }
    public int getAge() { return age; }
    public void setBalance(double balance) { this.balance = balance; }
    public double getBalance() { return balance; }

}
