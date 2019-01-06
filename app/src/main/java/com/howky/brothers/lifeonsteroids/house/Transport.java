package com.howky.brothers.lifeonsteroids.house;

public class Transport {

    private final String name;
    private final int price;
    // Time to go to the work/school etc. in seconds
    private final int speed;

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
