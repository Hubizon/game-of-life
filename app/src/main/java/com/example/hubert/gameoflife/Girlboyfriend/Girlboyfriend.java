package com.example.hubert.gameoflife.Girlboyfriend;

public class Girlboyfriend {
    private String name;
    private int happiness;
    private int money;

    public Girlboyfriend(String name, int happiness, int money)
    {
        this.name = name;
        this.happiness = happiness;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
