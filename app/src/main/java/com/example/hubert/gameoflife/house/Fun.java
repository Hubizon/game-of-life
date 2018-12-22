package com.example.hubert.gameoflife.house;

public class Fun {

    private final String name;
    private final String type;
    private final int price;
    private final int givenFun;

    public Fun(String name, String type, int price, int givenFun)
    {
        this.name = name;
        this.type = type;
        this.price = price;
        this.givenFun = givenFun;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getPrice() { return price; }
    public int getGivenFun() { return givenFun; }
}
