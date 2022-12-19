package com.Skills;

import com.PlayerType.Warrior;

public class PowerUp extends Warrior {
    int damage;
    int duration = 60;
     int cooldown = 50;

    public PowerUp(int damage, int duration) {
        this.damage = damage * 2;
        this.duration -= duration;
    }
    public void cooldownStart(){
        this.cooldown--;
    }

}
