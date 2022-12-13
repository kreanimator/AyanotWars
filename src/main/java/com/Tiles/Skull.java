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

public class Skull {

    private BufferedImage image;

    private final Point pos;

    public Skull(int x, int y) {

        loadImage();


        pos = new Point(x, y);
    }
    public static ArrayList<Skull> fillSkulls() {
        ArrayList<Skull> skullList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < CreateMap.NUM_SKULLS; ) {
            int skullX = rand.nextInt(CreateMap.COLUMNS);
            int skullY = rand.nextInt(CreateMap.ROWS);
            if (CreateMap.MAS_MAP[skullX][skullY] == 0) {
                skullList.add(new Skull(skullX ,skullY));
                i++;
            }
        }
        return skullList;
    }

    private void loadImage() {
        try {

            File enemyImageFile = new File("src/main/resources/images/skull.png");
            image = ImageIO.read(enemyImageFile);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {

        g.drawImage(image, (pos.x * CreateMap.TILE_SIZE)+CreateMap.xOffset, (pos.y * CreateMap.TILE_SIZE)+CreateMap.yOffset, observer);
    }

    public Point getPos() {
        return pos;
    }
}
