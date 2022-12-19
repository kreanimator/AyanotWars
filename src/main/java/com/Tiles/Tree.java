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

public class Tree {
    private BufferedImage image;

    private final Point pos;
    public static final int NUM_TREES =  ((CreateMap.ROWS*CreateMap.COLUMNS)/30);

    public Tree(int x, int y) {

        loadImage();


        pos = new Point(x, y);
    }
    public static ArrayList<Tree> fillTrees() {
        ArrayList<Tree> treeList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < NUM_TREES; ) {
            int treeX = rand.nextInt(CreateMap.COLUMNS-1);
            int treeY = rand.nextInt(CreateMap.ROWS-1);
            if (CreateMap.MAS_MAP[treeX][treeY] == 0) {
                CreateMap.MAS_MAP[treeX][treeY] = 2;
                treeList.add(new Tree(treeX, treeY));
                i++;
            }
        }
        return treeList;
    }

    private void loadImage() {
        try {
            Random rand = new Random();
            int randomNum = rand.nextInt((4 - 1) + 1) + 1;
            File enemyImageFile = new File("src/main/resources/images/tiles/trees/"+randomNum+".png");
            image = ImageIO.read(enemyImageFile);
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
