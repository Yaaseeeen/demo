package com.example.demo.entity;

public enum ShipmentStatus {
    REGISTERED("Зарегистрирован"),
    ARRIVED_AT_INTERMEDIATE_OFFICE("Прибыл в промежуточное почтовое отделение"),
    DEPARTED_FROM_OFFICE("Убыл из почтового отделения"),
    RECEIVED_BY_RECIPIENT("Получен адресатом");

    private final String displayName;

    ShipmentStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}