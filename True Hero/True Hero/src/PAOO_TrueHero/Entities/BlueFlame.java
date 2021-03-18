package PAOO_TrueHero.Entities;

import PAOO_TrueHero.Graphics.Animations;
import PAOO_TrueHero.Graphics.Assets;
import PAOO_TrueHero.Handler;

import java.awt.*;

public class BlueFlame extends Entity {

    private Animations fire;

    public BlueFlame(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        fire = new Animations(350, Assets.blue_flame);
    }

    @Override
    public void GetKilled()
    {

    }

    @Override
    public void Update() {
        fire.Update();
    }

    @Override
    public void Draw(Graphics graphics) {
        graphics.drawImage(fire.getCurrentFrame(), (int)x, (int)y, width, height, null);
    }

    public void setX( float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

}
