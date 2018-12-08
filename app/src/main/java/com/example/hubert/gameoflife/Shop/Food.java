package com.example.hubert.gameoflife.Shop;

public class Food {

    private final String name;
    private final int price;
    private final int givenFood;
    private final int givenHealth;
    private final int givenEnergy;
    private final int givenHappiness;

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
