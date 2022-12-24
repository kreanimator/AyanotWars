package com.Interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class Menu implements ActionListener {

    JFrame window;
    JButton startButton;
    JLabel logo;


    Font myFont = new Font(Font.SERIF, Font.BOLD, 30);

    public Menu() {

        Font myFont = null;
        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("src/main/resources/font/GravityBold8.ttf"));

            myFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);


        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }


        window = new JFrame();
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(1024, 576);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setBackground(Color.WHITE);
        window.setTitle("Ayanot wars");


        logo = new JLabel();
        logo.setBounds(0, 0, 1024, 576);
        logo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/background.gif"))));


        startButton = new JButton("Start");
        assert myFont != null;
        startButton.setFont(myFont.deriveFont(Font.BOLD,25f));
        startButton.setBounds(417, 268, 150, 60);
        startButton.addActionListener(this);
        startButton.setFocusable(false);
        startButton.setBackground(new Color(255,212,133,100));

        window.add(startButton);
        window.setUndecorated(true);
        window.setVisible(true);
        window.add(logo);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            window.getContentPane().removeAll();
            window.add(new PlayerChoose());
            window.dispose();


        }
    }
}

