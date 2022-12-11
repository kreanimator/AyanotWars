package com.Interfaces;

import com.CreateMap;
import com.Units.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PlayerChoose implements ActionListener {
    JFrame chooseWindow;
    JButton warrior, warlock, mage;
    JLabel logo;
    JLabel iconWar, iconMag, iconWarl;

    static JTextField name;

    Font myFont = new Font("Lato", Font.BOLD, 30);

    public PlayerChoose() {


        chooseWindow = new JFrame("Ayanot Wars");
        chooseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseWindow.setSize(900, 900);
        chooseWindow.setResizable(false);
        chooseWindow.setLocationRelativeTo(null);
        chooseWindow.setLayout(null);

        JLabel nameEnter = new JLabel();
        nameEnter.setText("Enter your name: ");
        nameEnter.setFont(myFont);
        nameEnter.setVisible(true);
        nameEnter.setBounds(145, 200, 300, 60);


        name = new JTextField();
        name.setBounds(415, 200, 200, 60);
        name.setVisible(true);
        name.setFont(myFont);
        name.addActionListener(this);


        logo = new JLabel();
        logo.setBounds(250, 30, 400, 100);
        logo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/logo.png"))));

        warrior = new JButton("Warrior");
        warrior.setFont(myFont);
        warrior.setBounds(325, 325, 200, 60);
        warrior.addActionListener(this);
        warrior.setFocusable(false);

        warlock = new JButton("Warlock");
        warlock.setFont(myFont);
        warlock.setBounds(325, 405, 200, 60);
        warlock.addActionListener(this);
        warlock.setFocusable(false);

        mage = new JButton("Mage");
        mage.setFont(myFont);
        mage.setBounds(325, 485, 200, 60);
        mage.addActionListener(this);
        mage.setFocusable(false);

        iconWar = new JLabel();
        iconWar.setBounds(250, 325, 60, 60);
        iconWar.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/warrior.png"))));
        iconWar.setHorizontalAlignment(SwingConstants.LEFT);
        iconWar.setVisible(true);

        iconWarl = new JLabel();
        iconWarl.setBounds(250, 405, 60, 60);
        iconWarl.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/warlock.png"))));
        iconWarl.setHorizontalAlignment(SwingConstants.LEFT);
        iconWarl.setVisible(true);

        iconMag = new JLabel();
        iconMag.setBounds(250, 485, 60, 60);
        iconMag.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/mage.png"))));
        iconMag.setHorizontalAlignment(SwingConstants.LEFT);
        iconMag.setVisible(true);


        chooseWindow.add(logo);
        chooseWindow.add(warrior);
        chooseWindow.add(mage);
        chooseWindow.add(warlock);
        chooseWindow.add(iconWar);
        chooseWindow.add(iconWarl);
        chooseWindow.add(iconMag);
        chooseWindow.add(name);
        chooseWindow.add(nameEnter);
        chooseWindow.setVisible(true);

    }

    JDialog window = new JDialog();

    public void initWindow() {

        window.setSize(900, 900);

        //window.setLocationRelativeTo(null); //
        window.setVisible(true);
        window.setResizable(false);
        window.pack();
        window.setLayout(null);
        window.setTitle("Ayanot wars");
//       window.add(Inventory.addButton());
         //TODO: Fix the inventory button, cvause it's ruins game.

    }

    public static String setPlayerName() {
        String playerName = name.getText();
        name.selectAll();
        return playerName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == warrior) {
            Player.setPlayerClass("warrior");
            CreateMap createMap = new CreateMap();
            window.add(createMap);
            window.addKeyListener(createMap);
            SwingUtilities.invokeLater(this::initWindow);

            chooseWindow.dispose();
        }
        if (e.getSource() == warlock) {

            Player.setPlayerClass("warlock");
            CreateMap createMap = new CreateMap();
            window.add(createMap);
            window.addKeyListener(createMap);
            SwingUtilities.invokeLater(this::initWindow);
           chooseWindow.dispose();
        }
        if (e.getSource() == mage) {

            Player.setPlayerClass("mage");
            CreateMap createMap = new CreateMap();
            window.add(createMap);
            window.addKeyListener(createMap);
            SwingUtilities.invokeLater(this::initWindow);
           chooseWindow.dispose();
        }
    }
}

