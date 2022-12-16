package com.Units;

import com.CreateMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Enemy extends Unit {

    private BufferedImage image;

    private final Point pos;
    private int hp = 45;
    int height;
    int width;
    private final static int FORWARD = 0;
    private final static int BACKWARD = 1;
    private final static int LEFT = 2;
    private final static int RIGHT = 3;
    private int facingDirection;
    ArrayList <Enemy> enemies;



    public Enemy(int x, int y,int height, int width) {
        this.height = height;
        this.width = width;
        loadImage();
        pos = new Point(x, y);

    }
    public static ArrayList<Enemy> populateEnemies() {
        ArrayList<Enemy> enemyList = new ArrayList<>();
        Random rand = new Random();

        // create the given number of enemies in random positions on the board.
        // note that there is not check here to prevent two coins from occupying the same
        // spot, nor to prevent coins from spawning in the same spot as the player
        for (int i = 0; i < CreateMap.NUM_ENEMIES; ) {
            int enemyX = rand.nextInt(CreateMap.COLUMNS);
            int enemyY = rand.nextInt(CreateMap.ROWS);
            if (CreateMap.MAS_MAP[enemyX][enemyY] == 0) {
                CreateMap.MAS_MAP[enemyX][enemyY] = 1;
                enemyList.add(new Enemy(enemyX, enemyY, 50, 50));
                i++;
            }
        }
        return enemyList;
    }
    public void attackEnemies() {
        for (Enemy enemy : enemies) {
            if(enemy.pos.x == enemy.getPos().x && enemy.pos.y == enemy.getPos().y + 1){
                facingDirection=FORWARD;
                enemy.getDamage(5);
                System.out.println("Enemy was hitted");
                getCurrentHP();
            }
            if (enemy.pos.x == enemy.getPos().x - 1 && enemy.pos.y == enemy.getPos().y){
                facingDirection=RIGHT;
                enemy.getDamage(5);
                System.out.println("Enemy was hitted");
                getCurrentHP();
            }
            if(enemy.pos.x == enemy.getPos().x && enemy.pos.y == enemy.getPos().y - 1){
                facingDirection = BACKWARD;
                enemy.getDamage(5);
                System.out.println("Enemy was hitted");
                getCurrentHP();
            }
            if(enemy.pos.x == enemy.getPos().x + 1 && enemy.pos.y == enemy.getPos().y){
                facingDirection=LEFT;
                enemy.getDamage(5);
                System.out.println("Enemy was hitted");
                getCurrentHP();
            }
//            if (obstacles[pos.x][pos.y - 1] != 2) {
//                facingDirection = FORWARD;
//                if (pos.x == enemy.getPos().x && pos.y == enemy.getPos().y + 1){
//                    enemy.getDamage(5);
//                System.out.println("Enemy was hitted");
//                getCurrentHP();
//            }
//            }
//            if (obstacles[pos.x + 1][pos.y] == 2) {
//                facingDirection = RIGHT;
//                if ( pos.x == enemy.getPos().x - 1 && pos.y == enemy.getPos().y) {
//                    enemy.getDamage(5);
//                    System.out.println("Enemy was hitted");
//                    getCurrentHP();
//                }
//            }
//            if (obstacles[pos.x][pos.y + 1] == 2) {
//                facingDirection = BACKWARD;
//                if ( pos.x == enemy.getPos().x && pos.y == enemy.getPos().y - 1) {
//                    enemy.getDamage(5);
//                    System.out.println("Enemy was hitted");
//                    getCurrentHP();
//                }
//            }
//            if (obstacles[pos.x - 1][pos.y] == 2) {
//                facingDirection = LEFT;
//                if (pos.x == enemy.getPos().x + 1 && pos.y == enemy.getPos().y) {
//                    enemy.getDamage(5);
//                    Player.getDamage(5);
//                    System.out.println("Enemy was hitted");
//                    getCurrentHP();
//                }
//            }
        }
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
            Random rand = new Random();
            int randomNum = rand.nextInt((4 - 1) + 1) + 1;
            File enemyImageFile = new File("src/main/resources/images/enemies/"+randomNum+".png");
            image = ImageIO.read(enemyImageFile);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void tick() {


        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= CreateMap.COLUMNS) {
            pos.x = (CreateMap.COLUMNS - 1) + CreateMap.xOffset;
        }
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= CreateMap.ROWS) {
            pos.y = (CreateMap.ROWS + 1) + CreateMap.yOffset;
        }

    }

    public void move(int[][] obstacles) {

        int dx = (int) Math.floor(Math.random() * (1 + 1 + 1) - 1);
        int dy = (int) Math.floor(Math.random() * (1 + 1 + 1) - 1);
        try {
            if (obstacles[pos.x + dx][pos.y + dy] == 0) {
                pos.translate(dx, dy);
                //attackEnemies();
                try {
                    Random rand = new Random();
                    int randomNum = rand.nextInt((4 - 1) + 1) + 1;
                    File enemyImageFile = new File("src/main/resources/images/enemies/"+randomNum+".png");
                    image = ImageIO.read(enemyImageFile);
                } catch (IOException exc) {
                    System.out.println("Error opening image file: " + exc.getMessage());
                }
            }
        } catch (Exception ignored) {
        }
    }
    public void removeObstacles(int [][] obstacles){
        obstacles[pos.x][pos.y] = 0;
    }

    public boolean isAlive() {
        return hp > 0;
    }
    public boolean isKilled() {
        return hp <= 0;
    }
    public void draw(Graphics g, ImageObserver observer) {

        g.drawImage(
                image,
                (pos.x * CreateMap.TILE_SIZE)+CreateMap.xOffset,
                (pos.y * CreateMap.TILE_SIZE)+CreateMap.yOffset,
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
        int x = (pos.x * CreateMap.TILE_SIZE)+CreateMap.xOffset;
        int width = hp;
        g2.fillRect(x, (pos.y * CreateMap.TILE_SIZE - 10)+CreateMap.yOffset, width, 5);
    }
    public Point getPos() {
        return pos;
    }

}
