package com.UnusedClasses;

import java.util.Arrays;


public class Map {
    public Map() {

        int wh = 30;
        int ww = 30;

        int stone = 1;
        int tree = 2;

        int stonesCount = 10;
        int treesCount = 10;

        final int[][] field = new int[ww][wh]; //Creating field

        showField(field);
    }

    private static void showField(int[][] result) {
        for (int[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

//    private static void generateTiles(int[][] field, int stone, int tree, int stonesCount, int treesCount) {
//        int[][] newField = field.clone();
//        for (int i = 0; i < stonesCount; i++) {
//            checkField();
//        }
//        for (int i = 0; i < treesCount; i++) {
//            checkField();
//        }
//        return newField;
//    }

        private static void checkField(int[][] field, int count, int[] points){

        }

    public static int[][] fillTiles(int[][] field, int x0, int y0, int x1, int y1) {
        for (int y = y0; y <= y1; y++) {
            for (int x = x0; x <= x1; x++) {
                field[x][y] = 1;
            }
        }
        return field;
    }
}