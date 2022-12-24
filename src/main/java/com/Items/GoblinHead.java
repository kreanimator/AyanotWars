package com.Items;

import com.CreateMap;
import com.Units.Enemies.Enemy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Objects;

public class GoblinHead extends Item{

    static BufferedImage image;

    String name = "goblinhead";
    private final Point pos;
    public int quantity = Enemy.NUM_ENEMIES;

    public GoblinHead(int x, int y) {
        super(x, y);
        pos = new Point(x,y);
    }
    public static void loadImage() {
        try {

            image = ImageIO.read(Objects.requireNonNull(Coin.class.getResourceAsStream("/images/items/goblinhead.png")));
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
        return quantity;
    }

    public String getName() {
        return name;
    }
}

