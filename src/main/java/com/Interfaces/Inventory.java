package com.Interfaces;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import com.Enemy;
import com.Items.*;
import com.Tiles.*;

import javax.swing.*;

public class Inventory {
    ArrayList<Item> items;
    public static final int TILE_SIZE = 50;
    public static final int ROWS = 9;
    public static final int COLUMNS = 9;
    
    public Inventory(){

        new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS);
        patches = fillPatches();

    }
    JFrame inventory = new JFrame("Inventory");

    public void initWindow(){

        inventory.setSize(300,300);

        //window.setLocationRelativeTo(null); //
        inventory.setVisible(true);
        inventory.setResizable(false);
        inventory.pack();
        inventory.setLayout(null);

    }
    public void paintComponent(Graphics g) {

        for (Patches patch : patches) {
            patch.draw(g, (ImageObserver) this);
        }

        Toolkit.getDefaultToolkit().sync();
    }


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
