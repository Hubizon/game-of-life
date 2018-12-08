package com.example.hubert.gameoflife.Work;

import com.example.hubert.gameoflife.Education.Skill;
import com.example.hubert.gameoflife.House.Fun;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.House.Transport;

public class Job {
    private final String name;
    private long salary;
    private final int salaryIncrease;
    private final int markRatio;
    private final int additionalSkillsRes;
    private final Fun minPhoneNeeded;
    private final Fun minComputerNeeded;
    private final Fun minTvNeeded;
    private final Lodging minLodgingNeeded;
    private final Transport minTransportNeeded;
    private final Skill[] skillsNeeded;
    private int position = 1;
    private int positionPoints = 75;

    public Job(String name, long salary, int salaryIncrease, int markRatio, int additionalSkillsRes,
               Fun minPhoneNeeded, Fun minComputerNeeded, Fun minTvNeeded, Lodging minLodgingNeeded, Transport minTransportNeeded, Skill[] skillsNeeded)
    {
        this.name = name;
        this.salary = salary;
        this.salaryIncrease = salaryIncrease;
        this.markRatio = markRatio;
        this.additionalSkillsRes = additionalSkillsRes;
        this.minPhoneNeeded = minPhoneNeeded;
        this.minComputerNeeded = minComputerNeeded;
        this.minTvNeeded = minTvNeeded;
        this.minLodgingNeeded = minLodgingNeeded;
        this.minTransportNeeded = minTransportNeeded;
        this.skillsNeeded = skillsNeeded;
    }

    public String getName() { return name; }
    public long getSalary() { return salary; }
    public int getSalaryIncrease() { return salaryIncrease; }
    public int getMarkRatio() { return markRatio; }
    public int getAdditionalSkillsRes() { return additionalSkillsRes; }
    public Fun getMinPhoneNeeded() { return minPhoneNeeded; }
    public Fun getMinComputerNeeded() { return minComputerNeeded; }
    public Fun getMinTvNeeded() { return minTvNeeded; }
    public Lodging getMinLodgingNeeded() { return minLodgingNeeded; }
    public Transport getMinTransportNeeded() { return minTransportNeeded; }
    public Skill[] getSkillsNeeded() { return skillsNeeded; }
    public int getPosition() { return position; }
    public int getPositionPoints() { return positionPoints; }

    public void setSalary(long salary) { this.salary = salary; }
    public void setPosition(int position) { this.position = position; }
    public void setPositionPoints(int positionPoints) { this.positionPoints = positionPoints; }
}
