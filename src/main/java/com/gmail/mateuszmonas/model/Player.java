package com.gmail.mateuszmonas.model;

import com.gmail.mateuszmonas.model.field.Field;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private int id;
    private Field startingField;
    private Set<Field> fields = new HashSet<>();

    public Player(int id) {
        this.id = id;
    }

    public Field getStartingField() {
        return startingField;
    }

    public void setStartingField(Field field) {
        if (startingField != null) {
            fields.clear();
        }
        this.startingField = field;
        addField(field);
    }

    public Set<Field> getFields() {
        return fields;
    }

    public void addField(Field field) {
        field.setOwner(this);
        fields.add(field);
    }

    public int getId() {
        return id;
    }
}

