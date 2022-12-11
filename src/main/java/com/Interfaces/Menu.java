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


    Font myFont = new Font("TimesRoman", Font.BOLD, 30);

    public Menu() {


        window = new JFrame("Ayanot Wars");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(900, 900);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setBackground(Color.WHITE);


        logo = new JLabel();
        logo.setBounds(250, 30, 400, 100);
        logo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/logo.png"))));


        startButton = new JButton("Start");
        startButton.setFont(myFont);
        startButton.setBounds(350, 425, 150, 60);
        startButton.addActionListener(this);
        startButton.setFocusable(false);


        window.add(logo);
        window.add(startButton);
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            new PlayerChoose();
            window.setVisible(false);

        }
    }
}

