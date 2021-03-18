package PAOO_TrueHero.Graphics.SpriteSheetFactory;

import PAOO_TrueHero.Graphics.SpriteSheet;

import java.awt.image.BufferedImage;

public class SpriteSheetFactory {
    public SpriteSheet makeSheet(BufferedImage image)
    {
        return new SpriteSheet(image);
    }
}
