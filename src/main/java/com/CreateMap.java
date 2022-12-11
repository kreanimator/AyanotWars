package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Random;

import com.Interfaces.Inventory;
import com.Items.HealthPotion;
import com.Tiles.*;
//import javafx.scene.Scene;
//import javafx.scene.shape.Rectangle;




public class CreateMap extends JPanel implements ActionListener , KeyListener {

    // controls the size of the map
    static Random gen = new Random();
    public static final int TILE_SIZE = 50;
    public static final int ROWS = 15;
    public static final int COLUMNS = 30;
    // controls how many enemies appear on the board
    public static final int NUM_ENEMIES = gen.nextInt(10, 30);
    public static final int NUM_ROCKS = 15;
    public static final int NUM_TREES = 15;
    public static final int NUM_BOSS = 1;
    public static final int[][] MAS_MAP = new int[COLUMNS][ROWS];

    // suppress serialization warning
    @Serial
    private static final long serialVersionUID = 490905409104883233L;

    // objects that appear on the game board
    private final Player player = new Player();
    private final Inventory inventory = new Inventory();
    private final ArrayList<Enemy> enemies;
    private final ArrayList<Stone> stone;
    private final ArrayList<Tree> trees;
    private final ArrayList<Grass> grasses;
    private final ArrayList<Boss> bosses;


    public CreateMap() {
        // set the game board size
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));

        MAS_MAP[0][0] = 1;
        MAS_MAP[1][0] = 1;
        MAS_MAP[0][1] = 1;
        // initialize the game state
        enemies = populateEnemies();
        stone = fillStones();
        trees = fillTrees();
        grasses = fillGrass();
        bosses = addBoss();


        // this timer will call the actionPerformed() method every DELAY ms
        int DELAY = 25;
        // keep a reference to the timer object that triggers actionPerformed() in
        // case we need access to it in another method
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this method is called by the timer every DELAY ms.
        // use this space to update the state of your game or animation
        // before the graphics are redrawn.

        // prevent the player from disappearing off the board
        player.tick();

        // give the player experience for killing enemies
        killEnemies();
        killBosses();
        // calling repaint() will trigger paintComponent() to run again,
        // which will refresh/redraw the graphics.
        repaint();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // when calling g.drawImage() we can use "this" for the ImageObserver
        // because Component implements the ImageObserver interface, and JPanel
        // extends from Component. So "this" Board instance, as a Component, can
        // react to imageUpdate() events triggered by g.drawImage()

        // draw our graphics.
        for (Grass grass : grasses) {
            grass.draw(g, this);
        }
        for (Enemy enemy : enemies) {
            enemy.draw(g, this);
            enemy.tick();
            enemy.drawHealthBar(g);

        }
        for (Stone stone : stone) {
            stone.draw(g, this);
        }
        for (Tree tree : trees) {
            tree.draw(g, this);
        }

        for (Boss boss : bosses) {
            boss.draw(g, this);
            boss.tick();
            boss.drawHealthBar(g);
        }
        player.draw(g, this);
        player.setPlayerName(g);
        drawActionPanel(g);

        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // this is not used but must be defined as part of the KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // react to key down events
        player.keyPressed(e, MAS_MAP, enemies, bosses);
    }

    public void mousePressed(MouseEvent m) {
        player.mousePressed(m, enemies);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // react to key up events
    }


    private void drawActionPanel(Graphics g) {
        // set the text to be displayed
        String text = "Exp " + player.getExperience();
        String textLvl = "Level " + player.getLevel();
        String hplvl = "HP " + player.getHP();
        String inv = "For inventory press 'i'";
        // we need to cast the Graphics to Graphics2D to draw nicer text
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.getHSBColor(31, 18, 95));
        g2.fillRect(0, TILE_SIZE * (ROWS - 1), TILE_SIZE * COLUMNS, TILE_SIZE);
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
        Rectangle rect = new Rectangle(0, TILE_SIZE * (ROWS - 1), TILE_SIZE * COLUMNS, TILE_SIZE);


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

        g2d.drawString(inv, x3, y);
//        Button inventory = new Button("Inventory");
//        inventory.setBounds(x3,y3,TILE_SIZE*3,TILE_SIZE-10);
//        inventory.setFont(new Font("Lato", Font.BOLD, 25));
//        inventory.setVisible(true);
        //TODO: Realise inventory button.

    }


    private ArrayList<Enemy> populateEnemies() {
        ArrayList<Enemy> enemyList = new ArrayList<>();
        Random rand = new Random();

        // create the given number of enemies in random positions on the board.
        // note that there is not check here to prevent two coins from occupying the same
        // spot, nor to prevent coins from spawning in the same spot as the player
        for (int i = 0; i < NUM_ENEMIES; ) {
            int enemyX = rand.nextInt(COLUMNS);
            int enemyY = rand.nextInt(ROWS);
            if (MAS_MAP[enemyX][enemyY] == 0) {
                MAS_MAP[enemyX][enemyY] = 1;
                enemyList.add(new Enemy(enemyX, enemyY));
                i++;
            }
        }
        return enemyList;
    }


    private ArrayList<Stone> fillStones() {
        ArrayList<Stone> stoneList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < NUM_ROCKS; ) {
            int rockX = rand.nextInt(COLUMNS);
            int rockY = rand.nextInt(ROWS);
            if (MAS_MAP[rockX][rockY] == 0) {
                MAS_MAP[rockX][rockY] = 2;
                stoneList.add(new Stone(rockX, rockY));
                i++;
            }
        }

        return stoneList;
    }

    private ArrayList<Boss> addBoss() {
            ArrayList<Boss> bossList = new ArrayList<>();
            Random rand = new Random();
            int bossX = rand.nextInt(COLUMNS);
            int bossY = rand.nextInt(ROWS);
            for (int i = 0; i < NUM_BOSS; ) {

                if (MAS_MAP[bossX][bossY] == 0) {
                    MAS_MAP[bossX][bossY] = 1;
                    bossList.add(new Boss(bossX, bossY));
                    i++;

                  }
            }
            return bossList;
        }

    private ArrayList<Tree> fillTrees() {
        ArrayList<Tree> treeList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < NUM_TREES; ) {
            int treeX = rand.nextInt(COLUMNS);
            int treeY = rand.nextInt(ROWS);
            if (MAS_MAP[treeX][treeY] == 0) {
                MAS_MAP[treeX][treeY] = 2;
                treeList.add(new Tree(treeX, treeY));
                i++;
            }
        }
        return treeList;
    }
    private ArrayList<Grass> fillGrass() {
        ArrayList<Grass> grassList = new ArrayList<>();
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
                grassList.add(new Grass(i, j));
            }
        }
        return grassList;
    }

    private void killEnemies() {
        // allow player to kill enemies
        ArrayList<Enemy> enemiesKilled = new ArrayList<>();

        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                enemy.getDamage(player.damage);
                // if the player is on the same tile as an enemy, collect it
                if (player.getPos().equals(enemy.getPos())) {
                    // give the player some points for picking this up
                    player.addExperience(100);
                    for(int i =1; i < 20; i++) {
                        player.addLevel(i);
                    }
                    enemiesKilled.add(enemy);
               }
//                if (String.valueOf(NUM_ENEMIES).equals(String.valueOf(enemiesKilled))){
//                    populateEnemies();
//            }
                    //TODO: Regeneration of enemies
            }
        }
        // remove enemies from the board
        enemies.removeAll(enemiesKilled);
    }
    private void killBosses() {
        ArrayList<Boss> bossesKilled = new ArrayList<>();

        for (Boss boss : bosses) {

                if (player.getPos().equals(boss.getPos())) {
                    player.addExperience(500);
                    bossesKilled.add(boss);
                }
            }bosses.removeAll(bossesKilled);
        }

    }
