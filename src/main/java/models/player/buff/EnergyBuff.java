package models.player.buff;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.App;
import models.Date;
import models.enums.SkillType;

public class EnergyBuff extends Buff implements Serializable {

    //TODO : handle max energy
    @JsonProperty("energyBuff")
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
