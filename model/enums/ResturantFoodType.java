package model.enums;

public enum ResturantFoodType {
    PERSIAN("Persian Food"),
    ITALIAN("Italian Food"),
    FASTFOOD("Fast Food"),
    FRIED("Fried Food"),
    ;

    private String name;

    ResturantFoodType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
