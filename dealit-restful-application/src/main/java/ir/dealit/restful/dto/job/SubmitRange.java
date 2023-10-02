package ir.dealit.restful.dto.job;

public enum SubmitRange {
    LESS_THAN_5,
    BETWEEN_5_10,
    BETWEEN_10_15,
    BETWEEN_15_20,
    BETWEEN_20_50,
    GREATER_THAN_50;

    public String getSubmitRange() {
        return this.name();
    }
}
