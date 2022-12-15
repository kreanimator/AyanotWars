package com.Interfaces;

import java.awt.*;
import com.Units.*;
import com.CreateMap;


import javax.swing.*;


public class GameInterface {
    int x, y;
    private static Point pos=null;

        public GameInterface() {

            int x = CreateMap.xOffset-20;
            int y = CreateMap.yOffset+CreateMap.HEIGHT-CreateMap.TILE_SIZE;

            pos = new Point(x, y);
        }
    public void draw(Graphics g) {

        g.drawRect(
                (pos.x * CreateMap.TILE_SIZE)+CreateMap.xOffset,
                (pos.y * CreateMap.TILE_SIZE)+CreateMap.yOffset,CreateMap.WIDTH,CreateMap.TILE_SIZE);
    }

        public static void drawActionPanel(Graphics g) {


            // set the text to be displayed
            String text = "EXP: ";
            String textLvl = "Level " + Player.getLevel();
            String hplvl = "HP: ";
            String inv = "For inventory press 'i'";

            // we need to cast the Graphics to Graphics2D to draw nicer text
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255,212,113));
            g2.fillRect(pos.x, pos.y, CreateMap.WIDTH, CreateMap.TILE_SIZE*2);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(
                    RenderingHints.KEY_FRACTIONALMETRICS,
                    RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            // set the text color and font
            g2d.setColor(java.awt.Color.BLACK);
            g2d.setFont(new Font(Font.SERIF, Font.BOLD, 25));
            // draw the score in the bottom center of the screen
            // https://stackoverflow.com/a/27740330/4655368
            FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
            // the text will be contained within this rectangle.
            // here I've sized it to be the entire bottom row of board tiles
//            Rectangle rect = new Rectangle(CreateMap.xOffset, CreateMap.TILE_SIZE * (CreateMap.ROWS - 1),
//                    CreateMap.TILE_SIZE * CreateMap.COLUMNS, CreateMap.TILE_SIZE);


            // draw the string
            g2d.drawString(text, pos.x + (CreateMap.WIDTH/2)-30, pos.y + CreateMap.TILE_SIZE/2);

            // determine the y coordinate for the text
            // (note we add the ascent, as in java 2d 0 is top of the screen)
            g2d.drawString(textLvl, pos.x + CreateMap.WIDTH/3, (pos.y + CreateMap.TILE_SIZE/2));
            // determine the y coordinate for the text
            // (note we add the ascent, as in java 2d 0 is top of the screen)
            g2d.drawString(hplvl, pos.x, (pos.y+ CreateMap.TILE_SIZE/2) + 2);

            //Drawing a frame for HP bar.
            g2.drawLine(pos.x + 50, pos.y +9, pos.x + 150, pos.y +9);
            g2.drawLine(pos.x + 50, pos.y+ 30, pos.x + 150, pos.y+ 30);
            g2.drawLine(pos.x + 49, pos.y + 9 , pos.x + 49, pos.y + 30);
            g2.drawLine(pos.x + 150, pos.y + 9, pos.x + 150, pos.y + 30);
            //Drawing a frame for EXP bar.

            g2.drawLine(pos.x + (CreateMap.WIDTH/2 + 50), pos.y +9, pos.x + (CreateMap.WIDTH/2 + 150), pos.y +9);
            g2.drawLine(pos.x + (CreateMap.WIDTH/2 + 50), pos.y+ 30, pos.x + (CreateMap.WIDTH/2 + 150), pos.y+ 30);
            g2.drawLine(pos.x + (CreateMap.WIDTH/2 + 49), pos.y + 9 , pos.x + (CreateMap.WIDTH/2 + 49), pos.y + 30);
            g2.drawLine(pos.x + (CreateMap.WIDTH/2 + 150), pos.y + 9, pos.x + (CreateMap.WIDTH/2 + 150), pos.y + 30);
            g2d.drawString(inv, pos.x + (CreateMap.WIDTH-(CreateMap.WIDTH/3)), pos.y + CreateMap.TILE_SIZE/2);
            g2.setColor(java.awt.Color.RED);
            g2.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(
                    RenderingHints.KEY_FRACTIONALMETRICS,
                    RenderingHints.VALUE_FRACTIONALMETRICS_ON);

            int widthhp = Player.getHP();
            g2.fillRect(pos.x + 50, pos.y +10, widthhp, 20);

            g2.setColor(Color.GREEN);
            g2.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(
                    RenderingHints.KEY_FRACTIONALMETRICS,
                    RenderingHints.VALUE_FRACTIONALMETRICS_ON);

            int width = Player.getExperience() / 10;
            g2.fillRect((pos.x + CreateMap.WIDTH/2) +50, pos.y +10, width, 20);
        }
    public void tick() {


        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= CreateMap.WIDTH) {
            pos.x = (CreateMap.WIDTH);
        }
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= CreateMap.HEIGHT) {
            pos.y = (CreateMap.HEIGHT);
        }

    }

}




