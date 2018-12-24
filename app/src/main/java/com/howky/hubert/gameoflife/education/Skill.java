package com.example.hubert.gameoflife.education;

public class Skill {

    private final String name;
    private int price;
    private int timeToLearn;
    private boolean isBought = false;
    private boolean isLearned = false;

    public Skill(String name, int price, int timeToLearn)
    {
        this.name = name;
        this.price = price;
        this.timeToLearn = timeToLearn;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getTimeToLearn() { return timeToLearn; }
    public boolean getIsBought() { return isBought; }
    public boolean getIsLearned() { return isLearned; }
}