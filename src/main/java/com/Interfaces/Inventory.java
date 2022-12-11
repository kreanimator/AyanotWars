package com.Interfaces;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import com.Items.*;
import com.Player;
import com.Tiles.*;

import javax.swing.*;

public class Inventory extends JPanel  {

    ArrayList<Item> items;
    public static final int TILE_SIZE = 49;
    public static final int ROWS = 7;
    public static final int COLUMNS = 7;
    
    public Inventory(){

        new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS);
        patches = fillPatches();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Patches patch : patches) {
            patch.draw(g, this);
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

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        try {
            if (key == KeyEvent.VK_I) {
                Player.closeWindow();
            }
        } catch (Exception ignored){

        }
    }

}
