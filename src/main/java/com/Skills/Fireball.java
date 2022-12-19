package com.Skills;

import com.PlayerType.Mage;

public class Fireball extends Mage {
    int hp;
    int duration = 60;
    int cooldown = 50;
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
}
