package models.player;

public class Skill {
    private int experience;
    private int level;


    public Skill(){
        level = 0;
        experience = 0;
    }

    public void addExperience(int experience) {
        //TODO
        this.experience += experience;
    }

    private void addLevel() {

    }

    public int getLevel() {
        return level;
    }
}
