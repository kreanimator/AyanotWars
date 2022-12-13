package com.Tiles;

import com.CreateMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Grass {
    private BufferedImage image;

    private final Point pos;

    public Grass(int x, int y) {

        loadImage();
        pos = new Point(x, y);
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

            File enemyImageFile = new File("src/main/resources/images/grass.png");
            image = ImageIO.read(enemyImageFile);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                image,
                (pos.x * CreateMap.TILE_SIZE) + CreateMap.xOffset,
                (pos.y * CreateMap.TILE_SIZE) + CreateMap.yOffset,
                observer
        );
    }

    public Point getPos() {
        return pos;
    }

}
