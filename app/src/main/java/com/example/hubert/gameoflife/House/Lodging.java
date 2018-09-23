package com.example.hubert.gameoflife.House;

public class Lodging {

    private String name;
    private int price;
    private int givenHealth;
    private int givenEnergy;
    private int givenHappiness;
    private String type;
    private int rentTime;

    public Lodging(String name, int price, int givenHealth, int givenEnergy, int givenHappiness/*TODO: zrobic enum z tyhc typow*/, String type, int rentTime)
    {
        this.name = name;
        this.price = price;
        this.givenHealth = givenHealth;
        this.givenEnergy = givenEnergy;
        this.givenHappiness = givenHappiness;
        this.type = type;
        this.rentTime = rentTime;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getGivenHealth() { return givenHealth; }
    public int getGivenEnergy() { return givenEnergy; }
    public int getGivenHappiness() { return givenHappiness; }
    public String getType() { return type; }
    public int getRentTime() { return rentTime; }
}
