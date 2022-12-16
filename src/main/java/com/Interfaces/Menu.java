package com.Interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Menu implements ActionListener {

    JFrame window;
    JButton startButton;
    JLabel logo;


    Font myFont = new Font(Font.SERIF, Font.BOLD, 30);

    public Menu() {


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
        startButton.setFont(myFont);
        startButton.setBounds(417, 268, 150, 60);
        startButton.addActionListener(this);
        startButton.setFocusable(false);
        startButton.setBackground(new Color(255,212,133));

        window.add(startButton);
        window.setVisible(true);
        window.add(logo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            new PlayerChoose();
            window.setVisible(false);

        }
    }
}

