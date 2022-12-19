package com.Interfaces;

import com.CreateMap;
import com.Items.Item;
import com.Tiles.Grass;
import com.Tiles.Patches;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;
import com.Units.*;

public class Inventory extends JPanel implements ActionListener,KeyListener {

    public static final int TILE_SIZE = 49;
    public static final int ROWS = 7;
    public static final int COLUMNS = 7;
    int inventorySize = ROWS*COLUMNS;
    JButton [] slot;
    String yourChoice;
    String itemId = "";
    ArrayList<Inventory> inventories;

    public Inventory() {

        new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS);
        setBackground(new Color(255, 212, 113));
        setLayout(new GridLayout(7, 7, 2, 2));
        slot = new JButton[inventorySize];
        for (int i = 0; i < inventorySize; i++) {
            slot[i] = new JButton();
            slot[i].setBackground(new Color(255, 212, 113));
            slot[i].setFocusable(false);
            slot[i].setSize(TILE_SIZE,TILE_SIZE);
            slot[i].setVisible(true);
            add(slot[i]);
            slot[i].addActionListener(this);
            slot[i].setActionCommand("Item"+i);
        }



    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        for (Patches patch : patches) {
//            patch.draw(g, this);
//        }

        Toolkit.getDefaultToolkit().sync();
    }






    @Override
    public void actionPerformed(ActionEvent e) {
    String yourChoice = e.getActionCommand();
    ImageIcon iconhp = new ImageIcon("/images/items/healthpotion.png");
    ImageIcon iconmp = new ImageIcon("/images/items/manapotion.png");
    ImageIcon gh= new ImageIcon("/images/items/goblinhead.png");
    ImageIcon coin= new ImageIcon("/images/items/coin.png");


        for (JButton jButton : slot) {
            switch (itemId) {
                case ("healthbar") -> jButton.setIcon(iconhp);
                case ("manapotion") -> jButton.setIcon(iconmp);
                case ("goblinhead") -> jButton.setIcon(gh);
                case ("coin") -> jButton.setIcon(coin);
            }break;
        }
      for(JButton jButton:slot){
          if(Objects.equals(yourChoice, "Item" + jButton)){

          }
      }
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
