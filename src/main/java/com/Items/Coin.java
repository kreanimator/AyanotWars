package com.Items;

import com.CreateMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Coin extends Item {
    static BufferedImage image;



    String name = "coin";
    private final Point pos;
    Random rand = new Random();
    public int quantity;

    public Coin(int x, int y){
        super(x,y);
        loadImage();
        pos = new Point(x, y);
    }
    public static void loadImage() {
        try {

            image = ImageIO.read(Objects.requireNonNull(Coin.class.getResourceAsStream("/images/items/coin.png")));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                image,
                (pos.x * CreateMap.TILE_SIZE - (CreateMap.TILE_SIZE/2)) + CreateMap.xOffset,
                (pos.y * CreateMap.TILE_SIZE - (CreateMap.TILE_SIZE/2)) + CreateMap.yOffset,
                observer
        );
    }
    @Override
    public int getQuantity() {
        return this.quantity;
    }

    public String getName() {
        return name;
    }
}
