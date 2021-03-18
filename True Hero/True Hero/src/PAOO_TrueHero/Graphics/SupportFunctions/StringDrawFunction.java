package PAOO_TrueHero.Graphics.SupportFunctions;

import java.awt.*;
import java.io.File;
import java.io.IOException;
/*! \class StringDrawFunction
    \brief For fonts. Loads fonts, and uses them to draw a text.

 */
public class StringDrawFunction {

    public static void StringDraw(Graphics graphics, String text, int x, int y, Color color, Font font)
    {
        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(text, x, y);

    }

    public static Font FontLoad(String path, float size)
    {
        try {
            //revolution = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("REVOLUTION.ttf"));
            //return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
            return Font.createFont(Font.TRUETYPE_FONT, StringDrawFunction.class.getResourceAsStream(path)).deriveFont(Font.PLAIN, size);
        }
        catch (FontFormatException | IOException oof) //collapsed
        {
            oof.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
