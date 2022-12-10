package com.Items;

import java.util.ArrayList;
import java.util.Random;

public class Drop {
    static Random gen = new Random();

    public static final int NUM_COINS = gen.nextInt(1,10);
    public static final int NUM_BOTTLES = gen.nextInt(1,10);

    ArrayList <Drop> drop = new ArrayList<>();
    private final ArrayList<Coin> coins;
    private final ArrayList<HealthPotion> potions;

    public Drop(){
        coins = DropCoin();
        potions = DropPotion();
    }

    public ArrayList<Coin> DropCoin () {
        ArrayList<Coin> coins = new ArrayList<>();
        ArrayList<HealthPotion> potions = new ArrayList<>();
        for (int i = 0; i < NUM_COINS; i++) {
            coins.add(new Coin());
            potions.add(new HealthPotion());
        }
        return coins;
    }
    public ArrayList<HealthPotion> DropPotion () {

        ArrayList<HealthPotion> potions = new ArrayList<>();
        for (int i = 0;i < NUM_BOTTLES; i++) {
            potions.add(new HealthPotion());
        }
        return potions;
    }
}
