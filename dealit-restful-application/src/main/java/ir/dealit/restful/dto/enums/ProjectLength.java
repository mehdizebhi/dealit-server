package ir.dealit.restful.module.job.entity;

public enum ProjectLength {
    LESS_THAN_ONE_MONTH,
    ONE_TO_THREE_MONTH,
    THREE_TO_SIX_MONTH,
    GREATER_THAN_SIX_MONTH;

    public String getProjectLength() {
        return this.name();
    }
}
