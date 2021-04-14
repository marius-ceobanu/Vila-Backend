package com.marius.vila.room.model;

public enum Type {
    SINGLE(1), DOUBLE(2);

    private final int capacity;

    Type(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
