package com.Units;

import com.CreateMap;
import com.Interfaces.Inventory;
import com.Interfaces.PlayerChoose;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Player {

    private static String nameClass = "";
    private BufferedImage image;
    private final Point pos;
    private final static int FORWARD = 0;
    private final static int BACKWARD = 1;
    private final static int LEFT = 2;
    private final static int RIGHT = 3;
    int height = 50;
    int width = 50;

    public static String getNameClass() {
        return nameClass;
    }

    static Random rd = new Random();

    //    static int x = rd.nextInt(CreateMap.ROWS);
//    static int y = rd.nextInt(CreateMap.COLUMNS)
    static int x = CreateMap.ROWS/2;
    static int y = CreateMap.COLUMNS/4;


    private static int experience;
    public static int hp = 100;
    private static int level = 1;
    private static String playerClass = "";
    public int damage = 15;
    private int attackRange;
    private int facingDirection;
    ArrayList<Enemy> enemies;


    public static void setPlayerClass(String playerClass) {

        if (Objects.equals(playerClass, "warrior")) {
            Player.playerClass = "src/main/resources/images/player/" + playerClass + ".png";
            Player.nameClass = "warrior.png";
            //Warrior warrior = new Warrior();
        } else if (Objects.equals(playerClass, "warlock")) {
            Player.playerClass = "src/main/resources/images/player/" + playerClass + ".png";
            Player.nameClass = "warlock.png";
            // Warlock warlock = new Warlock();
        } else if (Objects.equals(playerClass, "mage")) {
            Player.playerClass = "src/main/resources/images/player/" + playerClass + ".png";
            Player.nameClass = "mage.png";
            //Mage mage = new Mage();
        }
    }
    public void setPlayerName(Graphics g) {
        // set the text to be displayed

        String name = PlayerChoose.setPlayerName();

        // we need to cast the Graphics to Graphics2D to draw nicer text
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setColor(java.awt.Color.BLACK);
        g2d.setFont(new Font(Font.SERIF, Font.BOLD, 12));
        int x = (pos.x * CreateMap.TILE_SIZE);

        int y = (pos.y * CreateMap.TILE_SIZE - 15);
        g2d.drawString(name, x, y);
    }


    public int getDamage() {
        return damage;

    }

    public Rectangle getBounds() {
        return new Rectangle(pos.x, pos.y, width, height);
    }

    public Player(int width, int height) {

        this.width=width;
        this.height=height;

//        this.x = (CreateMap.ROWS/2) -(width/2);
//        this.y = (CreateMap.COLUMNS/2) -(height/2);


        attackRange = 1;
        loadImage();
        pos = new Point(x , y);
        //pos = new Point(CreateMap.xOffset, CreateMap.yOffset);
        experience = 0;
    }
    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }


    private void loadImage() {
        try {

            File playerImgFile = new File(playerClass);
            image = ImageIO.read(playerImgFile);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {

        g.drawImage(image, (pos.x * CreateMap.TILE_SIZE)+CreateMap.xOffset,
                (pos.y * CreateMap.TILE_SIZE)+CreateMap.yOffset, observer);
    }

    static JDialog inventoryWindow = new JDialog();

    public void initWindow() {

        inventoryWindow.setSize(Inventory.ROWS * Inventory.TILE_SIZE + 14, Inventory.COLUMNS * Inventory.TILE_SIZE + 35);
        inventoryWindow.setLocationRelativeTo(null);
        inventoryWindow.setVisible(true);
        inventoryWindow.setResizable(false);
        inventoryWindow.setTitle("Inventory");
    }

    public int[][] keyPressed(KeyEvent e, int[][] obstacles, ArrayList<Enemy> enemies, ArrayList<Boss> bosses) {

        int key = e.getKeyCode();

        //TODO: Realise turn when you're standing in front of obstacle.
        try {
            if (key == KeyEvent.VK_UP && obstacles[pos.x][pos.y - 1] != 2) {
                facingDirection = FORWARD;

                File playerImgFileUp = new File("src/main/resources/images/player/up/" + nameClass);
                image = ImageIO.read(playerImgFileUp);
                CreateMap.yOffset +=CreateMap.TILE_SIZE;
                pos.translate(0, -1);
                for (Enemy enemy : enemies) {
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 0;
                    enemy.move(obstacles);
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 2;
                }
                for (Boss boss : bosses) {
                    obstacles[boss.getPos().x][boss.getPos().y] = 0;
                    boss.move(obstacles);
                    obstacles[boss.getPos().x][boss.getPos().y] = 2;
                }

                obstacles[pos.x][pos.y + 1] = 0;
                obstacles[pos.x][pos.y] = 2;
            }else if (key == KeyEvent.VK_UP && obstacles[pos.x][pos.y - 1] == 2){
                facingDirection = FORWARD;
                File playerImgFileUp = new File("src/main/resources/images/player/up/" + nameClass);
                image = ImageIO.read(playerImgFileUp);
            }
        } catch (Exception ignored) {

        }
        try {
            if (key == KeyEvent.VK_RIGHT && obstacles[pos.x + 1][pos.y] != 2) {
                facingDirection = RIGHT;
                CreateMap.xOffset -=CreateMap.TILE_SIZE;
                File playerImageFileRight = new File("src/main/resources/images/player/right/" + nameClass);
                image = ImageIO.read(playerImageFileRight);
                pos.translate(1, 0);


                for (Enemy enemy : enemies) {
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 0;
                    enemy.move(obstacles);
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 2;
                }
                for (Boss boss : bosses) {
                    obstacles[boss.getPos().x][boss.getPos().y] = 0;
                    boss.move(obstacles);
                    obstacles[boss.getPos().x][boss.getPos().y] = 2;
                }
                obstacles[pos.x - 1][pos.y] = 0;
                obstacles[pos.x][pos.y] = 2;
            }else  if (key == KeyEvent.VK_RIGHT && obstacles[pos.x + 1][pos.y] == 2) {
                facingDirection = RIGHT;
                File playerImageFileRight = new File("src/main/resources/images/player/right/" + nameClass);
                image = ImageIO.read(playerImageFileRight);
            }
        } catch (Exception ignored) {

        }
        try {
            if (key == KeyEvent.VK_DOWN && obstacles[pos.x][pos.y + 1] != 2) {
                facingDirection = BACKWARD;

                CreateMap.yOffset -= CreateMap.TILE_SIZE;
                File playerImageFileDown = new File("src/main/resources/images/player/down/" + nameClass);
                image = ImageIO.read(playerImageFileDown);
                pos.translate(0, 1);
                for (Enemy enemy : enemies) {
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 0;
                    enemy.move(obstacles);
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 2;
                }
                for (Boss boss : bosses) {
                    obstacles[boss.getPos().x][boss.getPos().y] = 0;
                    boss.move(obstacles);
                    obstacles[boss.getPos().x][boss.getPos().y] = 2;
                }
                obstacles[pos.x][pos.y - 1] = 0;
                obstacles[pos.x][pos.y] = 2;
            }else if (key == KeyEvent.VK_DOWN && obstacles[pos.x][pos.y + 1] == 2) {
                facingDirection = BACKWARD;
                File playerImageFileDown = new File("src/main/resources/images/player/down/" + nameClass);
                image = ImageIO.read(playerImageFileDown);
            }
        } catch (Exception ignored) {

        }
        try {
            if (key == KeyEvent.VK_LEFT && obstacles[pos.x - 1][pos.y] != 2) {
                facingDirection = LEFT;
                CreateMap.xOffset +=CreateMap.TILE_SIZE;
                pos.translate(-1, 0);
                File playerImageFileLeft = new File("src/main/resources/images/player/left/" + nameClass);
                image = ImageIO.read(playerImageFileLeft);
                for (Enemy enemy : enemies) {
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 0;
                    enemy.move(obstacles);
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 2;
                }
                for (Boss boss : bosses) {
                    obstacles[boss.getPos().x][boss.getPos().y] = 0;
                    boss.move(obstacles);
                    obstacles[boss.getPos().x][boss.getPos().y] = 2;
                }
                obstacles[pos.x + 1][pos.y] = 0;
                obstacles[pos.x][pos.y] = 2;
            }else if (key == KeyEvent.VK_LEFT && obstacles[pos.x - 1][pos.y] == 2) {
                facingDirection = LEFT;
                File playerImageFileLeft = new File("src/main/resources/images/player/left/" + nameClass);
                image = ImageIO.read(playerImageFileLeft);
            }
        } catch (Exception ignored) {

        }
        try {
            if (key == KeyEvent.VK_I) {
                Inventory inventory = new Inventory();
                inventoryWindow.add(inventory);
                inventoryWindow.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        int key = e.getKeyCode();
                        try {
                            if (key == KeyEvent.VK_I) {
                                inventoryWindow.dispose();
                            }
                        } catch (Exception ignored) {
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                });
                SwingUtilities.invokeLater(this::initWindow);
            }
        } catch (Exception ignored) {

        }
        try {
            if (key == KeyEvent.VK_SPACE) {

                for (Enemy enemy : enemies) {
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 0;
                    enemy.move(obstacles);
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 2;
                    if (facingDirection == FORWARD && pos.x == enemy.getPos().x && pos.y == enemy.getPos().y + 1) {

                        System.out.println("Attack up");
                        enemy.getDamage(playerDamage());
                        enemy.getCurrentHP();
                        File playerAttackImgFileUp = new File("src/main/resources/images/player/attack/up/" + nameClass);
                        image = ImageIO.read(playerAttackImgFileUp);

                    }

                    if (facingDirection == BACKWARD && pos.x == enemy.getPos().x && pos.y == enemy.getPos().y - 1) {

                        System.out.println("Attack down");
                        enemy.getDamage(playerDamage());
                        enemy.getCurrentHP();
                        File playerAttackImgFileDown = new File("src/main/resources/images/player/attack/down/" + nameClass);
                        image = ImageIO.read(playerAttackImgFileDown);


                    }

                    if (facingDirection == LEFT && pos.x == enemy.getPos().x + 1 && pos.y == enemy.getPos().y) {

                        System.out.println("Attack Left");
                        enemy.getDamage(playerDamage());
                        enemy.getCurrentHP();
                        File playerAttackImgFileLeft = new File("src/main/resources/images/player/attack/left/" + nameClass);
                        image = ImageIO.read(playerAttackImgFileLeft);
                    }

                    if (facingDirection == RIGHT && pos.x == enemy.getPos().x - 1 && pos.y == enemy.getPos().y) {

                        System.out.println("Attack Right");
                        enemy.getDamage(playerDamage());
                        enemy.getCurrentHP();
                        File playerAttackImgFileRight = new File("src/main/resources/images/player/attack/right/" + nameClass);
                        image = ImageIO.read(playerAttackImgFileRight);

                    }
                }
                for (Boss boss : bosses) {
                    obstacles[boss.getPos().x][boss.getPos().y] = 0;
                    boss.move(obstacles);
                    obstacles[boss.getPos().x][boss.getPos().y] = 2;
                    if (facingDirection == FORWARD && pos.x == boss.getPos().x && pos.y == boss.getPos().y + 1) {

                        System.out.println("Attack up");
                        boss.getDamage(playerDamage());
                        boss.getCurrentHP();
                        File playerAttackImgFileDown = new File("src/main/resources/images/player/attack/up/" + nameClass);
                        image = ImageIO.read(playerAttackImgFileDown);

                    }
                    if (facingDirection == BACKWARD && pos.x == boss.getPos().x && pos.y == boss.getPos().y - 1) {

                        System.out.println("Attack down");

                        boss.getDamage(playerDamage());
                        boss.getCurrentHP();
                        File playerAttackImgFileDown = new File("src/main/resources/images/player/attack/down/" + nameClass);
                        image = ImageIO.read(playerAttackImgFileDown);
                    }
                    if (facingDirection == LEFT && pos.x == boss.getPos().x + 1 && pos.y == boss.getPos().y) {

                        System.out.println("Attack Left");
                        boss.getDamage(playerDamage());
                        boss.getCurrentHP();
                        File playerAttackImgFileLeft = new File("src/main/resources/images/player/attack/left/" + nameClass);
                        image = ImageIO.read(playerAttackImgFileLeft);
                    }
                    if (facingDirection == RIGHT && pos.x == boss.getPos().x - 1 && pos.y == boss.getPos().y) {

                        System.out.println("Attack Right");
                        boss.getDamage(playerDamage());
                        boss.getCurrentHP();
                        File playerAttackImgFileRight = new File("src/main/resources/images/player/attack/right/" + nameClass);
                        image = ImageIO.read(playerAttackImgFileRight);
                    }
                }
            }
        } catch (Exception ignored) {

        }

        return obstacles;
    }



    public void attack() { //TODO: Attack method
        for (Enemy enemy : enemies) {
            enemy.getDamage(playerDamage());
        }
    }
    public int playerDamage(){
        return damage;
    }


    public void tick() {

        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= CreateMap.COLUMNS ) {
            pos.x = (CreateMap.COLUMNS - 1);

        }
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= CreateMap.ROWS ) {
            pos.y = (CreateMap.ROWS - 1);

        }
    }

    public static int getExperience() {
        return experience;
    }

    public static String getLevel() {
        return String.valueOf(level);
    }

    public static int getHP() {
        return hp;
    }

    public void addExperience(int amount) {
        experience += amount;
    }

    public void addLevel(int amount) {
        if (experience == 1000) {
            level += amount;
            experience = 0;
            hp = (int) (hp * 1.2);
            damage = (int) (damage *1.2);

        }
    }

    public Point getPos() {
        return pos;
    }


}

