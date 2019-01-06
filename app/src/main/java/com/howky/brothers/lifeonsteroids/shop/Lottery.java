package com.howky.brothers.lifeonsteroids.shop;

public class Lottery {

    private final String name;
    private final int price;
    private final int chanceToWin;
    private final int prize;

    public Lottery(String name, int price, int chanceToWin, int prize)
    {
        this.name = name;
        this.price = price;
        this.chanceToWin = chanceToWin;
        this.prize = prize;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getChanceToWin() { return chanceToWin; }
    public int getPrize() { return prize; }
}
