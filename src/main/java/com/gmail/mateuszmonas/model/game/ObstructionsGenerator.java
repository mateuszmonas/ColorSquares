package com.gmail.mateuszmonas.model.game;

import com.gmail.mateuszmonas.model.field.Field;
import com.gmail.mateuszmonas.model.field.FieldStatus;
import com.gmail.mateuszmonas.util.GeneralUtil;

import java.util.*;
import java.util.stream.Collectors;

public class ObstructionsGenerator {

    private final static int UNVISITED = -1;
    Set<Field> validFields;
    BoundaryUnionSet boundaryUnionSet;
    Field[][] board;
    int width;
    int height;

    public ObstructionsGenerator(Field[][] board) {
        this.board = board;
        width = board.length;
        height = board[0].length;
        boundaryUnionSet = new BoundaryUnionSet(width, height);
        validFields = Arrays.stream(board).flatMap(Arrays::stream).collect(Collectors.toSet());
    }

    void generateObstructions(int obstructionsCount) {
        int obstructionsCreated = 0;
        while (obstructionsCreated < obstructionsCount) {
            Optional<Field> o = GeneralUtil.getRandomSetElement(validFields);
            if (o.isPresent()) {
                Field field = o.get();
                validFields.remove(field);
                if (!checkIfValid(field)) continue;
                field.setState(FieldStatus.BLOCKED);
                mergeCorners(field);
                addNeighboursToValidFields(field);
                obstructionsCreated++;
            }
        }
    }

    boolean checkIfValid(Field field) {
        if(field.getAdjacent().stream().filter(Field::isEmpty).count()<=1) return true;
        if (boundaryUnionSet.findParent(field.x, field.y) == boundaryUnionSet.findParent(field.x + 1, field.y + 1)) {
            if (boundaryUnionSet.findParent(field.x, field.y + 1) != boundaryUnionSet.findParent(field.x, field.y) &&
                    boundaryUnionSet.findParent(field.x, field.y) != boundaryUnionSet.findParent(field.x + 1, field.y)) {
                return false;
            }
        }
        if (boundaryUnionSet.findParent(field.x + 1, field.y) == boundaryUnionSet.findParent(field.x, field.y + 1)) {
            if (boundaryUnionSet.findParent(field.x, field.y) != boundaryUnionSet.findParent(field.x + 1, field.y) &&
                    boundaryUnionSet.findParent(field.x + 1, field.y + 1) != boundaryUnionSet.findParent(field.x + 1, field.y)) {
                return false;
            }
        }
        if (0<=field.x - 1 && board[field.x - 1][field.y].isEmpty()) {
            if (boundaryUnionSet.findParent(field.x, field.y) == boundaryUnionSet.findParent(field.x, field.y + 1)) {
                return false;
            }
        }
        if (0<=field.y - 1 && board[field.x][field.y - 1].isEmpty()) {
            if (boundaryUnionSet.findParent(field.x, field.y) == boundaryUnionSet.findParent(field.x+1, field.y)) {
                return false;
            }
        }
        if (field.x + 1<width && board[field.x + 1][field.y].isEmpty()) {
            if (boundaryUnionSet.findParent(field.x+1, field.y) == boundaryUnionSet.findParent(field.x+1, field.y + 1)) {
                return false;
            }
        }
        if (field.y + 1<height && board[field.x][field.y + 1].isEmpty()) {
            if (boundaryUnionSet.findParent(field.x+1, field.y+1) == boundaryUnionSet.findParent(field.x, field.y + 1)) {
                return false;
            }
        }
        return true;
    }

    void mergeCorners(Field field) {
        boundaryUnionSet.union(field.x, field.y, field.x+1, field.y+1);
        boundaryUnionSet.union(field.x, field.y, field.x, field.y+1);
        boundaryUnionSet.union(field.x, field.y, field.x+1, field.y);
    }

    void addNeighboursToValidFields(Field field) {
        for (Field field1 : field.getAdjacent()) {
            if (!field1.isBlocked()) {
                validFields.add(field1);
            }
        }
    }

    private static class BoundaryUnionSet{
        private int[][] boundary;

        public BoundaryUnionSet(int width, int height) {
            width = width + 1;
            height = height + 1;
            boundary = new int[width][height];
            for (int i = 0; i < width * height; i++) {
                boundary[i % width][i / width] = i;
            }
            for (int i = 0; i < width; i++) {
                boundary[i][0] = 0;
                boundary[i][height - 1] = 0;
            }
            for (int i = 0; i < height; i++) {
                boundary[0][i] = 0;
                boundary[width-1][i] = 0;
            }
        }

        public int findParent(int x, int y) {
            int parentX = boundary[x][y] % boundary.length;
            int parentY = boundary[x][y] / boundary.length;
            if (boundary[x][y] == boundary[parentX][parentY]) {
                return boundary[x][y];
            }
            int parent = findParent(parentX, parentY);
            boundary[x][y] = parent;
            return parent;
        }

        public void union(int x1, int y1, int x2, int y2) {
            int parent1X = findParent(x1, y1) % boundary.length;
            int parent1Y = findParent(x1, y1) / boundary.length;
            boundary[parent1X][parent1Y] = findParent(x2, y2);
        }

    }

}
