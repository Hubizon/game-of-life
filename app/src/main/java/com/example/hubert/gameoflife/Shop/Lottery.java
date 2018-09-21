package com.example.hubert.gameoflife.Shop;

public class Lottery {

    private String name;
    private int price;
    private int chanceToWin;
    private int prize;

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
