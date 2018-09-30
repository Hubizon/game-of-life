package com.example.hubert.gameoflife.House;

public class Lodging {

    private String name;
    private int price;
    private int givenHealth;
    private int givenEnergy;
    private int givenHappiness;
    private String type;
    private int rentTime;

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
    public int getRentTime() { return rentTime; }
}
