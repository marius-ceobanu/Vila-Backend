package com.marius.vila.room.model;

public enum RType {
    SINGLE(1), DOUBLE(2);

    private final int capacity;

    RType(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
