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

public class House {
    private BufferedImage image;
    public static final int NUM_HOUSES = ((CreateMap.ROWS*CreateMap.COLUMNS)/60);

    private final Point pos;

    public House(int x, int y) {

        loadImage();


        pos = new Point(x, y);
    }
    public static ArrayList<House> fillHouses() {
        ArrayList<House> houseList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < NUM_HOUSES; ) {
            int houseX = rand.nextInt(CreateMap.COLUMNS-1);
            int houseY = rand.nextInt(CreateMap.ROWS-1);
            if (CreateMap.MAS_MAP[houseX][houseY] == 0) {
                CreateMap.MAS_MAP[houseX][houseY] = 2;
                houseList.add(new House(houseX, houseY));
                i++;
            }
        }
        return houseList;
    }

    private void loadImage() {
            try {
                Random rand = new Random();
                int randomNum = rand.nextInt((4 - 1) + 1) + 1;
                File houseImageFile = new File("src/main/resources/images/houses/" + randomNum + ".png");
                image = ImageIO.read(houseImageFile);
            } catch (IOException exc) {
                System.out.println("Error opening image file: " + exc.getMessage());
            }
        }


    public void draw(Graphics g, ImageObserver observer) {

        g.drawImage(image, ((pos.x * CreateMap.TILE_SIZE)-(CreateMap.TILE_SIZE/2))+CreateMap.xOffset,
                (pos.y * CreateMap.TILE_SIZE-(CreateMap.TILE_SIZE/2))+CreateMap.yOffset, observer);
    }

    public Point getPos() {
        return pos;
    }
}
