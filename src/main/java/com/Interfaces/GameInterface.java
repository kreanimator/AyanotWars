package com.Interfaces;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import com.Items.Item;
import com.Skills.Skill;
import com.Units.*;
import com.CreateMap;
import javax.imageio.ImageIO;



public class GameInterface {
    static BufferedImage gh,spellicon,bag;
    static Point pos=null;
    static Item item;


        public GameInterface() {

            int x = CreateMap.xOffset-20;
            int y = CreateMap.yOffset+CreateMap.HEIGHT-CreateMap.TILE_SIZE;
            getImage();

            pos = new Point(x, y);
        }
        public void getImage(){
            try{
                gh = ImageIO.read(Objects.requireNonNull(GameInterface.class.getResourceAsStream("/images/items/goblinhead.png")));
                bag = ImageIO.read(Objects.requireNonNull(GameInterface.class.getResourceAsStream("/images/items/bag.png")));
                if(Objects.equals(Player.getNameClass(), "warrior")){
                    spellicon= ImageIO.read(Objects.requireNonNull(GameInterface.class.getResourceAsStream("/images/skills/warrior.png")));
                }
                if(Objects.equals(Player.getNameClass(), "warlock")){
                    spellicon= ImageIO.read(Objects.requireNonNull(GameInterface.class.getResourceAsStream("/images/skills/warlock.png")));
                }
                if(Objects.equals(Player.getNameClass(), "mage")){
                    spellicon= ImageIO.read(Objects.requireNonNull(GameInterface.class.getResourceAsStream("/images/skills/mage.png")));
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    public static void draw(Graphics g, ImageObserver observer) {
            
        g.drawImage(spellicon, pos.x + (CreateMap.TILE_SIZE * 7), pos.y - (CreateMap.TILE_SIZE / 6), observer);
        g.setColor(new Color(0, 0, 0, 200));
        int height = Skill.getCooldown();
        if (Skill.isActivated()) {
            g.fillRect(pos.x + (CreateMap.TILE_SIZE * 7), pos.y - (CreateMap.TILE_SIZE / 6), CreateMap.TILE_SIZE, height);
        }
    }

        public static void drawActionPanel(Graphics g) {

            Font myFont = null;
            try {
                InputStream inputStream = new BufferedInputStream(
                        new FileInputStream("src/main/resources/font/GravityBold8.ttf"));

                myFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);

            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            // set the text to be displayed
            String text = "EXP: ";
            String textLvl = "Level " + Player.getLevel();
            String hplvl = "HP: ";
            String mplvl = "MP: ";
            String inv = "For inventory press 'i'";


            // we need to cast the Graphics to Graphics2D to draw nicer text
            Graphics2D g2 = (Graphics2D) g;
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(
                    RenderingHints.KEY_FRACTIONALMETRICS,
                    RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            // set the text color and font
            g2d.setColor(java.awt.Color.BLACK);
            assert myFont != null;
            g2d.setFont(myFont.deriveFont(Font.BOLD,15f));
            // draw the score in the bottom center of the screen
            // https://stackoverflow.com/a/27740330/4655368
            // the text will be contained within this rectangle.
            // here I've sized it to be the entire bottom row of board tiles
            // draw the string
            g2d.drawString(text, pos.x + (CreateMap.WIDTH/2)-30, pos.y + CreateMap.TILE_SIZE/2);
            // determine the y coordinate for the text
            // (note we add the ascent, as in java 2d 0 is top of the screen)
            g2d.drawString(textLvl, pos.x + CreateMap.WIDTH/3, (pos.y + CreateMap.TILE_SIZE/2));
            // determine the y coordinate for the text
            // (note we add the ascent, as in java 2d 0 is top of the screen)
            g2d.drawString(hplvl,  pos.x + 10, (pos.y+ CreateMap.TILE_SIZE)- CreateMap.TILE_SIZE);

            //Getting a changeble width for HP bar
            int widthhp = Player.getHP();
            //Getting a changeble width for MP bar
            int widthmp = Player.getMp();
            //Drawing a frame for MP bar.
            g2.drawLine(pos.x + 50, pos.y +9, pos.x + widthmp+50, pos.y +9);
            g2.drawLine(pos.x + 50, pos.y+ 30, pos.x + widthmp+50, pos.y+ 30);
            g2.drawLine(pos.x + 49, pos.y + 9 , pos.x + 49, pos.y + 30);
            g2.drawLine(pos.x + (widthmp+50), pos.y + 9, pos.x + (widthmp+50), pos.y + 30);
            //Drawing a frame for EXP bar.
            g2.drawLine(pos.x + (CreateMap.WIDTH/2 + 50), pos.y +9, pos.x + (CreateMap.WIDTH/2 + 150), pos.y +9);
            g2.drawLine(pos.x + (CreateMap.WIDTH/2 + 50), pos.y+ 30, pos.x + (CreateMap.WIDTH/2 + 150), pos.y+ 30);
            g2.drawLine(pos.x + (CreateMap.WIDTH/2 + 49), pos.y + 9 , pos.x + (CreateMap.WIDTH/2 + 49), pos.y + 30);
            g2.drawLine(pos.x + (CreateMap.WIDTH/2 + 150), pos.y + 9, pos.x + (CreateMap.WIDTH/2 + 150), pos.y + 30);

            //Drawing a frame for HP bar
            g2.drawLine(pos.x + 49, pos.y +3, pos.x + widthhp+50, pos.y+3 );
            g2.drawLine(pos.x + 50, pos.y-18, pos.x + widthhp+50, pos.y-18);
            g2.drawLine(pos.x + 49, pos.y + 3 , pos.x + 49, pos.y -18);
            g2.drawLine(pos.x + (widthhp+50), pos.y + 3, pos.x + (widthhp+50), pos.y -18);

            g2d.drawString(inv, pos.x + (CreateMap.WIDTH-(CreateMap.WIDTH/3)), pos.y + CreateMap.TILE_SIZE/2);
            g2d.drawString(mplvl, pos.x + 10, (pos.y+ CreateMap.TILE_SIZE/2) + 2);

            //Drawing health bar
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

            g2.fillRect(pos.x + 50, pos.y -17, widthhp, 20);
            //Drawing mana bar

            g2.setColor(Color.BLUE);
            g2.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(
                    RenderingHints.KEY_FRACTIONALMETRICS,
                    RenderingHints.VALUE_FRACTIONALMETRICS_ON);


            g2.fillRect(pos.x + 50, pos.y +10, widthmp, 20);
            //Drawing experience bar
            g2.setColor(Color.GREEN);
            g2.setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setRenderingHint(
                    RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(
                    RenderingHints.KEY_FRACTIONALMETRICS,
                    RenderingHints.VALUE_FRACTIONALMETRICS_ON);

            int width = Player.getExperience() / 10;
            g2.fillRect((pos.x + CreateMap.WIDTH/2) +50, pos.y +10, width, 20);
        }

    public void tick() {


        if (pos.x < 0) {
            pos.x = 0;
        } else if (pos.x >= CreateMap.WIDTH) {
            pos.x = (CreateMap.WIDTH);
        }
        if (pos.y < 0) {
            pos.y = 0;
        } else if (pos.y >= CreateMap.HEIGHT) {
            pos.y = (CreateMap.HEIGHT);
        }
    }
}




