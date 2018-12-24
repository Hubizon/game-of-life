package com.howky.hubert.gameoflife.work;

import com.howky.hubert.gameoflife.education.Skill;
import com.howky.hubert.gameoflife.house.Fun;
import com.howky.hubert.gameoflife.house.Lodging;
import com.howky.hubert.gameoflife.house.Transport;
import com.howky.hubert.gameoflife.R;
import com.howky.hubert.gameoflife.shop.Weapon;

public class CriminalJob extends Job{
    private final int chanceToGetBusted;
    private final Weapon[] weaponsNeeded;
    //

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
