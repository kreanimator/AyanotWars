package com.Interfaces;


import com.Items.HealthPotion;
import com.Items.Item;
import com.Items.ManaPotion;
import com.*;
import com.Units.Enemy;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;

import static java.awt.Font.BOLD;

public class Inventory extends JPanel implements ActionListener, KeyListener {

    public static final int TILE_SIZE = 60;
    public static final int ROWS = 7;
    public static final int COLUMNS = 7;
    public static String[][] INVENTORY = new String[COLUMNS][ROWS];
    int inventorySize = COLUMNS*ROWS;
    static JButton[] slot;
    JTextArea ghA = new JTextArea();
    JTextArea coinA = new JTextArea();
    static JButton gh, coin;
    String yourChoice;
    String itemId = "";


    Item item;

    public Inventory() {

        new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * (ROWS+1));
        setBackground(new Color(0, 0, 0, 0));

        setLayout(new GridLayout(8, 7, 2, 2));
        addKeyListener(this);
        Font myFont = null;
        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("src/main/resources/font/GravityBold8.ttf"));

            myFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        slot = new JButton[inventorySize];
        for (int i = 0; i < inventorySize; i++) {
            slot[i] = new JButton();
            slot[i].setBackground(new Color(0, 0, 0, 0));
            slot[i].setFocusable(false);
            slot[i].setSize(TILE_SIZE, TILE_SIZE);
            slot[i].setVisible(true);
            slot[i].addActionListener(this);
            slot[i].setEnabled(true);
            slot[i].setActionCommand("Item" + i);
            add(slot[i]);

        }
        gh= new JButton();
        gh.setBackground(new Color(0, 0, 0, 0));
        //gh.setEnabled(false);
        gh.setVisible(true);
        gh.setBounds(0,0,TILE_SIZE,TILE_SIZE);
        gh.setIcon((new ImageIcon(Objects.requireNonNull(Inventory.class.getResource("/images/items/goblinhead.png")))));

        ghA.setBounds(TILE_SIZE,0,120 ,TILE_SIZE);
        ghA.setBackground(new Color(0, 0, 0, 0));
        ghA.setFont(myFont);
        ghA.setForeground(new Color(255, 255, 255, 255));
        ghA.setText("\n = " + Enemy.getQuantityKilled());
        ghA.setEditable(false);
        assert myFont != null;
        ghA.setFont(myFont.deriveFont(BOLD,15f));
        //ghA.setVisible(true);


        coin= new JButton();
        coin.setBackground(new Color(0, 0, 0, 0));
        //coin.setEnabled(false);
        coin.setVisible(true);
        coin.setBounds(400,0,TILE_SIZE,TILE_SIZE);
        coin.setIcon((new ImageIcon(Objects.requireNonNull(Inventory.class.getResource("/images/inventoryicons/coin.png")))));

        coinA.setBounds(460,0,120 ,TILE_SIZE);
        coinA.setBackground(new Color(0, 0, 0, 0));
        coinA.setFont(myFont);
        coinA.setForeground(new Color(255, 255, 255, 255));
        coinA.setText("\n = " + Item.getQuantityCollected());
        coinA.setEditable(false);
        coinA.setFont(myFont.deriveFont(BOLD,15f));
       // coinA.setVisible(true);
        add(gh);
        add(ghA);
        add(coin);
        add(coinA);


    }


    public static String setItemId(String itemId) {
        return itemId;
    }


    public String getItemId() {
        return itemId;
    }
    public static void fillInventory(){
            for (int i =0; i < ROWS*COLUMNS; i++){
                if (Objects.equals(INVENTORY[i / COLUMNS][i % ROWS],"manapotion")) {
                    slot[i].setIcon((new ImageIcon(Objects.requireNonNull(Inventory.class.getResource("/images/inventoryicons/manapotion.png")))));
                }
                if (Objects.equals(INVENTORY[i / COLUMNS][i % ROWS], "goblinhead")) {
                    slot[i].setIcon((new ImageIcon(Objects.requireNonNull(Inventory.class.getResource("/images/inventoryicons/goblinhead.png")))));
                }
                if (Objects.equals(INVENTORY[i / COLUMNS][i % ROWS], "coin")) {
                    slot[i].setIcon((new ImageIcon(Objects.requireNonNull(Inventory.class.getResource("/images/inventoryicons/coin.png")))));
                }
                if (Objects.equals(INVENTORY[i / COLUMNS][i % ROWS], "healthpotion")) {
                    slot[i].setIcon((new ImageIcon(Objects.requireNonNull(Inventory.class.getResource("/images/inventoryicons/healthpotion.png")))));
                }
            }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String yourChoice = e.getActionCommand();
        for (int i = 0; i < inventorySize; i++) {
            if (Objects.equals(yourChoice, "Item" + i)) {
                System.out.println(Arrays.deepToString(INVENTORY).replace("], ", "]\n"));

                if (yourChoice.equals("healthpotion")) {

                    HealthPotion.isUsed = true;
                    HealthPotion.effect();

                }
                if (yourChoice.equals("manapotion")) {

                    ManaPotion.isUsed = true;
                    ManaPotion.effect();

                }
            }
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
