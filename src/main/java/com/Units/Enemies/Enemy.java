package com.Units.Enemies;

import com.CreateMap;
import com.Units.Player;
import com.Units.Unit;

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
    public static final int NUM_ENEMIES = ((CreateMap.ROWS * CreateMap.COLUMNS) / 30);

    private static int quantityKilled;
    static ArrayList<Enemy> enemyList ;

    //Enemy initialisation.
    public Enemy(int x, int y, int height, int width) {
        this.height = height;
        this.width = width;

        loadImage();
        pos = new Point(x, y);
    }

    public static ArrayList<Enemy> populateEnemies() {
        enemyList = new ArrayList<>();
        Random rand = new Random();
        // create the given number of enemies in random positions on the board.
        // note that there is not check here to prevent two coins from occupying the same
        // spot, nor to prevent enemies from spawning in the same spot as the player
        for (int i = 0; i < NUM_ENEMIES; ) {
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
    //Algorithm for enemy chasing player
//    public void chase(Player player) {
//
//        if (pos.y >= player.getPos().y + 1 && pos.y >= enemy.getPos().y + 1) {
//            facingDirection = FORWARD;
//            enemy.pos.x += (enemy.pos.y - player.getPos().y) * 0.25;
//        }
//        if (pos.y <= player.getPos().y - 1 && pos.y >= enemy.getPos().y - 1) {
//            facingDirection = BACKWARD;
//            enemy.pos.x += (enemy.pos.y - player.getPos().y) * 0.25;
//        }
//        if (pos.x >= player.getPos().x + 1 && pos.y >= enemy.getPos().x + 1) {
//            facingDirection = LEFT;
//            enemy.pos.x += (enemy.pos.x - player.getPos().x) * 0.25;
//        }
//        if (pos.x < player.getPos().x - 1 && pos.y > enemy.getPos().x - 1) {
//            facingDirection = RIGHT;
//            enemy.pos.x += (enemy.pos.x - player.getPos().x) * 0.25;
//        }
//    }
    public static void addQuantityKilled(int amount) {
        quantityKilled += amount;
    }
    public static int getQuantityKilled() {
        return quantityKilled;
    }

    public void chaseEnemies(int[][] obstacles) {
        if (enemyList == null){
            throw new RuntimeException("No enemies");
        }
        Integer minDist=null;
        Enemy nearestEnemy=null;

        for(Enemy enemy: enemyList){
            if (enemy == this) continue;
           int distX = Math.abs(this.pos.x - enemy.pos.x);
            int distY = Math.abs(this.pos.y - enemy.pos.y);
            if (distX >= 10 || distY >= 10) continue;
            int dist = distX + distY;
            if( minDist == null || dist < minDist ){
                minDist=dist;
                nearestEnemy = enemy;
            }
        }
        if (nearestEnemy == null){
            move(CreateMap.MAS_MAP);
            return;
        }
        int directDistX = nearestEnemy.pos.x - this.pos.x;
        int directDistY = nearestEnemy.pos.y - this.pos.y;
        if(directDistY == 0 || Math.abs(directDistX) >= Math.abs(directDistY)){
            pos.translate(directDistX/Math.abs(directDistX),0);
            return;
        }
        pos.translate(0,directDistY/Math.abs(directDistY));

    }

    //Enemies attacking method
    public void attackEnemies() {
//        if (facingDirection == FORWARD && pos.x == enemy.getPos().x && pos.y == enemy.getPos().y + 1) {
//
//            enemy.getDamage(5);
//            System.out.println("Enemy was hitted front");
//            getCurrentHP();
//        }
//        if (facingDirection == RIGHT && pos.x == enemy.getPos().x - 1 && pos.y == enemy.getPos().y) {
//
//            enemy.getDamage(5);
//            System.out.println("Enemy was hitted right");
//            getCurrentHP();
//        }
//        if (facingDirection == BACKWARD && pos.x == enemy.getPos().x && pos.y == enemy.getPos().y - 1) {
//
//            enemy.getDamage(5);
//            System.out.println("Enemy was hitted backw");
//            getCurrentHP();
//        }
//        if (facingDirection == LEFT && pos.x == enemy.getPos().x + 1 && pos.y == enemy.getPos().y) {
//
//            enemy.getDamage(5);
//            System.out.println("Enemy was hitted left");
//            getCurrentHP();
//        }

    }


    //Method for getting bounds for rectangle - rectangle collision. Not realised.
    public Rectangle getBounds() {
        return new Rectangle(pos.x, pos.y, width, height);
    }

    //Receiving damage.
    public void getDamage(int value) {
        this.hp -= value;
    }

    //Current HP status.
    public void getCurrentHP() {
        System.out.println(hp);
    }

    //Method for loading images.
    private void loadImage() {
        try {
            Random rand = new Random();
            int randomNum = rand.nextInt((4 - 1) + 1) + 1;
            File enemyImageFile = new File("src/main/resources/images/enemies/" + randomNum + ".png");
            image = ImageIO.read(enemyImageFile);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    //Prevent enemy for getting out of bounds.
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

    //Move initialisation.
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
                    File enemyImageFile = new File("src/main/resources/images/enemies/" + randomNum + ".png");
                    image = ImageIO.read(enemyImageFile);
                } catch (IOException exc) {
                    System.out.println("Error opening image file: " + exc.getMessage());
                }
            }
        } catch (Exception ignored) {
        }
    }


    //Remove obstacles when enemy die.
    public void removeObstacles(int[][] obstacles) {
        obstacles[pos.x][pos.y] = 0;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public boolean isKilled() {
        return hp <= 0;
    }

    //Java2d graphics rendering
    public void draw(Graphics g, ImageObserver observer) {

        g.drawImage(
                image,
                (pos.x * CreateMap.TILE_SIZE - (CreateMap.TILE_SIZE / 2)) + CreateMap.xOffset,
                (pos.y * CreateMap.TILE_SIZE - (CreateMap.TILE_SIZE / 2)) + CreateMap.yOffset,
                observer
        );
    }
    public void drawHealthBar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(248, 4, 4));
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(
                RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        int x = (pos.x * CreateMap.TILE_SIZE) + CreateMap.xOffset;
        int width = hp;
        g2.fillRect(x - CreateMap.TILE_SIZE / 2, (pos.y * CreateMap.TILE_SIZE - (CreateMap.TILE_SIZE / 2) - 10) + CreateMap.yOffset, width, 5);
    }

    public Point getPos() {
        return pos;
    }


}
