package com.Skills;

import com.PlayerType.Mage;

public class Fireball extends Skill {


    int hp;
    int duration = 60;
    static int cooldown = 50;
    boolean isUsed;

    public Fireball(int hp, int duration) {
        if (isUsed) {
            this.hp = hp * 2;
            this.duration -= duration;
        }
    }
    public void cooldownStart(){
        this.cooldown--;
    }
    public int getHp() {
        return hp;
    }

    public int getDuration() {
        return duration;
    }

    public static int getCooldown() {
        return cooldown;
    }

    public boolean isUsed() {
        return isUsed;
    }
}
