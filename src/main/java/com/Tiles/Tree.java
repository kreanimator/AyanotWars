package com.Tiles;

import com.CreateMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Tree {
    private BufferedImage image;

    private final Point pos;

    public Tree(int x, int y) {

        loadImage();


        pos = new Point(x, y);
    }

    private void loadImage() {
        try {

            File enemyImageFile = new File("src/main/resources/images/tree.png");
            System.out.println("File IO is OK");
            image = ImageIO.read(enemyImageFile);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {

        g.drawImage(image, pos.x * CreateMap.TILE_SIZE, pos.y * CreateMap.TILE_SIZE, observer);
    }

    public Point getPos() {
        return pos;
    }

}
