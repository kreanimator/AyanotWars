package com.Items;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HealthPotion extends Item {
    private BufferedImage image;

    private void loadImage() {
        try {
            File healthpotionImageFile = new File("src/main/resources/images/healthpotion.png");
            System.out.println("File IO is OK");
            image = ImageIO.read(healthpotionImageFile);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }
}
