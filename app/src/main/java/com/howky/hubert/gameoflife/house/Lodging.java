package com.howky.hubert.gameoflife.house;

public class Lodging {

    private final String name;
    private final int price;
    private final int givenHealth;
    private final int givenEnergy;
    private final int givenHappiness;
    private String type;
    // --Commented out by Inspection (12/8/2018 12:30 AM):private int rentTime;

    public Lodging(String name, int price, int givenHealth, int givenEnergy, int givenHappiness)
    {
        this.name = name;
        this.price = price;
        this.givenHealth = givenHealth;
        this.givenEnergy = givenEnergy;
        this.givenHappiness = givenHappiness;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getGivenHealth() { return givenHealth; }
    public int getGivenEnergy() { return givenEnergy; }
    public int getGivenHappiness() { return givenHappiness; }
    public String getType() { return type; }
    // --Commented out by Inspection (12/8/2018 12:30 AM):public int getRentTime() { return rentTime; }
}
