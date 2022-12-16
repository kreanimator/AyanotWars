package com.Skills;

public class Heal {
    int hp;
    int duration = 60;
    boolean isUsed;

    public Heal(int hp, int duration) {
        if (isUsed) {
            this.hp = hp * 2;
            this.duration -= duration;
        }
    }
}
