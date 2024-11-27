package util;

public enum FinancingTypesTranslated {
    HOUSE("Casa"),
    APARTMENT("Apartamento"),
    LAND("Terreno");

    private final String financingType;

    FinancingTypesTranslated(String financingType) {
        this.financingType = financingType;
    }

    public String getFinancingType() {
        return financingType;
    }

    public static String getFinancingTypeByClassName(String className) {
        switch (className) {
            case "House":
                return HOUSE.getFinancingType();
            case "Apartment":
                return APARTMENT.getFinancingType();
            case "Land":
                return LAND.getFinancingType();
            default:
                return "Tipo desconhecido";
        }
    }
}