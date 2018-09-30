package com.example.hubert.gameoflife.Work;

public class CriminalJob extends Job{

    private int chanceToGetBusted;

    public CriminalJob(String name, int salary, int salaryNo2, int salaryNo3, int markRatio, String subjectNameNeededToWork, int additionalSkillsRes, int chanceToGetBusted) {
        super(name, salary, salaryNo2, salaryNo3, markRatio, subjectNameNeededToWork, additionalSkillsRes);

        this.chanceToGetBusted = chanceToGetBusted;
    }

    public int getChanceToGetBusted() { return chanceToGetBusted; }
}
