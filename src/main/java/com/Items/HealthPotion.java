package com.Items;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HealthPotion extends Item {

    public void loadImage() {
        try {
            File healthpotionImageFile = new File("src/main/resources/images/healthpotion.png");
            System.out.println("File IO is OK");
            BufferedImage image = ImageIO.read(healthpotionImageFile);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }
}
