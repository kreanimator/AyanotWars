package com.Skills;

import com.PlayerType.Warrior;

public class Curse extends Skill {


    int damage;
    int duration = 60;
    static int cooldown = 50;

    public Curse(int damage, int duration) {
        this.damage = damage / 2;
        this.duration -= duration;
    }

    public int getDamage() {
        return damage;
    }

    public int getDuration() {
        return duration;
    }

    public static int getCooldown() {
        return cooldown;
    }
}
