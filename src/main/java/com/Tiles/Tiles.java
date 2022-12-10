package com.Tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tiles {

    BufferedImage tile;


    public void getGrass() {
        try {

            tile = ImageIO.read(new File("grass.png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }
    public void getStone() {
        try {

            tile = ImageIO.read(new File("stone.png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }
    public void getTree() {
        try {

            tile = ImageIO.read(new File("tree.png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

 //    public enum Type {
//        GRASS,
//        WATER,
//        STONE,
//        TREE
//    }
//
//    private Type type;
//
//    public Tiles()
//    {
//        type = Type.GRASS;
//    }
//
//    public void setType(Type type)
//    {
//        this.type = type;
//    }
//
//    public int getType()
//    {
//        return type.ordinal();
//    }
//
//    public String getTypeName()
//    {
//        return type.name();
//    }
//
//    public boolean isWalkable()
//    {
//        switch (type)
//        {
//            case GRASS:
//                return true;
//            default:
//                return false;
//        }
//    }
//
//
//    public boolean isSwimmable()
//    {
//        switch (type)
//        {
//            case WATER:
//                return true;
//            default:
//                return false;
//        }
//    }
//
//    public String getImage()
//    {
//        return type.name().toLowerCase() + ".png";
//    }
}

