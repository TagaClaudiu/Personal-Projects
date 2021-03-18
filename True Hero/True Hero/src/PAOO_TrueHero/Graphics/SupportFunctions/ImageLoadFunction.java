package PAOO_TrueHero.Graphics.SupportFunctions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
/*! \class ImageLoadFunction
    \brief loads images. that's it.

 */
//loads images. that's it.
public class ImageLoadFunction {

    public static BufferedImage loadImage (String path ) {
        try {
            return ImageIO.read(ImageLoadFunction.class.getResource(path)); //this is how we load an image in Java.
        }
        catch (IOException oof)
        {
            //afiseaza de ce a crapat
            oof.printStackTrace();
            System.exit(1); //exits game with code 1. if it failed to load, don't open game.
        }
        return null;
    }
}
