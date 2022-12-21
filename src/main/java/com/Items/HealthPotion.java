package com.Items;

import com.CreateMap;
import com.Units.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Objects;

public class HealthPotion extends Item {
     String name = "healthpotion";
    public int quantity;
    private final Point pos;
    public static boolean isUsed;

    public HealthPotion(int x, int y){
        super(x,y);
        loadImage();
        pos = new Point(x, y);

    }
    public static void effect(){
        if(isUsed) {
            Player.addHP(50);
        }
    }
    public static void loadImage() {
        try {

            image = ImageIO.read(Objects.requireNonNull(HealthPotion.class.getResourceAsStream("/images/items/healthpotion.png")));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public String getName() {
        return name;
    }
    @Override
    public int getQuantity() {
        return quantity;
    }
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                image,
                (pos.x * CreateMap.TILE_SIZE - (CreateMap.TILE_SIZE/2)) + CreateMap.xOffset,
                (pos.y * CreateMap.TILE_SIZE - (CreateMap.TILE_SIZE/2)) + CreateMap.yOffset,
                observer
        );
    }
}
