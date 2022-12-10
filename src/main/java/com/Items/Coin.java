package com.Items;

import com.Interfaces.Inventory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Coin extends Item{
    private BufferedImage image;

    private void loadImage() {
        try {
            File coinImageFile = new File("src/main/resources/images/coin.png");
            System.out.println("File IO is OK");
            image = ImageIO.read(coinImageFile);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }
}
