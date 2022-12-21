package com.Units;

import com.CreateMap;
import com.Interfaces.Inventory;
import com.Interfaces.PlayerChoose;
import com.Interfaces.QuestDialog;
import com.Skills.Skill;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Player {

    private static String nameClass = "";
    private BufferedImage up1, up2, up3, down1, down2, down3, right1, right2, right3, left1, left2, left3;
    //    private BufferedImage atkup1, atkup2, atkup3, atkup4, atkdown1, atkdown2, atkdown3,atkdown4,
//            atckleft1, atckleft2, atckleft3, atckleft4, atkright1,atkright2,atkright3,atkright4;
    private final Point pos;
    private final static int FORWARD = 0;
    private final static int BACKWARD = 1;
    private final static int LEFT = 2;
    private final static int RIGHT = 3;
    int height = 50;
    int width = 50;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
//    public static int screenX = CreateMap.WIDTH/2 - (CreateMap.TILE_SIZE/2);
//    public static int screenY = CreateMap.HEIGHT/2-(CreateMap.TILE_SIZE/2);

    //TODO: for proper camera realization its needed to realize player screenY & screenX and connect them with map offset,
    //TODO: Also it should be done for all tiles etc.


    static Random rd = new Random();

    //    static int x = rd.nextInt(CreateMap.ROWS)* CreateMap.TILE_SIZE;
//    static int y = rd.nextInt(CreateMap.COLUMNS)*CreateMap.TILE_SIZE;
    static int x = CreateMap.ROWS / 2;
    static int y = CreateMap.COLUMNS / 4;


    private static int experience;
    public static int hp = 100;
    public static int mp = 100;
    private static int level = 1;
    private static String playerClass = "";
    public int damage = 15;
    private int attackRange;
    public int facingDirection;
    ArrayList<Enemy> enemies;
    Inventory inventory = new Inventory();

    public static String getNameClass() {
        return nameClass;
    }

    public Player(int width, int height) {

        this.width = width;
        this.height = height;

//        this.x = (CreateMap.ROWS/2) -(width/2);
//        this.y = (CreateMap.COLUMNS/2) -(height/2);
        attackRange = 1;
        getPlayerImage();
        pos = new Point(x, y);
        //pos = new Point(CreateMap.xOffset, CreateMap.yOffset);
        experience = 0;
    }

    public static void setPlayerClass(String playerClass) {

        if (Objects.equals(playerClass, "warrior")) {
            Player.playerClass = "src/main/resources/images/player/" + playerClass + ".png";
            Player.nameClass = "warrior";
            //Warrior warrior = new Warrior();
        } else if (Objects.equals(playerClass, "warlock")) {
            Player.playerClass = "src/main/resources/images/player/" + playerClass + ".png";
            Player.nameClass = "warlock";
            // Warlock warlock = new Warlock();
        } else if (Objects.equals(playerClass, "mage")) {
            Player.playerClass = "src/main/resources/images/player/" + playerClass + ".png";
            Player.nameClass = "mage";
            //Mage mage = new Mage();
        }
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + nameClass + "/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + nameClass + "/up2.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + nameClass + "/up3.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + nameClass + "/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + nameClass + "/down2.png")));
            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + nameClass + "/down3.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + nameClass + "/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + nameClass + "/left2.png")));
            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + nameClass + "/left3.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + nameClass + "/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + nameClass + "/right2.png")));
            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + nameClass + "/right3.png")));
//            atkup1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/up1.png")));
//            atkup2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/up2.png")));
//            atkup3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/up3.png")));
//            atkup4= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/up4.png")));
//            atkdown1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/down1.png")));
//            atkdown2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/down2.png")));
//            atkdown3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/down3.png")));
//            atkdown4= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/down4.png")));
//            atckleft1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/left1.png")));
//            atckleft2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/left2.png")));
//            atckleft3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/left3.png")));
//            atckleft4= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/left4.png")));
//            atkright1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/right1.png")));
//            atkright2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/right2.png")));
//            atkright3= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/right3.png")));
//            atkright4= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/attack/right4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerName(Graphics g) {
        // set the text to be displayed

        String name = PlayerChoose.setPlayerName();
        Font myFont = null;
        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("src/main/resources/font/GravityBold8.ttf"));

            myFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);


        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // we need to cast the Graphics to Graphics2D to draw nicer text
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setColor(Color.WHITE);
        assert myFont != null;
        g2d.setFont(myFont.deriveFont(Font.BOLD, 10f));
        int x = ((pos.x * CreateMap.TILE_SIZE) - CreateMap.TILE_SIZE / 2) + CreateMap.xOffset;

        int y = ((pos.y * CreateMap.TILE_SIZE - 10) - CreateMap.TILE_SIZE / 2) + CreateMap.yOffset;
        g2d.drawString(name, x, y);
    }


    public static void getDamage(int value) {
        hp -= value;

    }
    public static void addHP(int value) {
        Player.hp += value;

    }
    public static void addMP(int value) {
        Player.mp += value;

    }

    public Rectangle getBounds() {
        return new Rectangle(pos.x, pos.y, width, height);
    }


    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }


    public void draw(Graphics g, ImageObserver observer) {

        BufferedImage image = null;

        switch (facingDirection) {
            case FORWARD -> {
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                if (spriteNumber == 3) {
                    image = up3;
                }
            }
            case BACKWARD -> {
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                if (spriteNumber == 3) {
                    image = down3;
                }
            }
            case LEFT -> {
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                if (spriteNumber == 3) {
                    image = left3;
                }
            }

            case RIGHT -> {
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                if (spriteNumber == 3) {
                    image = right3;
                }
            }
        }


        g.drawImage(image, ((pos.x * CreateMap.TILE_SIZE) - (CreateMap.TILE_SIZE / 2)) + CreateMap.xOffset,
                (pos.y * CreateMap.TILE_SIZE - (CreateMap.TILE_SIZE / 2)) + CreateMap.yOffset, observer);

//        g.drawImage(image, screenX + CreateMap.xOffset,
//                screenY + CreateMap.yOffset, observer);

    }

//    public void drawatk(Graphics g, ImageObserver observer) {
//
//        BufferedImage imageatk = null;
//
//                if (facingDirection==FORWARD){
//                    if (spriteNumber == 1) {
//                        imageatk = atkup1;
//                    }
//                    if (spriteNumber == 2) {
//                        imageatk = atkup2;
//                    }
//                    if (spriteNumber == 3) {
//                        imageatk = atkup3;
//                    }
//                    if (spriteNumber == 4) {
//                        imageatk = atkup4;
//                    }
//
//                }
//                if (facingDirection==BACKWARD){
//                    if (spriteNumber == 1) {
//                        imageatk = atkdown1;
//                    }
//                    if (spriteNumber == 2) {
//                        imageatk = atkdown2;
//                    }
//                    if (spriteNumber == 3) {
//                        imageatk= atkdown3;
//                    }
//                    if (spriteNumber == 4) {
//                        imageatk = atkdown4;
//                    }
//
//                }         if (facingDirection==LEFT){
//                    if (spriteNumber == 1) {
//                        imageatk = atckleft1;
//                    }
//                    if (spriteNumber == 2) {
//                        imageatk = atckleft2;
//                    }
//                    if (spriteNumber == 3) {
//                        imageatk = atckleft3;
//                    }
//                    if (spriteNumber == 4) {
//                        imageatk = atckleft4;
//                    }
//
//                }
//                if (facingDirection==RIGHT){
//                    if (spriteNumber == 1) {
//                        imageatk = atkright1;
//                    }
//                    if (spriteNumber == 2) {
//                        imageatk = atkright2;
//                    }
//                    if (spriteNumber == 3) {
//                        imageatk = atkright3;
//                    }
//                    if (spriteNumber == 4) {
//                        imageatk = atkright4;
//                    }
//
//
//            }
//        }
//
//        g.drawImage(imageatk, (((pos.x  * CreateMap.TILE_SIZE))+CreateMap.TILE_SIZE) + CreateMap.xOffset,
//                ((pos.y * CreateMap.TILE_SIZE)+CreateMap.TILE_SIZE) + CreateMap.yOffset, observer);
//    }

    static JDialog inventoryWindow = new JDialog();


    public void initWindow() {

        inventoryWindow.setSize(Inventory.ROWS * Inventory.TILE_SIZE + 14, Inventory.COLUMNS * Inventory.TILE_SIZE + 35);
        inventoryWindow.setLocationRelativeTo(null);
        inventoryWindow.setVisible(true);
        inventoryWindow.setResizable(false);
        inventoryWindow.setForeground(new Color(0, 0, 0, 0));
        inventoryWindow.add(inventory);
        inventoryWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public int[][] keyPressed(KeyEvent e, int[][] obstacles, ArrayList<Enemy> enemies, ArrayList<Boss> bosses) {

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP && obstacles[pos.x][pos.y - 1] != 2) {
            facingDirection = FORWARD;
            CreateMap.yOffset += CreateMap.TILE_SIZE;
            //screenY -= CreateMap.TILE_SIZE;
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
        } else if (key == KeyEvent.VK_UP && obstacles[pos.x][pos.y - 1] == 2) {
            facingDirection = FORWARD;
        }
        if (key == KeyEvent.VK_RIGHT && obstacles[pos.x + 1][pos.y] != 2) {
            facingDirection = RIGHT;
            //screenX += CreateMap.TILE_SIZE;
            CreateMap.xOffset -= CreateMap.TILE_SIZE;

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

        } else if (key == KeyEvent.VK_RIGHT && obstacles[pos.x + 1][pos.y] == 2) {
            facingDirection = RIGHT;
        }

        if (key == KeyEvent.VK_DOWN && obstacles[pos.x][pos.y + 1] != 2) {
            facingDirection = BACKWARD;

            CreateMap.yOffset -= CreateMap.TILE_SIZE;
            //screenY -= CreateMap.TILE_SIZE;
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

        } else if (key == KeyEvent.VK_DOWN && obstacles[pos.x][pos.y + 1] == 2) {
            facingDirection = BACKWARD;
        }

        if (key == KeyEvent.VK_LEFT && obstacles[pos.x - 1][pos.y] != 2) {
            facingDirection = LEFT;
            CreateMap.xOffset += CreateMap.TILE_SIZE;
            //screenX += CreateMap.TILE_SIZE;
            pos.translate(-1, 0);

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

        } else if (key == KeyEvent.VK_LEFT && obstacles[pos.x - 1][pos.y] == 2) {
            facingDirection = LEFT;
        }
        spriteCounter++;
        if (spriteCounter > 0) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 3;
            } else if (spriteNumber == 3) {
                spriteNumber = 1;
            }
        }

        if (key == KeyEvent.VK_I) {


            inventoryWindow.setUndecorated(true);
            inventoryWindow.setVisible(true);
            inventoryWindow.setBackground(new Color(0, 0, 0, 100));
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
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
        if (key == KeyEvent.VK_S) {
            Skill skill = new Skill();
        }

        if (key == KeyEvent.VK_E) {
            if (facingDirection == FORWARD && pos.x == Npc.getPos().x && pos.y == Npc.getPos().y + 1 ||
                    facingDirection == BACKWARD && pos.x == Npc.getPos().x && pos.y == Npc.getPos().y - 1 ||
                    facingDirection == RIGHT && pos.x == Npc.getPos().x - 1 && pos.y == Npc.getPos().y ||
                    facingDirection == LEFT && pos.x == Npc.getPos().x + 1 && pos.y == Npc.getPos().y) {
                    QuestDialog questDialog = new QuestDialog();
            }
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
                    }

                    if (facingDirection == BACKWARD && pos.x == enemy.getPos().x && pos.y == enemy.getPos().y - 1) {

                        System.out.println("Attack down");
                        enemy.getDamage(playerDamage());
                        enemy.getCurrentHP();
                    }

                    if (facingDirection == LEFT && pos.x == enemy.getPos().x + 1 && pos.y == enemy.getPos().y) {

                        System.out.println("Attack Left");
                        enemy.getDamage(playerDamage());
                        enemy.getCurrentHP();

                    }

                    if (facingDirection == RIGHT && pos.x == enemy.getPos().x - 1 && pos.y == enemy.getPos().y) {

                        System.out.println("Attack Right");
                        enemy.getDamage(playerDamage());
                        enemy.getCurrentHP();
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
                    }
                    if (facingDirection == BACKWARD && pos.x == boss.getPos().x && pos.y == boss.getPos().y - 1) {

                        System.out.println("Attack down");
                        boss.getDamage(playerDamage());
                        boss.getCurrentHP();

                    }
                    if (facingDirection == LEFT && pos.x == boss.getPos().x + 1 && pos.y == boss.getPos().y) {

                        System.out.println("Attack Left");
                        boss.getDamage(playerDamage());
                        boss.getCurrentHP();

                    }
                    if (facingDirection == RIGHT && pos.x == boss.getPos().x - 1 && pos.y == boss.getPos().y) {

                        System.out.println("Attack Right");
                        boss.getDamage(playerDamage());
                        boss.getCurrentHP();

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

    public int playerDamage() {
        return damage;
    }


    public void tick() {

        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= CreateMap.COLUMNS) {
            pos.x = (CreateMap.COLUMNS - 1);

        }
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= CreateMap.ROWS) {
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

    public static int getMp() {
        return mp;
    }

    public void addExperience(int amount) {
        experience += amount;
    }

    public void addLevel(int amount) {
        if (experience == 1000) {
            level += amount;
            experience = 0;
            hp = (int) (hp * 1.2);
            damage = (int) (damage * 1.2);
        }
    }

    public Point getPos() {
        return pos;
    }
}

