package ir.dealit.restful.repository.entity;

public enum ExperienceLevel {
    ENTRY,
    INTERMEDIATE,
    EXPERT;

    public String getExperienceLevel() {
        return this.name();
    }
}
