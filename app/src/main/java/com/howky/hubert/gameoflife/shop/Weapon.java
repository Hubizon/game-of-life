package com.howky.hubert.gameoflife.shop;

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
    public boolean getIsBought() { return isBought; }
}
