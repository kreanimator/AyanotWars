package com;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class Boss {

    private BufferedImage image;

    private final Point pos;

    public Boss(int x, int y) {

        loadImage();


        pos = new Point(x, y);
    }

    private void loadImage() {
        try {

            File enemyImageFile = new File("src/main/resources/images/boss.png");
            System.out.println("File IO is OK");
            image = ImageIO.read(enemyImageFile);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }
    public void tick() {

        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= CreateMap.COLUMNS) {
            pos.x = CreateMap.COLUMNS - 1;

        }
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= CreateMap.ROWS) {
            pos.y = CreateMap.ROWS - 1;
        }
    }
    public void move (int [][] obstacles){

        int dx = (int)Math.floor(Math.random() * ( 1 + 1 + 1) -1);
        int dy = (int)Math.floor(Math.random() * ( 1 + 1 + 1) -1);
        try {
            if (obstacles [pos.x][pos.y]-1 != 2){
                pos.translate(dx, dy);
            }
        } catch (Exception ignored) {
        }
    }

    private static int direction() {
        return new Random().nextInt(2);
    }
    public void draw(Graphics g, ImageObserver observer) {

        g.drawImage(
                image,
                pos.x * CreateMap.TILE_SIZE,
                pos.y * CreateMap.TILE_SIZE,
                observer
        );
    }
    public void drawHealthBar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
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
        g2.fillRect(pos.x * CreateMap.TILE_SIZE, pos.y * CreateMap.TILE_SIZE-10, 50, 5);
    }
    public Point getPos() {
        return pos;
    }

}