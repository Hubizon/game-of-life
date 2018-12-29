package com.howky.hubert.gameoflife.education;

public class Skill {

    private final String name;
    private int price;
    private int timeToLearn;
    private boolean isBought = false;
    private boolean isLearned = false;
    private String useName;

    public Skill(String name, String useName, int price, int timeToLearn)
    {
        this.name = name;
        this.price = price;
        //this.timeToLearn = timeToLearn;
        this.useName = useName;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
   // public int getTimeToLearn() { return timeToLearn; }
    public boolean getIsBought() { return isBought; }
    //public boolean getIsLearned() { return isLearned; }
}