package com.example.hubert.gameoflife.work;

import com.example.hubert.gameoflife.education.Skill;
import com.example.hubert.gameoflife.house.Fun;
import com.example.hubert.gameoflife.house.Lodging;
import com.example.hubert.gameoflife.house.Transport;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.shop.Weapon;

public class CriminalJob extends Job{

    private final int chanceToGetBusted;
    private final Weapon[] weaponsNeeded;

    public CriminalJob(String name, int salary, int salaryIncrease, Fun minPhoneNeeded, Fun minComputerNeeded, Fun minTvNeeded, Lodging minLodgingNeeded,
                       Transport minTransportNeeded,  Skill[] skillsNeeded, int chanceToGetBusted, Weapon[] weaponsNeeded) {
        super(name, salary, salaryIncrease, 0, R.string.saved_criminal_points_key, minPhoneNeeded, minComputerNeeded, minTvNeeded, minLodgingNeeded, minTransportNeeded, skillsNeeded);

        this.chanceToGetBusted = chanceToGetBusted;
        this.weaponsNeeded = weaponsNeeded;
    }

    //TODO: uzywać tego! xD
    public int getChanceToGetBusted() { return chanceToGetBusted; }
    public Weapon[] getWeaponsNeeded() { return weaponsNeeded; }
}
