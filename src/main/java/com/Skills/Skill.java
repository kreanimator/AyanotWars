package com.Skills;

import com.Units.Player;

import javax.swing.plaf.ColorUIResource;
import java.util.Objects;

public class Skill {


    static int cooldown = 50;



    static boolean isActivated = false;


    public Skill() {
        if (Objects.equals(Player.getNameClass(), "warrior")) {
            isActivated = true;
            System.out.println("Warrior perk is activated");
            PowerUp powerUp = new PowerUp();
        }
        if (Objects.equals(Player.getNameClass(), "warlock")) {
            isActivated = true;
            System.out.println("Warlock perk is activated");
            Curse curse = new Curse();
        }
        if (Objects.equals(Player.getNameClass(), "mage")) {
            isActivated = true;
            System.out.println("Mage perk is activated");
            Fireball fireball = new Fireball();
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

