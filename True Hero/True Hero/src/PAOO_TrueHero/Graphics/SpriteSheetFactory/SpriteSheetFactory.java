package PAOO_TrueHero.Graphics.SpriteSheetFactory;

import PAOO_TrueHero.Graphics.SpriteSheet;

import java.awt.image.BufferedImage;
/*! \class SpriteSheetFactory
    \brief Self-explanatory.

 */
public class SpriteSheetFactory {
    public SpriteSheet makeSheet(BufferedImage image)
    {
        return new SpriteSheet(image);
    }
}
