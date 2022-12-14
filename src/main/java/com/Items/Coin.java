package com.Items;

import com.CreateMap;
import com.Units.Enemy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Coin extends Item {
    BufferedImage image;


    public void loadImage() {
        try {
            File coinImageFile = new File("src/main/resources/images/coin.png");
            BufferedImage image = ImageIO.read(coinImageFile);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, CreateMap createMap) {
    }
}
