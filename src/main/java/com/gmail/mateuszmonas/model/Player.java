package com.gmail.mateuszmonas.model;

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
        if(startingField!=null){
            fields.remove(startingField);
            field.setState(FieldState.EMPTY);
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

