package PAOO_TrueHero.MenuUI;

import java.awt.*;
import java.awt.image.BufferedImage;
/*! \class UIButton
    \brief Basically a hover-able, click-able button

 */
public class UIButton extends UIObject {

    private BufferedImage[] images;
    private ClickListener clicker;

    public UIButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height);
        this.images = images;
        this.clicker = clicker;
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics graphics) {
        if(mouse_hover)
            graphics.drawImage(images[0], (int) x, (int) y, width, height, null); //daca tii mouse-ul deasupra lui
        else
            graphics.drawImage(images[1], (int) x, (int) y, width, height, null); //daca nu tii
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
