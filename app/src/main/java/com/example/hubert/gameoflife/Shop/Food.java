package com.example.hubert.gameoflife.Shop;

public class Food {

    private String name;
    private int price;
    private int givenFood;
    private int givenHealth;
    private int givenEnergy;
    private int givenHappiness;

    public Food(String name, int price, int givenFood, int givenHealth, int givenEnergy, int givenHappiness)
    {
        this.name = name;
        this.price = price;
        this.givenFood = givenFood;
        this.givenHealth = givenHealth;
        this.givenEnergy = givenEnergy;
        this.givenHappiness = givenHappiness;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getGivenFood() { return givenFood; }
    public int getGivenHealth() { return givenHealth; }
    public int getGivenEnergy() { return givenEnergy; }
    public int getGivenHappiness() { return givenHappiness; }
}
