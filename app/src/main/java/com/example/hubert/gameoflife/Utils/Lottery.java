package com.example.hubert.gameoflife;

public class Lottery {

    private String name;
    private int price;
    private double chancheToWin;
    private int prize;

    public Lottery(String name, int price, double chancheToWin, int prize)
    {
        this.name = name;
        this.price = price;
        this.chancheToWin = chancheToWin;
        this.prize = prize;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public double getChancheToWin() { return chancheToWin; }
    public int getPrize() { return prize; }
}
