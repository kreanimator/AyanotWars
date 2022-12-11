package com.Skills;

public class Heal {
    int hp;
    int duration = 60;

    public Heal(int hp, int duration) {
        this.hp = hp * 2;
        this.duration -= duration;
    }
}
