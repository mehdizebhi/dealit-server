package ir.dealit.restful.dto.enums;

public enum SMSMessageType {
    IN("in"),
    OUT("out"),
    ALL("out");

    private final String value;

    private SMSMessageType(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
