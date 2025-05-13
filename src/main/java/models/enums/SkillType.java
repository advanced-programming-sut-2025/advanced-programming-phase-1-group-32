package models.enums;

public enum SkillType {
    FARMING,
    FORAGING,
    MINING,
    FISHING;

    public static SkillType getSkillType(String input) {
        for (SkillType type : SkillType.values()) {
            if (type.toString().equalsIgnoreCase(input)) {
                return type;
            }
        }
        return null;
    }
}
