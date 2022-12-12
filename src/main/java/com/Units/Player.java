package com.Units;

import com.CreateMap;
import com.Interfaces.Inventory;
import com.Interfaces.PlayerChoose;
import com.PlayerType.Mage;
import com.PlayerType.Warlock;
import com.PlayerType.Warrior;

import javax.imageio.ImageIO;
import javax.swing.*;
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

public class Player  {

    private static String nameClass ="";
    private BufferedImage image;
    private final Point pos;
    private final static int FORWARD =0;
    private final static int BACKWARD =1;
    private final static int LEFT =2;
    private final static int RIGHT =3;
    int height = 50;
    int width = 50;

    private int experience;
    protected static int hp = 100;
    private int level = 1;
    private static String playerClass = "";
    public int damage;
    private int attackRange;
    private int facingDirection;
    ArrayList <Enemy> enemies;




    public static void setPlayerClass(String playerClass) {

        if (Objects.equals(playerClass, "warrior")) {
            Player.playerClass = "src/main/resources/images/player/"+playerClass+".png";
            Player.nameClass = "warrior.png";
            //Warrior warrior = new Warrior();
        } else if (Objects.equals(playerClass, "warlock")) {
            Player.playerClass = "src/main/resources/images/warlock.png";
            Player.nameClass  = "warlock.png";
           // Warlock warlock = new Warlock();
        } else if (Objects.equals(playerClass, "mage")) {
            Player.playerClass = "src/main/resources/images/mage.png";
            Player.nameClass  = "mage.png";
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
        g2d.setFont(new Font("Lato", Font.BOLD, 12));
        int x = (pos.x * CreateMap.TILE_SIZE);

        int y = (pos.y * CreateMap.TILE_SIZE - 15);
        g2d.drawString(name, x, y);
    }
//    public static int getDamage(int value){
//        hp -= value;
//        return value;
//    }
    public int getDamage(){
        return damage;

    }
    public Rectangle getBounds() {
        return new Rectangle(pos.x, pos.y, width, height);
    }

    public Player(int width,int height) {
        Random rd = new Random();
        int x = rd.nextInt();
        int y = rd.nextInt();
        attackRange = 1;
        loadImage();
        this.height = height;
        this.width = width;
        pos = new Point(x, y);
        experience = 0;
    }


    private void loadImage() {
        try {

            File playerImgFile = new File(playerClass);
            System.out.println("File IO is ok");
            image = ImageIO.read(playerImgFile);
            System.out.println("Image IO is OK");
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {

        g.drawImage(image, pos.x * CreateMap.TILE_SIZE, pos.y * CreateMap.TILE_SIZE, observer);
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


        try {
            if (key == KeyEvent.VK_UP && obstacles[pos.x][pos.y - 1] != 2) {
                facingDirection =FORWARD;
                File playerImgFileUp = new File("src/main/resources/images/player/up/"+ nameClass);
                image = ImageIO.read(playerImgFileUp);

                pos.translate(0, -1);
                for (Enemy enemy : enemies) {
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 0;
                    enemy.move(obstacles);
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 2;
                }
                for (Boss boss : bosses) {
                    boss.move(obstacles);
                }

                obstacles[pos.x][pos.y + 1] = 0;
                obstacles[pos.x ][pos.y] = 2;
            }
        } catch (Exception ignored) {

        }
        try {
            if (key == KeyEvent.VK_RIGHT && obstacles[pos.x + 1][pos.y] != 2) {
                facingDirection =RIGHT;
                File playerImageFileRight = new File("src/main/resources/images/player/right/"+ nameClass);
                image = ImageIO.read(playerImageFileRight);
                pos.translate(1, 0);


                for (Enemy enemy : enemies) {
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 0;
                    enemy.move(obstacles);
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 2;
                }
                for (Boss boss : bosses) {
                    boss.move(obstacles);
                }
                obstacles[pos.x - 1][pos.y] = 0;
                obstacles[pos.x][pos.y] = 2;
            }
        } catch (Exception ignored) {

        }
        try {
            if (key == KeyEvent.VK_DOWN && obstacles[pos.x][pos.y + 1] != 2) {
                pos.translate(0, 1);
                File playerImageFileDown = new File("src/main/resources/images/player/down/"+ nameClass);
                image = ImageIO.read(playerImageFileDown);
                facingDirection=BACKWARD;
                for (Enemy enemy : enemies) {
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 0;
                    enemy.move(obstacles);
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 2;
                }
                for (Boss boss : bosses) {
                    boss.move(obstacles);
                }
                obstacles[pos.x][pos.y - 1] = 0;
                obstacles[pos.x][pos.y] = 2;
            }
        } catch (Exception ignored) {

        }
        try {
            if (key == KeyEvent.VK_LEFT && obstacles[pos.x - 1][pos.y] != 2) {

                facingDirection = LEFT;
                pos.translate(-1, 0);
                File playerImageFileLeft = new File("src/main/resources/images/player/left/"+ nameClass);
                image = ImageIO.read(playerImageFileLeft);
                for (Enemy enemy : enemies) {
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 0;
                    enemy.move(obstacles);
                    obstacles[enemy.getPos().x][enemy.getPos().y] = 2;
                }
                for (Boss boss : bosses) {
                    boss.move(obstacles);
                }

                obstacles[pos.x + 1][pos.y] = 0;
                obstacles[pos.x][pos.y] = 2;
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

        }try {
            if (key == KeyEvent.VK_SPACE) {
               attack();
                System.out.println("attack");
            }
        } catch (Exception ignored) {

        }

        return obstacles;
    }



    public void attack() { //TODO: Attack method
        for (Enemy enemy : enemies) {
            if (pos.x == enemy.getPos().x && pos.y == enemy.getPos().y - 1) {
                System.out.println("Attack up");
                enemy.getDamage(getDamage());
            }
            if (pos.x == enemy.getPos().x && pos.y == enemy.getPos().y + 1) {
                System.out.println("Attack down");
                enemy.getDamage(getDamage());
            }
            if (pos.x == enemy.getPos().x - 1 && pos.y == enemy.getPos().y) {
                System.out.println("Attack Left");
                enemy.getDamage(getDamage());
            }
            if (pos.x == enemy.getPos().x + 1 && pos.y == enemy.getPos().y) {
                System.out.println("Attack Right");
                enemy.getDamage(getDamage());
            }
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

    public String getExperience() {
        return String.valueOf(experience);
    }

    public String getLevel() {
        return String.valueOf(level);
    }

    public String getHP() {
        return String.valueOf(hp);
    }

    public void addExperience(int amount) {
        experience += amount;
    }

    public void addLevel(int amount) {
        if (experience == 1000) {
            level += amount;
            experience = 0;
            hp = (int) (hp * 1.2);

        }
    }

    public Point getPos() {
        return pos;
    }
}


