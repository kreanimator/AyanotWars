package com.Interfaces;

import com.CreateMap;
import com.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class PlayerChoose implements ActionListener  {
    JFrame chooseWindow;
    JButton warrior, warlock, mage;
    JLabel logo;
    JLabel iconWar, iconMag, iconWarl;
    Font myFont = new Font("TimesRoman", Font.BOLD, 30);

    public PlayerChoose() {

        chooseWindow = new JFrame("Ayanot Wars");
        chooseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseWindow.setSize(900, 900);
        chooseWindow.setResizable(false);
        chooseWindow.setLocationRelativeTo(null);
        chooseWindow.setLayout(null);

        logo = new JLabel();
        logo.setBounds(250, 30, 400, 100);
        logo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/logo.png"))));

        warrior = new JButton("Warrior");
        warrior.setFont(myFont);
        warrior.setBounds(325, 325, 200, 60);
        warrior.addActionListener(this);

        warlock = new JButton("Warlock");
        warlock.setFont(myFont);
        warlock.setBounds(325, 405, 200, 60);
        warlock.addActionListener(this);

        mage = new JButton("Mage");
        mage.setFont(myFont);
        mage.setBounds(325, 485, 200, 60);
        mage.addActionListener(this);

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
        chooseWindow.setVisible(true);

    }
    JFrame window = new JFrame("Ayanot wars");

    public void initWindow(){

        window.setSize(900,900);

        //window.setLocationRelativeTo(null); //
        window.setVisible(true);
        window.setResizable(false);
        window.pack();
        window.setLayout(null);

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

