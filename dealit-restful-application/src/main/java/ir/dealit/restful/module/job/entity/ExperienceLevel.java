package ir.dealit.restful.module.job.entity;

public enum ExperienceLevel {
    ENTRY,
    INTERMEDIATE,
    EXPERT;

    public String getExperienceLevel() {
        return this.name();
    }
}
