package com.Items;

import com.CreateMap;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.CreateMap;
import com.Tiles.Skull;
import com.Units.Enemy;

public class Drop extends Item {
    static Random gen = new Random();
    Enemy enemy;

    public static final int NUM_COINS = gen.nextInt(1, 10);
    public static final int NUM_BOTTLES = gen.nextInt(1, 10);
    private final Point pos;

    ArrayList<Drop> drop = new ArrayList<>();

    public Drop(int x, int y) {
        super(x,y);
        loadImage();
        pos = new Point(x, y);
        ArrayList<Coin> coins = DropCoin();
        ArrayList<HealthPotion> potions = DropPotion();
        for (int i = 0; i < NUM_COINS;i++){
            for(int j = 0; j < NUM_BOTTLES; j++){
                if(enemy.isKilled()){
                    coins.add(new Coin(x,y));
                    potions.add(new HealthPotion(x,y));
                }
            }
        }
    }


    public static String getName() {
        return null;
    }

    public ArrayList<Coin> DropCoin() {
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < NUM_COINS; i++) {
            coins.add(new Coin(pos.x, pos.y));
        }
        return coins;
    }

    public ArrayList<HealthPotion> DropPotion() {

        ArrayList<HealthPotion> potions = new ArrayList<>();
        for (int i = 0; i < NUM_BOTTLES; i++) {
            potions.add(new HealthPotion(pos.x, pos.y));
        }
        return potions;
    }

}
