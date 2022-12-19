package com.Items;

import com.CreateMap;

import com.Units.Enemy;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

public class Item {



    static BufferedImage image;
    static Random rand = new Random();
    private static final int NUM_ITEMS = rand.nextInt(10,30);

    public String name = "";

    private final Point pos;

    public int quantity;






    public Item(int x, int y) {
        loadImage();
        pos = new Point(x, y);
    }


    public static ArrayList<Item> fillItems() {
        ArrayList<Item> itemList = new ArrayList<>();
        for (int i = 0; i < NUM_ITEMS; i++) {

            int x = rand.nextInt(CreateMap.COLUMNS);
            int y = rand.nextInt(CreateMap.ROWS);
            Random rand = new Random();
            int tmp = rand.nextInt(2);
            if (CreateMap.MAS_MAP[x][y] == 0) {
                switch (tmp) {
                    case 0 -> itemList.add(new HealthPotion(x, y));
                    case 1 -> itemList.add(new Coin(x, y));
                }
            }
        }
            return itemList;
        }



   public static void loadImage()  {
     }



    public void draw(Graphics g, ImageObserver observer) {
      g.drawImage(

              image,
              (pos.x * CreateMap.TILE_SIZE - (CreateMap.TILE_SIZE/2)) + CreateMap.xOffset,
              (pos.y * CreateMap.TILE_SIZE - (CreateMap.TILE_SIZE/2)) + CreateMap.yOffset,
              observer
      );
   }
   public String getName() {
      return this.name;
   }

   public int getQuantity() {
      return quantity;
   }
   public Point getPos() {
      return pos;
   }


}

