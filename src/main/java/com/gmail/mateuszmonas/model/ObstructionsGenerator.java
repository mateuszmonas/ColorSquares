package com.gmail.mateuszmonas.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class ObstructionsGenerator {

    private final static int UNVISITED = -1;



    static void generateObstructions(int obstructionsCount, GameBoard gameBoard) {
    }

    private class boundaryUnionSet{
        private int[][] boundary;

        public boundaryUnionSet(int width, int height) {
            boundary = new int[width][height];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    boundary[i][j] = i + j;
                }
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
