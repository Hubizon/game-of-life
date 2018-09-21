package com.example.hubert.gameoflife.Shop;

public class Medicines {

    private String name;
    private int price;
    private int givenHealth;

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
