package com.company;

public class Movie {

    private String name;
    private String genre;
    private int ageRestriction;


    public Movie(String name, String genre, int ageRestriction) {
        this.name = name;
        this.genre = genre;
        this.ageRestriction = ageRestriction;
    }


    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getGenre() { return genre; }
    public void setAgeRestriction(int ageRestriction) { this.ageRestriction = ageRestriction; }
    public int getAgeRestriction() { return ageRestriction; }
}
