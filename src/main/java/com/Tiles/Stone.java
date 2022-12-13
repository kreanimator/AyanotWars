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

public class Stone {

    private BufferedImage image;

    private final Point pos;

    public Stone(int x, int y) {

        loadImage();


        pos = new Point(x, y);
    }
    public static ArrayList<Stone> fillStones() {
        ArrayList<Stone> stoneList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < CreateMap.NUM_ROCKS; ) {
            int rockX = rand.nextInt(CreateMap.COLUMNS);
            int rockY = rand.nextInt(CreateMap.ROWS);
            if (CreateMap.MAS_MAP[rockX][rockY] == 0) {
                CreateMap.MAS_MAP[rockX][rockY] = 2;
                stoneList.add(new Stone(rockX, rockY));
                i++;
            }
        }
        return stoneList;
    }

    private void loadImage() {
        try {

            File enemyImageFile = new File("src/main/resources/images/stone.png");
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

