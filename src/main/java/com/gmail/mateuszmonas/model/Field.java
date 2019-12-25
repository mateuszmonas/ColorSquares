package com.gmail.mateuszmonas.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Field {
    int x, y;
    private FieldState state = FieldState.EMPTY;
    private Player owner;
    private Set<Field> adjacent = new HashSet<>();

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setOwner(Player owner) {
        this.state = FieldState.OCCUPIED;
        this.owner = owner;
    }

    public FieldState getState() {
        return state;
    }

    public void setState(FieldState state) {
        if (state == FieldState.OCCUPIED)
            throw new IllegalArgumentException("can't set state to occupied without owner");
        this.owner = null;
        this.state = state;
    }

    public void addAdjacent(Field field) {
        adjacent.add(field);
    }

    public Set<Field> getAdjacent() {
        return adjacent;
    }

    public int getOwnerId() {
        if (state != FieldState.OCCUPIED) throw new IllegalStateException("field is not occupied");
        return owner.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Field)) return false;
        Field field = (Field) o;
        return x == field.x &&
                y == field.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}