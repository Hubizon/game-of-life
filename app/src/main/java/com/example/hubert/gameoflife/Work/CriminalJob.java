package com.example.hubert.gameoflife.Work;

import com.example.hubert.gameoflife.Education.Skill;
import com.example.hubert.gameoflife.House.Fun;
import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.House.Transport;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Shop.Weapon;

public class CriminalJob extends Job{

    private int chanceToGetBusted;
    private Weapon[] weaponsNeeded;

    public CriminalJob(String name, int salary, int salaryIncrease, Fun minPhoneNeeded, Fun minComputerNeeded, Fun minTvNeeded, Lodging minLodgingNeeded,
                       Transport minTransportNeeded,  Skill[] skillsNeeded, int chanceToGetBusted, Weapon[] weaponsNeeded) {
        super(name, salary, salaryIncrease, 0, R.string.saved_criminal_points_key, minPhoneNeeded, minComputerNeeded, minTvNeeded, minLodgingNeeded, minTransportNeeded, skillsNeeded);

        this.chanceToGetBusted = chanceToGetBusted;
        this.weaponsNeeded = weaponsNeeded;
    }

    //TODO: używać tego! xD
    public int getChanceToGetBusted() { return chanceToGetBusted; }
    public Weapon[] getWeaponsNeeded() { return weaponsNeeded; }
}
