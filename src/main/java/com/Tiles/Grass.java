package com.Tiles;

import com.CreateMap;
import com.Units.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Grass {
    private BufferedImage image, imageleft, imageright, imageup, imagedown, cornerupright, cornerupleft, cornerdownright, cornerdownleft;

    private final Point pos;
    int direction;
    private final static int LEFT=0;
    private final static int RIGHT=1;
    private final static int UP=2;
    private final static int DOWN=3;
    public int RIGHTUPCORNER;
    public int LEFTUPCORNER;
    public int RIGHTDOWNCORNER;
    public int LEFTDOWNCORNER;

    public Grass(int x, int y) {

        loadImage();
        pos = new Point(x, y);
    }
    public void checkDirection(){
        if (pos.x == CreateMap.COLUMNS){
            direction = LEFT;
        }
        if (pos.y == CreateMap.ROWS){
            direction = UP;
        }
        if (pos.x == CreateMap.ROWS){
            direction = RIGHT;
        }
        if (pos.y == CreateMap.COLUMNS){
            direction = DOWN;
        }
    }

    public static ArrayList<Grass> fillGrass() {
        ArrayList<Grass> grassList = new ArrayList<>();
        for (int i = 0; i < CreateMap.COLUMNS; i++) {
            for (int j = 0; j < CreateMap.ROWS; j++) {
                grassList.add(new Grass(i, j));
            }
        }
        return grassList;
    }

    private void loadImage() {
        try {

            File enemyImageFile = new File("src/main/resources/images/tiles/grass/grass2.png");
            image = ImageIO.read(enemyImageFile);
//            imageleft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/grass/grassleft.png")));
//            imageright = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/grass/grassright.png")));
//            imagedown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/grass/grassdown.png")));
//            imageup = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/tiles/grass/grassup.png")));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
//        BufferedImage image = null;
//        switch (direction){
//            case UP -> {
//                image = imageup;
//                break;
//            }
//            case DOWN -> {
//                image = imagedown;
//                break;
//            }
//            case LEFT -> {
//                image = imageleft;
//                break;
//            }
//            case RIGHT -> {
//                image = imageright;
//                break;
//            }
//        }
        g.drawImage(

                image,
                (pos.x * CreateMap.TILE_SIZE - (CreateMap.TILE_SIZE/2)) + CreateMap.xOffset,
                (pos.y * CreateMap.TILE_SIZE - (CreateMap.TILE_SIZE/2)) + CreateMap.yOffset,
                observer
        );
    }

    public Point getPos() {
        return pos;
    }

}
