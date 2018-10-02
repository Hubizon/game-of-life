package com.example.hubert.gameoflife.Work;

import com.example.hubert.gameoflife.Education.Skill;
import com.example.hubert.gameoflife.House.Fun;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.House.Transport;

public class CriminalJob extends Job{

    private int chanceToGetBusted;

    public CriminalJob(String name, int salary, int salaryNo2, int salaryNo3, int markRatio, int additionalSkillsRes,
                       Fun minFunNeeded, Lodging minLodgingNeeded, Transport minTransportNeeded,  Skill[] skillsNeeded, int chanceToGetBusted/*, BlackItem[] neededBlackItems*/) {
        super(name, salary, salaryNo2, salaryNo3, markRatio, additionalSkillsRes, minFunNeeded, minLodgingNeeded, minTransportNeeded, skillsNeeded);

        this.chanceToGetBusted = chanceToGetBusted;
    }

    public int getChanceToGetBusted() { return chanceToGetBusted; }
}
