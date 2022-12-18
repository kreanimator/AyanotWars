package com.Items;

import com.CreateMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class HealthPotion extends Item {



    static String name = "healthpotion";
    public int quantity;
    private final Point pos;

    public HealthPotion(int x, int y){
        super(x,y);
        loadImage();
        pos = new Point(x, y);
    }
    public void loadImage() {
        try {
            File healthpotionImageFile = new File("src/main/resources/images/healthpotion.png");
            System.out.println("File IO is OK");
            BufferedImage image = ImageIO.read(healthpotionImageFile);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }
    public static String getName() {
        return name;
    }
    @Override
    public int getQuantity() {
        return quantity;
    }
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(

                image,
                (pos.x * CreateMap.TILE_SIZE) + CreateMap.xOffset,
                (pos.y * CreateMap.TILE_SIZE) + CreateMap.yOffset,
                observer
        );
    }
}
