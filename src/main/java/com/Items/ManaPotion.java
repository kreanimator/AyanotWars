package com.Items;

import com.CreateMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class ManaPotion extends Item {

        String name = "healthpotion";
        public int quantity;
        private final Point pos;

        public ManaPotion(int x, int y){
            super(x,y);
            loadImage();
            pos = new Point(x, y);

        }



    public static ArrayList<com.Items.HealthPotion> fillPotions() {
            ArrayList<com.Items.HealthPotion> potionList = new ArrayList<>();
            Random rand = new Random();
            for (int i = 0; i < 10; ) {
                int potionX = rand.nextInt(CreateMap.COLUMNS);
                int potionY = rand.nextInt(CreateMap.ROWS);
                potionList.add(new com.Items.HealthPotion(potionX, potionY));
                i++;
            }
            return potionList;
        }
        public static void loadImage() {
            try {

                image = ImageIO.read(Objects.requireNonNull(com.Items.HealthPotion.class.getResourceAsStream("/images/items/manapotion.png")));
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
                    (pos.x * CreateMap.TILE_SIZE) + CreateMap.xOffset,
                    (pos.y * CreateMap.TILE_SIZE) + CreateMap.yOffset,
                    observer
            );
        }
    }

