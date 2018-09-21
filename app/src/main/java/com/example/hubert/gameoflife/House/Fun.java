package com.example.hubert.gameoflife.House;

public class Fun {

    private String name;
    private String type;
    private int price;
    private int givenFun;

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
