package ir.dealit.restful.dto.enums;

public enum Currency {
    RIAL("IRR"),
    TOMAN("IRR"),
    USD("USD"),
    IRR("IRR");

    private final String code;

    private Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
