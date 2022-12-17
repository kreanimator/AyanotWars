package com.Units;

import com.CreateMap;
import com.Interfaces.PlayerChoose;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;
import java.util.Random;

public class Npc {
    private BufferedImage image;
    static Random random = new Random();
    static int x = random.nextInt(CreateMap.ROWS);

    static int y = random.nextInt(CreateMap.COLUMNS);
    private static final Point pos = new Point(x,y);

    public Npc(int [][] obstacles) {

        
        loadImage();
        Point pos1 = pos;
        obstacles[pos.x][pos.y] = 2;

    }
    public void setNpcName(Graphics g) {
        // set the text to be displayed
        Font myFont = null;
        try {
            InputStream inputStream = new BufferedInputStream(
                    new FileInputStream("src/main/resources/font/GravityBold8.ttf"));

            myFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);


        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        String name = "Nick";

        // we need to cast the Graphics to Graphics2D to draw nicer text
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setColor(Color.WHITE);
        assert myFont != null;
        g2d.setFont(myFont.deriveFont(Font.BOLD,10f));
        int x = (pos.x * CreateMap.TILE_SIZE)+10 + CreateMap.xOffset;

        int y = (pos.y * CreateMap.TILE_SIZE - 10)+CreateMap.yOffset;
        g2d.drawString(name, x, y);
    }
    private void loadImage() {
        try {
            Random rand = new Random();
            int randomNum = rand.nextInt((3 - 1) + 1) +1;
            File enemyImageFile = new File("src/main/resources/images/npc/"+randomNum+".png");
            image = ImageIO.read(enemyImageFile);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {

        g.drawImage(
                image,
                (pos.x * CreateMap.TILE_SIZE)+CreateMap.xOffset,
                (pos.y * CreateMap.TILE_SIZE)+CreateMap.yOffset,
                observer
        );
    }
    public static Point getPos() {
        return pos;
    }
}
