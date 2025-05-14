package models.player.buff;

import models.enums.SkillType;

public class SkillBuff extends Buff{

    SkillType skillType;

    public SkillBuff(SkillType skillType, int energyBuff) {
        super(energyBuff);
        this.skillType = skillType;
    }

    @Override
    public double effectOnMaxEnergy() {
        return 0;
    }

    @Override
    public int effectOnSkill(SkillType skillType) {
        if(remainingTime() == 0)
            return 0;
        if(skillType != null && skillType.equals(this.skillType))
            return 1;
        return 0;
    }

}
