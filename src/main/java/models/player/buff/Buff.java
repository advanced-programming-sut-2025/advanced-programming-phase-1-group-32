package models.player.buff;

import models.App;
import models.Date;
import models.enums.SkillType;

abstract public class Buff {
    protected Date startDate;
    protected int buffTime;
    abstract public double effectOnMaxEnergy();
    abstract public int effectOnSkill(SkillType skillType);


    public Buff(int buffTime) {
        this.buffTime = buffTime;
        this.startDate = App.getActiveGame().getDate();
    }

    public int remainingTime() {
        //TODO: delete in active buff when its 0
        Date currentDate = App.getActiveGame().getDate();
        int pastTime = (currentDate.getDay() - startDate.getDay()) * 24 + (currentDate.getHour() - startDate.getHour());
        if(pastTime > buffTime)
            return 0;
        return buffTime - pastTime;
    }

}
