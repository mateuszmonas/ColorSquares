package com.gmail.mateuszmonas.model.field;

import com.gmail.mateuszmonas.model.Player;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Field {
    public int x, y;
    private FieldStatus state = FieldStatus.EMPTY;
    private Player owner;
    private Set<Field> adjacent = new HashSet<>();
    private FieldObserver observer;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        setState(FieldStatus.OCCUPIED);
    }

    public void setState(FieldStatus state) {
        if (state == FieldStatus.OCCUPIED && owner==null)
            throw new IllegalArgumentException("can't set state to occupied without owner");
        if (state != FieldStatus.OCCUPIED) {
            owner = null;
        }
        this.state = state;
        observer.onFieldStateChanged(this);
    }

    public void setObserver(FieldObserver observer) {
        this.observer = observer;
        observer.onFieldStateChanged(this);
    }

    public void addAdjacent(Field field) {
        adjacent.add(field);
    }

    public Set<Field> getAdjacent() {
        return adjacent;
    }

    public int getOwnerId() {
        if (state != FieldStatus.OCCUPIED) throw new IllegalStateException("field is not occupied");
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

    public boolean isBlocked() {
        return state == FieldStatus.BLOCKED;
    }

    public boolean isEmpty() {
        return state == FieldStatus.EMPTY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
