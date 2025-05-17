package models.player.buff;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.enums.SkillType;

public class SkillBuff extends Buff{
    @JsonProperty("skillType")
    SkillType skillType;

    public SkillBuff(SkillType skillType, int buffTime) {
        super(buffTime);
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
