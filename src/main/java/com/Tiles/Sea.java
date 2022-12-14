package com.Tiles;

import com.CreateMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



public class Sea {
    private BufferedImage image;

    private final Point pos;

    public Sea(int x, int y) {

        loadImage();
        pos = new Point(x, y);
    }
    public static ArrayList<Sea> fillSea() {
        ArrayList<Sea> seaList = new ArrayList<>();
        for (int i = 0; i > CreateMap.COLUMNS/0.8 ; i++) {
            for (int j = 0; j > CreateMap.ROWS/0.8; j++) {
                if (CreateMap.MAS_MAP[i][j] == 0) {
                    CreateMap.MAS_MAP[i][j] = 2;
                    seaList.add(new Sea(i, j));
                }
                }
            }
            return seaList;
        }

        private void loadImage () {
            try {

                File enemyImageFile = new File("src/main/resources/images/sea.png");
                image = ImageIO.read(enemyImageFile);
            } catch (IOException exc) {
                System.out.println("Error opening image file: " + exc.getMessage());
            }
        }

        public void draw (Graphics g, ImageObserver observer){
            g.drawImage(
                    image,
                    (pos.x * CreateMap.TILE_SIZE) + CreateMap.xOffset,
                    (pos.y * CreateMap.TILE_SIZE) + CreateMap.yOffset,
                    observer
            );
        }

        public Point getPos () {
            return pos;}

    }