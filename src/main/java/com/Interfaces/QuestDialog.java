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

import static java.awt.Font.BOLD;

public class QuestDialog implements ActionListener {

    JDialog questDialog;
    JTextArea area = new JTextArea();
    JButton confirm, decline;
    JLabel back;

    public QuestDialog(){
        Font myFont = null;
        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("src/main/resources/font/GravityBold8.ttf"));

            myFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);


        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        back = new JLabel();
        confirm = new JButton("Ok, I think I can deal with it.");
        confirm.setFocusable(false);
        confirm.setBounds(0,200,500,50);
        confirm.setVisible(true);
        confirm.setBackground(new Color(255,212,113));
        confirm.addActionListener(this);
        assert myFont != null;
        confirm.setFont(myFont.deriveFont(BOLD,8f));
        decline = new JButton("Fuck off sick bustard!");
        decline.setFocusable(false);
        decline.setBounds(0,250,500,50);
        decline.setVisible(true);
        decline.setBackground(new Color(255,212,113));
        decline.setFont(myFont.deriveFont(BOLD,8f));
        decline.addActionListener(this);


        questDialog = new JDialog();

        questDialog.setSize(500, 400);
        questDialog.setLocationRelativeTo(null);
        questDialog.setVisible(true);
        questDialog.setTitle("Nick the courageous");
        area.setBackground(new Color(255,212,113));
        area.setEditable(false);

        area.setText("              Well stranger! I see you didn't solve all tasks\n             I've gave you..." +
                "Ok lazy bastard, as you ve might\n " +
                "             seen, there are a lot of goblins are sneaking\n " +
                "              around,bring me their heads and I'll treat you\n              like a king!");
        assert myFont!= null;
        area.setFont(myFont.deriveFont(BOLD,10f));
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
