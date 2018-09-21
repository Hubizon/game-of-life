package com.example.hubert.gameoflife.House;

public class Transport {

    private String name;
    private int price;
    // Time to go to the work/school etc. in seconds
    private int speed;

    public Transport(String name, int price, int speed)
    {
        this.name = name;
        this.price = price;
        this.speed = speed;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getSpeed() { return speed; }
}
