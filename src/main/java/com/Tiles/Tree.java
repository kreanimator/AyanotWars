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

    public Tree(int x, int y) {

        loadImage();


        pos = new Point(x, y);
    }
    public static ArrayList<Tree> fillTrees() {
        ArrayList<Tree> treeList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < CreateMap.NUM_TREES; ) {
            int treeX = rand.nextInt(CreateMap.COLUMNS);
            int treeY = rand.nextInt(CreateMap.ROWS);
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

            File enemyImageFile = new File("src/main/resources/images/tree.png");
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
