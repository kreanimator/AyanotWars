package com.Tiles;

import com.CreateMap;

import java.awt.*;
import  com.Units.*;
import java.util.ArrayList;

public class AttackSprite {
    Player player;




    public void drawAttackSprite(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(java.awt.Color.RED);
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(
                RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        int x = player.getPos().x * CreateMap.TILE_SIZE;

        g2.fillOval(x, player.getPos().y * CreateMap.TILE_SIZE - 10, 40, 5);
    }
}
