package ir.dealit.restful.dto.enums;

public enum WeeklyLoad {
    LESS_THAN_THIRTY_HRS,
    THIRTY_TO_FORTY_HRS,
    GREATER_THAN_FORTY_HRS;

    public String getWeeklyLoad() {
        return this.name();
    }
}
