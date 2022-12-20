package com.Skills;

import com.Units.Player;

import java.util.Objects;

public class Skill {


    static int cooldown = 50;



    static boolean isActivated = false;


    public Skill() {
        if (Objects.equals(Player.getNameClass(), "warrior")) {
            isActivated = true;
            System.out.println("Warrior perk is activated");
            // Skill = PowerUp;
        }
        if (Objects.equals(Player.getNameClass(), "warlock")) {
            isActivated = true;
            System.out.println("Warlock perk is activated");
            //Skill = Curse
        }
        if (Objects.equals(Player.getNameClass(), "mage")) {
            isActivated = true;
            //Skill = Fireball
            System.out.println("Mage perk is activated");
        }
        cooldown--;
    }

        public static int getCooldown() {

            return cooldown;
        }
    public static boolean isActivated() {
        return isActivated;
    }
    }

