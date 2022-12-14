package com.Interfaces;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;

import com.Units.*;
import com.CreateMap;


import javax.swing.*;


public class GameInterface implements KeyListener {
    int x, y;
    private final Point pos;

        public GameInterface() {

            int x = CreateMap.xOffset;
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
            g2.setColor(Color.getHSBColor(31, 18, 95));
            g2.fillRect(CreateMap.xOffset, CreateMap.yOffset + CreateMap.HEIGHT - CreateMap.TILE_SIZE, CreateMap.WIDTH, CreateMap.TILE_SIZE);
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
            g2d.setFont(new Font("Lato", Font.BOLD, 25));
            // draw the score in the bottom center of the screen
            // https://stackoverflow.com/a/27740330/4655368
            FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
            // the text will be contained within this rectangle.
            // here I've sized it to be the entire bottom row of board tiles
            Rectangle rect = new Rectangle(CreateMap.xOffset, CreateMap.TILE_SIZE * (CreateMap.ROWS - 1),
                    CreateMap.TILE_SIZE * CreateMap.COLUMNS, CreateMap.TILE_SIZE);

            // determine the x coordinate for the text
            int x = (int) (rect.getX() + (rect.getWidth() - metrics.stringWidth(text)) / 2);
            // determine the y coordinate for the text
            // (note we add the ascent, as in java 2d 0 is top of the screen)
            int y = (int) (rect.getY() + ((rect.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent());
            // draw the string
            g2d.drawString(text, x, y);

            int x1 = (int) (rect.getX() + (rect.getWidth() - metrics.stringWidth(text)) / 3);
            // determine the y coordinate for the text
            // (note we add the ascent, as in java 2d 0 is top of the screen)
            g2d.drawString(textLvl, x1, y);
            int x2 = (int) (rect.getX() + (rect.getWidth() - metrics.stringWidth(text)) / 8);
            // determine the y coordinate for the text
            // (note we add the ascent, as in java 2d 0 is top of the screen)
            g2d.drawString(hplvl, x2, y);

            int x3 = (int) (rect.getX() + (rect.getWidth() - metrics.stringWidth(text)) - 250);
            //Drawing a frame for HP bar.
            g2.drawLine(x2 + 50, y + 2, x2 + 150, y + 2);
            g2.drawLine(x2 + 50, y - 19, x2 + 150, y - 19);
            g2.drawLine(x2 + 49, y - 19, x2 + 49, y + 2);
            g2.drawLine(x2 + 150, y - 19, x2 + 150, y + 2);
            //Drawing a frame for EXP bar.
            g2.drawLine(x + 60, y + 2, x + 160, y + 2);
            g2.drawLine(x + 60, y - 19, x + 160, y - 19);
            g2.drawLine(x + 59, y - 19, x + 59, y + 2);
            g2.drawLine(x + 160, y - 19, x + 160, y + 2);
            g2d.drawString(inv, x3, y);
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
            g2.fillRect(x2 + 50, y - 18, widthhp, 20);

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
            g2.fillRect(x + 60, y - 18, width, 20);
        }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // every keyboard get has a certain code. get the value of that code from the
        // keyboard event so that we can compare it to KeyEvent constants
        int key = e.getKeyCode();

        // depending on which arrow key was pressed, we're going to move the player by
        // one whole tile for this input
        if (key == KeyEvent.VK_UP) {
            System.out.println("Moving interface up");
            pos.translate(0, -1);
        }
        if (key == KeyEvent.VK_RIGHT) {
            System.out.println("Moving interface right");
            pos.translate(1, 0);
        }
        if (key == KeyEvent.VK_DOWN) {
            System.out.println("Moving interface down");
            pos.translate(0, 1);
        }
        if (key == KeyEvent.VK_LEFT) {
            System.out.println("Moving interface left");
            pos.translate(-1, 0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
};



