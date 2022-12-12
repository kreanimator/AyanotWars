package com.Interfaces;

import com.Items.Item;
import com.Units.Player;
import com.Tiles.Patches;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Inventory extends JPanel {
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
    public JButton addButton() {
        JButton inventory = new JButton("Inventory");
        inventory.setBounds(500,500, TILE_SIZE * 3, TILE_SIZE - 10);
        inventory.setFont(new Font("Lato", Font.BOLD, 25));
        inventory.setVisible(true);
        inventory.setBackground(Color.getHSBColor(31, 18, 95));
        //TODO: Realise inventory button.
        return inventory;
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
