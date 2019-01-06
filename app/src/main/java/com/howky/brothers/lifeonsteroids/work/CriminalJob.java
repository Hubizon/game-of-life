package com.howky.brothers.lifeonsteroids.work;

import com.howky.brothers.lifeonsteroids.education.Skill;
import com.howky.brothers.lifeonsteroids.house.Fun;
import com.howky.brothers.lifeonsteroids.house.Lodging;
import com.howky.brothers.lifeonsteroids.house.Transport;
import com.howky.brothers.lifeonsteroids.R;
import com.howky.brothers.lifeonsteroids.shop.Weapon;

public class CriminalJob extends Job{
    private final int chanceToGetBusted;
    private final Weapon[] weaponsNeeded;
    //

    public CriminalJob(String name, int salary, int salaryIncrease, Fun minPhoneNeeded, Fun minComputerNeeded, Fun minTvNeeded, Lodging minLodgingNeeded,
                       Transport minTransportNeeded,  Skill[] skillsNeeded, int chanceToGetBusted, Weapon[] weaponsNeeded) {
        super(name, salary, salaryIncrease, 1, R.string.saved_criminal_points_key, minPhoneNeeded, minComputerNeeded, minTvNeeded, minLodgingNeeded, minTransportNeeded, skillsNeeded);

        this.chanceToGetBusted = chanceToGetBusted;
        this.weaponsNeeded = weaponsNeeded;
    }

    //TOD: uzywać tego! xD
    public int getChanceToGetBusted() { return chanceToGetBusted; }
    public Weapon[] getWeaponsNeeded() { return weaponsNeeded; }
}
