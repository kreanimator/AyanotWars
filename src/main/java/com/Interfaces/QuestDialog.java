package com.Interfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static java.awt.Font.BOLD;

public class QuestDialog implements ActionListener {

    JDialog questDialog;
    JTextArea area = new JTextArea();
    JButton confirm, decline;
    Font font = new Font(Font.SERIF,BOLD,15);
    JLabel back;

    public QuestDialog(){
        back = new JLabel();
        confirm = new JButton("Ok, I think I can deal with it.");
        confirm.setFocusable(false);
        confirm.setBounds(0,200,500,50);
        confirm.setVisible(true);
        confirm.setBackground(new Color(255,212,113));
        confirm.addActionListener(this);
        decline = new JButton("Fuck off sick bustard!");
        decline.setFocusable(false);
        decline.setBounds(0,250,500,50);
        decline.setVisible(true);
        decline.setBackground(new Color(255,212,113));
        decline.addActionListener(this);


        questDialog = new JDialog();

        questDialog.setSize(500, 400);
        questDialog.setLocationRelativeTo(null);
        questDialog.setVisible(true);
        questDialog.setTitle("Nick the courageous");
        area.setBackground(new Color(255,212,113));
        area.setEditable(false);

        area.setText("              Well stranger! I see you didn't solve all tasks I've gave you...\n              Ok lazy bastard, as you ve might seen, there are a lot of goblins\n        are sneaking around, bring me their heads and I'll treat you\n like a king!");
        area.setFont(font);
        back.setBounds(0, 0, 50, 50);
        back.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/npc.png"))));
        questDialog.add(confirm);
        questDialog.add(decline);
        questDialog.add(back);
        questDialog.add(area);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirm){
            questDialog.dispose();
        }
        if(e.getSource()==decline){
            questDialog.dispose();
        }
    }
}
