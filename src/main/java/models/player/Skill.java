package models.player;

public class Skill {
    private int experience;
    private int level;

    public Skill(){
        level = 0;
        experience = 0;
    }

    public void addExperience(int experience) {
        this.experience += experience;
        if (this.experience > 100 * this.level + 50) {
            this.experience -= 100 * this.level + 50;
            level++;
        }
    }

    private void addLevel() {

    }

    public int getLevel() {
        return level;
    }

    public void reset() {
        experience = 0;
        level = 0;
    }
}
