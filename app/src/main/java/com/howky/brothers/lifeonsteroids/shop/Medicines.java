package com.howky.brothers.lifeonsteroids.shop;

public class Medicines {

    private final String name;
    private final int price;
    private final int givenHealth;

    public Medicines(String name, int price, int givenHealth)
    {
        this.name = name;
        this.price = price;
        this.givenHealth = givenHealth;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getGivenHealth() { return givenHealth; }
}
