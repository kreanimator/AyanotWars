package com.Graphics;

import com.CreateMap;
import com.Units.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Objects;

public class Animation {
//    private BufferedImage up1,up2,up3,down1,down2,down3,right1,right2,right3,left1,left2,left3;
//
//    public int spriteCounter =0;
//    public int spriteNumber =1;
//    Player player;
//
//    public void getPlayerImage(){
//
//
//        try{
//            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/"+ Player.getNameClass() +"/up1.png")));
//            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + Player.getNameClass() + "/up2.png")));
//            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + Player.getNameClass() + "/up3.png")));
//            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + Player.getNameClass() + "/down1.png")));
//            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + Player.getNameClass() + "/down2.png")));
//            down3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + Player.getNameClass() + "/down3.png")));
//            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + Player.getNameClass() + "/left1.png")));
//            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + Player.getNameClass() + "/left2.png")));
//            left3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + Player.getNameClass() + "/left3.png")));
//            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + Player.getNameClass() + "/right1.png")));
//            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + Player.getNameClass() + "/right2.png")));
//            right3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/player/" + Player.getNameClass() + "/right3.png")));
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//    public void draw(Graphics g, ImageObserver observer) {
//
//        BufferedImage image = null;
//
//        switch (player.facingDirection) {
//            case Player.FORWARD -> {
//                if (spriteNumber==1) {
//                    image = up1;
//                }
//                if (spriteNumber==2) {
//                    image = up2;
//                }
//                if (spriteNumber==3) {
//                    image = up1;
//                }break;
//            }
//            case BACKWARD -> {
//                if (spriteNumber==1) {
//                    image = down1;
//                }
//                if (spriteNumber==2) {
//                    image = down2;
//                }
//                if (spriteNumber==3) {
//                    image = down3;
//                }break;
//            }
//            case LEFT -> {
//                if (spriteNumber==1) {
//                    image = left1;
//                }
//                if (spriteNumber==2) {
//                    image = left2;
//                }
//                if (spriteNumber==3) {
//                    image = left3;
//                }break;
//            }
//
//            case RIGHT -> {
//                if (spriteNumber==1) {
//                    image = right1;
//                }
//                if (spriteNumber==2) {
//                    image = right2;
//                }
//                if (spriteNumber==3) {
//                    image = right3;
//                }break;
//            }
//
//        }
//
//        g.drawImage(image, (pos.x * CreateMap.TILE_SIZE)+CreateMap.xOffset,
//                (pos.y * CreateMap.TILE_SIZE)+CreateMap.yOffset, observer);
//    }

}
