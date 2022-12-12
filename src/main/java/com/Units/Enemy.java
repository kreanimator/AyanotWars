package com.Units;

import com.CreateMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Enemy extends Unit {

    private BufferedImage image;

    private final Point pos;
    private int hp = 45;
    int height = 50;
    int width = 50;

    String u = "src/main/resources/images/enemies/enemyup.png";
    String d = "src/main/resources/images/enemies/enemydown.png";
    String l = "src/main/resources/images/enemies/enemyleft.png";
    String r = "src/main/resources/images/enemies/enemyright.png";

    public Enemy(int x, int y,int height, int width) {

        loadImage();
        pos = new Point(x, y);
        this.height = height;
        this.width = width;
    }
    public Rectangle getBounds() {
        return new Rectangle(pos.x, pos.y, width, height);
    }


    public void getDamage(int value) {

        this.hp -= value;
    }
    public void getCurrentHP(){
        System.out.println(hp);
    }

    private void loadImage() {
        try {
            File enemyImageFile = new File("src/main/resources/images/enemies/enemydown.png");
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

    public void move(int[][] obstacles) {

        int dx = (int) Math.floor(Math.random() * (1 + 1 + 1) - 1);
        int dy = (int) Math.floor(Math.random() * (1 + 1 + 1) - 1);
        try {
            if (obstacles[pos.x + dx][pos.y + dy] == 0) {
                pos.translate(dx, dy);
            }
        } catch (Exception ignored) {
        }
    }

    public boolean isAlive() {
        return hp > 0;
    }


    public boolean isKilled() {
        return hp <= 0;
    }

    public String getHP() {
        return String.valueOf(hp);
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
        ArrayList<Enemy> enemies;
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
        int x = pos.x * CreateMap.TILE_SIZE;
        int width = hp;
        g2.fillRect(x, pos.y * CreateMap.TILE_SIZE - 10, width, 5);
    }

    public Point getPos() {
        return pos;
    }

}
