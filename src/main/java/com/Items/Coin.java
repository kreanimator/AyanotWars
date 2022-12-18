package com.Items;

import com.CreateMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Coin extends Item {
    BufferedImage image;



    static String name = "Coin";
    private final Point pos;

    public int quantity;

    public Coin(int x, int y){
        super(x,y);
        loadImage();
        pos = new Point(x, y);
    }
    public void loadImage() {
        try {
            File coinImageFile = new File("src/main/resources/images/coin.png");
            BufferedImage image = ImageIO.read(coinImageFile);
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
    @Override
    public int getQuantity() {
        return quantity;
    }

    public static String getName() {
        return name;
    }
}
