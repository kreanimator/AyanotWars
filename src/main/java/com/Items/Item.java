package com.Items;

import com.CreateMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Item {


   BufferedImage image;

   public static String name = "";

   private final Point pos;

   public int quantity;



   public void loadImage() {

   }

   public Item(int x, int y) {
      loadImage();
      pos = new Point(x, y);
   }

   public void draw(Graphics g, ImageObserver observer) {
      g.drawImage(

              image,
              (pos.x * CreateMap.TILE_SIZE) + CreateMap.xOffset,
              (pos.y * CreateMap.TILE_SIZE) + CreateMap.yOffset,
              observer
      );
   }

   public static String getName() {
      return name;
   }

   public int getQuantity() {
      return quantity;
   }
}

