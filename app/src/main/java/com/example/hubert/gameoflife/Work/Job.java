package com.example.hubert.gameoflife.Work;

import com.example.hubert.gameoflife.Education.Skill;
import com.example.hubert.gameoflife.House.Fun;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.House.Transport;

public class Job {
    private String name;
    private int salary;
    private int salaryNo2;
    private int salaryNo3;
    private int markRatio;
    private int additionalSkillsRes;
    private Fun minFunNeeded;
    private Lodging minLodgingNeeded;
    private Transport minTransportNeeded;
    private Skill[] skillsNeeded;

    public Job(String name, int salary, int salaryNo2, int salaryNo3, int markRatio, int additionalSkillsRes,
               Fun minFunNeeded, Lodging minLodgingNeeded, Transport minTransportNeeded, Skill[] skillsNeeded)
    {
        this.name = name;
        this.salary = salary;
        this.salaryNo2 = salaryNo2;
        this.salaryNo3 = salaryNo3;
        this.markRatio = markRatio;
        this.additionalSkillsRes = additionalSkillsRes;
        this.minFunNeeded = minFunNeeded;
        this.minLodgingNeeded = minLodgingNeeded;
        this.minTransportNeeded = minTransportNeeded;
        this.skillsNeeded = skillsNeeded;
    }

    public String getName() { return name; }
    public int getSalary() { return salary; }
    public int getSalaryNo2() { return salaryNo2; }
    public int getSalaryNo3() { return salaryNo3; }
    public int getMarkRatio() { return markRatio; }
    public int getAdditionalSkillsRes() { return additionalSkillsRes; }
    public Fun getMinFunNeeded() { return minFunNeeded; }
    public Lodging getMinLodgingNeeded() { return minLodgingNeeded; }
    public Transport getMinTransportNeeded() { return minTransportNeeded; }
    public Skill[] getSkillsNeeded() { return skillsNeeded; }
}
