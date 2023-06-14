package model.enums;

public enum ResturantFoodType {
    PERSIAN("Persian"),
    ITALIAN("Italian"),
    FASTFOOD("Fast"),
    FRIED("Fried"),
    ;

    private String name;

    ResturantFoodType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ResturantFoodType[] getType(String[] name) {
        ResturantFoodType[] output = new ResturantFoodType[name.length];
        for (int i = 0; i < name.length; i++) {
            for (ResturantFoodType type : values()) {
                if (type.name.equals(name[i])) output[i] = type;
            }
            if (output[i] == null) return null;
        }
        return output;
    }
}
