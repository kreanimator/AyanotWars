package com.Interfaces;


import com.Items.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class Inventory extends JPanel implements ActionListener,KeyListener {

    public static final int TILE_SIZE = 49;
    public static final int ROWS = 7;
    public static final int COLUMNS = 7;
    int inventorySize = ROWS*COLUMNS;
    JButton [] slot;
    String yourChoice;
    String itemId = "";
    Item item;
    public Inventory() {

        new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS);

        setBackground(new Color(0,0,0,0));
        setLayout(new GridLayout(7, 7, 2, 2));
        addKeyListener(this);




        slot = new JButton[inventorySize];
        for (int i = 0; i < inventorySize; i++) {
            slot[i] = new JButton();
            slot[i].setBackground(new Color(0,0,0,0));
            slot[i].setFocusable(false);
            slot[i].setSize(TILE_SIZE,TILE_SIZE);
            slot[i].setVisible(true);
            add(slot[i]);
            slot[i].addActionListener(this);
            slot[i].setActionCommand("Item"+i);

        }
    }

    public static String setItemId(String itemId) {
       return itemId;
    }
    public String getItemId() {
        return itemId;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        Toolkit.getDefaultToolkit().sync();
    }


//    public void draw(Graphics g, ImageObserver observer) {
//        g.drawImage(
//
//                image,
//                (pos.x * CreateMap.TILE_SIZE - (CreateMap.TILE_SIZE/2)) + CreateMap.xOffset,
//                (pos.y * CreateMap.TILE_SIZE - (CreateMap.TILE_SIZE/2)) + CreateMap.yOffset,
//                observer
//        );
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String yourChoice = e.getActionCommand();
        for (int i = 0; i < inventorySize; i++) {
            if(Objects.equals(itemId, "healthbar")) {
                slot[i].setIcon((new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/items/healthpotion.png")))));
                slot[i].getItemListeners();
            }
            if(Objects.equals(itemId, "manapotion")) {
                slot[i].setIcon((new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/items/manapotion.png")))));
            }
            if(Objects.equals(itemId, "goblinhead")) {
                slot[i].setIcon((new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/items/goblinhead.png")))));
            }
            if(Objects.equals(itemId, "coin")) {
                slot[i].setIcon((new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/items/coin.png")))));
            }

        }

            if (Objects.equals(yourChoice, "Item0")) {
                System.out.println("ItemId in inventory: "+ getItemId());

            }
        }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_I) {
            setVisible(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
