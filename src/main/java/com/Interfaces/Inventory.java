package com.Interfaces;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import com.Items.*;
import com.Tiles.*;

public class Inventory {
    ArrayList<Item> items;
    public static final int TILE_SIZE = 50;
    public static final int ROWS = 9;
    public static final int COLUMNS = 9;

    ArrayList<Patches> patches;
    private ArrayList<Patches> fillPatches() {
        ArrayList<Patches> patchesList = new ArrayList<>();
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                patchesList.add(new Patches(i, j));
            }
        }
        return patchesList;
    }
}
