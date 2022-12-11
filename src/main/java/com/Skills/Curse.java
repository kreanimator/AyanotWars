package com.Skills;

public class Curse {
    int damage;
    int duration = 60;

    public Curse(int damage, int duration) {
        this.damage = damage / 2;
        this.duration -= duration;
    }
}
