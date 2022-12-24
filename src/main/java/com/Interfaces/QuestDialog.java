package com.Interfaces;

import com.Events.Quest;

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

    public QuestDialog() {
        Font myFont = null;
        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("src/main/resources/font/GravityBold8.ttf"));

            myFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);


        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        questDialog = new JDialog();
        questDialog.setSize(500, 400);
        questDialog.setLocationRelativeTo(null);
        questDialog.setUndecorated(true);
        questDialog.setVisible(true);
        questDialog.setForeground(new Color(255, 255, 255, 255));
        //questDialog.setTitle("Nick the courageous");

        questDialog.setBackground(new Color(0,0,0,100));
        back = new JLabel();
        confirm = new JButton("Ok, I think I can deal with it.");
        confirm.setFocusable(false);
        confirm.setBounds(0,200,500,50);
        confirm.setVisible(true);
        confirm.setBackground(new Color(0,0,0,0));
        confirm.addActionListener(this);
        confirm.setForeground(new Color(255, 255, 255, 255));
        assert myFont != null;
        confirm.setFont(myFont.deriveFont(BOLD,10f));
        decline = new JButton("Fuck off sick bustard!");
        decline.setFocusable(false);
        decline.setBounds(0,250,500,50);
        decline.setVisible(true);
        decline.setBackground(new Color(0,0,0,0));
        decline.setFont(myFont.deriveFont(BOLD,10f));
        decline.addActionListener(this);
        decline.setForeground(new Color(255, 255, 255, 255));




;
        area.setBackground(new Color(0,0,0,100));
        area.setEditable(false);
        area.setForeground(new Color(255, 255, 255, 255));

        area.setText("              Well stranger! I see you didn't solve all tasks\n\n             I've gave you..." +
                "Ok lazy bastard, as you ve might\n\n " +
                "             seen, there are a lot of goblins are sneaking\n\n " +
                "              around,bring me their heads and I'll treat you\n\n              like a king!" );
        assert myFont!= null;
        area.setFont(myFont.deriveFont(BOLD,10f));
        back.setBounds(0, 0, 50, 50);
        back.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/npc.png"))));
        questDialog.add(confirm);
        questDialog.add(decline);
        questDialog.add(back);
        questDialog.add(area);



    }
    public static void draw(Graphics g) {



//        g.drawImage(gh, (pos.x * CreateMap.TILE_SIZE) + CreateMap.xOffset,
//                (pos.y * CreateMap.TILE_SIZE) + CreateMap.yOffset, observer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==confirm){
            questDialog.setVisible(false);
            Quest quest = new Quest();
            quest.isTaken();
        }
        if(e.getSource()==decline){
            questDialog.setVisible(false);
        }
    }
    }


