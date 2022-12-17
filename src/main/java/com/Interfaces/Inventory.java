package com.Interfaces;

import com.CreateMap;
import com.Items.Item;
import com.Units.Player;
import com.Tiles.Patches;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Inventory extends JPanel implements ActionListener,KeyListener {
    ArrayList<Item> items;
    public static final int TILE_SIZE = 49;
    public static final int ROWS = 7;
    public static final int COLUMNS = 7;

    public Inventory() {

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

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    @Override
    public void keyTyped(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_I) {

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
