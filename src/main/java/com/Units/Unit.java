package com.Units;

public abstract class Unit {

    double exp = 0;
    protected double hp = 0;
    int level = 0;
    double damage = 0;
    static boolean isKilled;

    public Unit() {

    }


    public static void attack() {

    }

//    void getDamage(int value) {
//        this.hp -= value;
//    }

    void addLevel(int value) {
        this.exp += value;
        if (exp > 1000) {
            level++;
        }
    }

    boolean isAlive() {
        return hp > 0;
    }

}

