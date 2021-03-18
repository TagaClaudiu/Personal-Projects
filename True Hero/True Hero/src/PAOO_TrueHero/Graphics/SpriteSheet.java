package PAOO_TrueHero.Graphics;

import java.awt.image.BufferedImage;
/*! \class SpriteSheet
    \brief Almost unneeded class, but helpful. Crops Spritesheets

 */
public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet)
    {
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int width, int height)
    {
        return sheet.getSubimage(x, y, width, height); //crops image from (x,y) to (x+width, y+height) and returns it
    }
}
