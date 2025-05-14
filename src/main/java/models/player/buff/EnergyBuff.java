package models.player.buff;

import models.App;
import models.Date;
import models.enums.SkillType;

public class EnergyBuff extends Buff{

    //TODO : handle max energy
    private double energyBuff;

    public EnergyBuff(double energyBuff, int buffTime) {
        super(buffTime);
        this.energyBuff = energyBuff;
    }

    @Override
    public double effectOnMaxEnergy() {
        if(remainingTime() == 0)
            return 0;
        return energyBuff;
    }

    @Override
    public int effectOnSkill(SkillType skillType) {
        return 0;
    }


}
