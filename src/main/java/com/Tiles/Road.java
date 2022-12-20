package com.Tiles;

import com.CreateMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;



public class Road {
    private BufferedImage image;

    private final Point pos;

    public Road(int x, int y) {

        loadImage();
        pos = new Point(x, y);
    }
    public static ArrayList<Road> fillRoad() {
        ArrayList<Road> roadList = new ArrayList<>();
        for (int i = 0; i < CreateMap.COLUMNS; i++) {
            for (int j = 0; j < CreateMap.ROWS; j++) {
                if (CreateMap.MAS_MAP[i][j] == 5) {
                    roadList.add(new Road(i, j));
                }
            }
        }return roadList;
    }


    private void loadImage() {
        try {

            File enemyImageFile = new File("src/main/resources/images/tiles/road.png");
            image = ImageIO.read(enemyImageFile);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {

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

