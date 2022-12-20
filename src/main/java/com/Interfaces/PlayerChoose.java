package com.Interfaces;

import com.CreateMap;
import com.Units.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;
import com.Graphics.*;

public class PlayerChoose extends JPanel implements ActionListener {
    JFrame chooseWindow;
    BufferedImage image;

    JButton warrior, warlock, mage;

    JLabel logo;
    JLabel iconWar, iconMag, iconWarl;
    static JTextField name;

//    String font = "src/main/resources/font/GravityBold8.ttf";
//    Font myFont = new Font(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(font)));


    public PlayerChoose() {

        Font myFont = null;
        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("src/main/resources/font/GravityBold8.ttf"));

            myFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);


        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        chooseWindow = new JFrame();
        chooseWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        chooseWindow.setSize(1024, 576);
        chooseWindow.setResizable(false);
        chooseWindow.setUndecorated(true);
        chooseWindow.setLocationRelativeTo(null);
        chooseWindow.setLayout(null);
        chooseWindow.setTitle("Ayanot Wars");

        JLabel nameEnter = new JLabel();
        nameEnter.setText("Name: ");
        assert myFont != null;
        nameEnter.setFont(myFont.deriveFont(Font.BOLD,25f));
        nameEnter.setVisible(true);
        nameEnter.setBounds(550, 100, 300, 60);


        name = new JTextField();
        name.setBounds(550, 150, 200, 60);
        name.setVisible(true);
        name.setFont(myFont.deriveFont(Font.BOLD,20f));
        name.addActionListener(this);
        name.setBackground(new Color(255, 212, 133,100));


        logo = new JLabel();
        logo.setBounds(0, 0, 1024, 576);
        logo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/background.gif"))));

        warrior = new JButton("Warrior");
        warrior.setFont(myFont.deriveFont(Font.BOLD,25f));
        warrior.setBounds(550, 225, 200, 60);
        warrior.addActionListener(this);
        warrior.setFocusable(false);
        warrior.setBackground(new Color(255, 212, 133,100));

        warlock = new JButton("Warlock");
        warlock.setFont(myFont.deriveFont(Font.BOLD,25f));
        warlock.setBounds(550, 300, 200, 60);
        warlock.addActionListener(this);
        warlock.setFocusable(false);
        warlock.setBackground(new Color(255, 212, 133,100));

        mage = new JButton("Mage");
        mage.setFont(myFont.deriveFont(Font.BOLD,25f));
        mage.setBounds(550, 375, 200, 60);
        mage.addActionListener(this);
        mage.setFocusable(false);
        mage.setBackground(new Color(255, 212, 133,100));

        iconWar = new JLabel();
        iconWar.setBounds(485, 225, 60, 60);
        iconWar.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/warrior.png"))));
        iconWar.setHorizontalAlignment(SwingConstants.LEFT);
        iconWar.setVisible(true);

        iconWarl = new JLabel();
        iconWarl.setBounds(485, 300, 60, 60);
        iconWarl.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/warlock.png"))));
        iconWarl.setHorizontalAlignment(SwingConstants.LEFT);
        iconWarl.setVisible(true);

        iconMag = new JLabel();
        iconMag.setBounds(485, 375, 60, 60);
        iconMag.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/mage.png"))));
        iconMag.setHorizontalAlignment(SwingConstants.LEFT);
        iconMag.setVisible(true);


        chooseWindow.add(warrior);
        chooseWindow.add(mage);
        chooseWindow.add(warlock);
        chooseWindow.add(iconWar);
        chooseWindow.add(iconWarl);
        chooseWindow.add(iconMag);
        chooseWindow.add(name);
        chooseWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chooseWindow.add(nameEnter);
        chooseWindow.add(logo);
        chooseWindow.setVisible(true);
        chooseWindow.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_ESCAPE){
                    System.exit(1);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


    }


    JFrame window = new JFrame();


    public void initWindow() {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/sea.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
        window.setSize(900, 700);
        window.pack();
        window.setLocationRelativeTo(null);

        window.setVisible(true);
        //window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        window.getMaximizedBounds();

        window.setTitle("Ayanot wars");
        window.toFront();




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
            window.setUndecorated(true);
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

