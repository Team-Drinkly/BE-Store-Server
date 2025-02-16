package com.drinkhere.drinklystore.domain.enums;

public enum StoreImageType {
    AVAILABLE_DRINK("AVAILABLE_DRINK"),
    MENU("MENU");

    private final String description;

    StoreImageType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
