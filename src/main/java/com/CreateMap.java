package com;

import com.Items.*;
import com.Tiles.*;
import com.Units.Boss;
import com.Units.Enemy;
import com.Units.Npc;
import com.Units.Player;
import com.Interfaces.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Arrays;


public class CreateMap extends JPanel implements ActionListener, KeyListener {

    // controls the size of the map
    public static final int TILE_SIZE = 50;
    public static final int ROWS = 30;
    public static final int COLUMNS = 30;
    // controls how many enemies appear on the board
    public static final int NUM_BOSS = 1;

//    public static final int worldWidth = TILE_SIZE * ROWS;
//    public static final int worldHeight = TILE_SIZE * COLUMNS;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 700;
    public static int[][] MAS_MAP = new int[COLUMNS][ROWS];
    public static int xOffset =0;
    public static int yOffset = 0;

    // suppress serialization warning
    @Serial
    private static final long serialVersionUID = 490905409104883233L;

    // objects that appear on the game board
    Player player;
    Npc npc;

    private final GameInterface gameInterface = new GameInterface();
    private final ArrayList<Enemy> enemies;
    private final ArrayList<Stone> stone;
    private final ArrayList<Tree> trees;
    private final ArrayList<Grass> grasses;
    private final ArrayList<Boss> bosses;
    private final ArrayList<Skull> skulls;
    private final ArrayList<House> houses;
    private final ArrayList<Road> roads;
    private final ArrayList<Item> items;



    static ArrayList<Item> collectedItems;
    Inventory inventory;



    public CreateMap() {
        // set the game board size
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setBackground(Color.blue);


//        MAS_MAP[0][0] = 1;
//        MAS_MAP[1][0] = 1;
//        MAS_MAP[0][1] = 1;
//        MAS_MAP[ROWS-1][COLUMNS-1] =1;
//        MAS_MAP[ROWS-2][COLUMNS-1] =1;
//        MAS_MAP[ROWS-1][COLUMNS-2] =1;
        for (int i = 0; i<ROWS;i++) {
            for (int j = 0; j < COLUMNS; j++) {
                    MAS_MAP[i][j]=0;
                    MAS_MAP[j][i]=0;
                }
            }
        for (int i = 0; i<ROWS;i++) {
            for (int j = 0; j < COLUMNS; j++) {
                MAS_MAP[i][i]=5;
                MAS_MAP[j][j]=5;
            }
        }
//        for (int x = ROWS -(ROWS-1); x > ROWS; x--){
//            for(int y =COLUMNS - (COLUMNS-1); y>COLUMNS; y--){
//                MAS_MAP[x][y]=0;
//                MAS_MAP[y][x]=0;
//            }
//        }

//        for (int i = ROWS-2; i>0;i--) {
//            for (int j = COLUMNS-2; j >0 ; j--) {
//                if (MAS_MAP[i][j] == MAS_MAP [ROWS-1][COLUMNS-1] ){
//                    MAS_MAP[i-1][j-1]=1;
//                    MAS_MAP[j-1][i-1]=1;
//                }
//            }
//        }
        System.out.println(Arrays.deepToString(MAS_MAP).replace("], ", "]\n"));

        skulls = Skull.fillSkulls();
        stone = Stone.fillStones();
        trees = Tree.fillTrees();
        enemies = Enemy.populateEnemies();
        bosses = Boss.addBoss();
        grasses = Grass.fillGrass();
        houses = House.fillHouses();
        roads = Road.fillRoad();
        items = Item.fillItems();
        player = new Player(50,50);
        npc = new Npc(MAS_MAP);



        // initialize the game state
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
        gameInterface.tick();
        // give the player experience for killing enemies
        killEnemies();
        killBosses();
        collectItems();
        repaint();
        // calling repaint() will trigger paintComponent() to run again,
        // which will refresh/redraw the graphics.
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
        for(Road road:roads){
            road.draw(g,this);
        }
        for (Skull skull: skulls){
            skull.draw(g,this);
        }
        for (Stone stone : stone) {
            stone.draw(g, this);
        }
        for (House house : houses) {
            house.draw(g, this);
        }
        for (Tree tree : trees) {
            tree.draw(g, this);
        }
        for (Enemy enemy : enemies) {
            enemy.draw(g, this);
            enemy.tick();
            enemy.drawHealthBar(g);
        }
        for (Boss boss : bosses) {
            boss.draw(g, this);
            boss.tick();
            boss.drawHealthBar(g);
        }
        for (Item item : items) {
            item.draw(g, this);
        }
        player.draw(g, this);
        npc.draw(g,this);
        npc.setNpcName(g);
        player.setPlayerName(g);
        GameInterface.drawActionPanel(g);
        GameInterface.draw(g,this);

        QuestDialog.draw(g);
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
        MAS_MAP = player.keyPressed(e, MAS_MAP, enemies, bosses);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // react to key up events
    }

    private void killEnemies() {
        // allow player to kill enemies
        ArrayList<Enemy> enemiesKilled = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (enemy.isKilled()) {
                enemy.removeObstacles(MAS_MAP);
                Enemy.addQuantityKilled(1);

                player.addExperience(100);
                for (int i = 1; i < 20; i++) {
                    player.addLevel(i);
                }
                enemiesKilled.add(enemy);
                //TODO: Regeneration of enemies
            }
        }
        // remove enemies from the board
        enemies.removeAll(enemiesKilled);
    }

    private void collectItems() {
        //ArrayList<Object> itemsInInventory = new ArrayList<>();
        ArrayList<Item> collectedItems = new ArrayList<>();

        for (Item item : items) {
            if (player.getPos().equals(item.getPos())) {
                collectedItems.add(item);
                item.addQuantityCollected(1);
                Inventory.setItemId(item.getName());
                //itemsInInventory.add(item);
                System.out.println("Item collected " + item.getName());
                System.out.println(" itemId " + Inventory.setItemId(item.getName()));
            }
        }

        // remove collected items from the board
        items.removeAll(collectedItems);
    }

    public static ArrayList<Item> getCollectedItems() {
        return collectedItems;
    }

    private void killBosses() {
        ArrayList<Boss> bossesKilled = new ArrayList<>();

        for (Boss boss : bosses) {
            if (boss.isKilled()) {
                player.addExperience(500);
                for (int i = 1; i < 20; i++) {
                    player.addLevel(i);
                }
                bossesKilled.add(boss);
            }
        }
        bosses.removeAll(bossesKilled);
    }
//    public void checkCollisions() { //TODO
//
//        Rectangle r3 = player.getBounds();
//
//
//        for (Enemy enemy : enemies) {
//            Rectangle r2 = enemy.getBounds();
//            if (r3.intersects(r2)) {
//
//
//            }
//        }
//    }
//    public void enemyCollide(){
//
//    }
}