package ir.dealit.restful.module.job.entity;

public enum WeeklyLoad {
    LESS_THAN_THIRTY_HRS,
    THIRTY_TO_FORTY_HRS,
    GREATER_THAN_FORTY_HRS;

    public String getWeeklyLoad() {
        return this.name();
    }
}
