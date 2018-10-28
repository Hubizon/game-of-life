package com.example.hubert.gameoflife.Shop;

public class Weapon {
    String name;
    int price;
    boolean isBought = false;

    public Weapon(String name, int price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public boolean getIsBought() { return isBought; }
}
