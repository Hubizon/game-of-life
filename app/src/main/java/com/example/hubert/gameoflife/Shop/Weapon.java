package com.example.hubert.gameoflife.Shop;

public class Weapon {
    private final String name;
    private final int price;
    private final boolean isBought = false;

    public Weapon(String name, int price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    // --Commented out by Inspection (12/8/2018 12:30 AM):public boolean getIsBought() { return isBought; }
}
