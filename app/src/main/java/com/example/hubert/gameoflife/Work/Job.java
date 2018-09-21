package com.example.hubert.gameoflife.Work;

public class Job {
    private String name;
    private int Salary;

    public Job(String name, int salary)
    {
        this.name = name;
        this.Salary = salary;
    }

    public String getName() { return name; }
    public int getSalary() { return Salary; }
}
