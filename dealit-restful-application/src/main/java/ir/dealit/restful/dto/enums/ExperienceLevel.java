package ir.dealit.restful.dto.enums;

public enum ExperienceLevel {
    ENTRY,
    INTERMEDIATE,
    EXPERT;

    public String getExperienceLevel() {
        return this.name();
    }
}
