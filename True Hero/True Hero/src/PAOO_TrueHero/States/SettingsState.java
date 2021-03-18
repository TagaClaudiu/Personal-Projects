package PAOO_TrueHero.States;

import PAOO_TrueHero.Entities.MovingEntities.Enemy;
import PAOO_TrueHero.Graphics.Assets;
import PAOO_TrueHero.Graphics.SupportFunctions.StringDrawFunction;
import PAOO_TrueHero.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
/*! \class SettingsState
    \brief Shows controls

 */
public class SettingsState extends State{


    public SettingsState(Handler handler) {
        super(handler);

    }

    @Override
    public void Update()
    {
        if (handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_F))
            State.setCurrentState(handler.getGame().getMenuState());
    }

    @Override
    public void Draw(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, handler.getGame().getWidth(), handler.getGame().getHeight());
        graphics.drawImage(Assets.background, 200, 125, 640, 480, null);
        StringDrawFunction.StringDraw(graphics, "Controls", 200 + 40, 125 + 40, Color.white, Assets.font36);
        StringDrawFunction.StringDraw(graphics, "W - move up", 200 + 40, 125 + 40 * 2, Color.white, Assets.font36);
        StringDrawFunction.StringDraw(graphics, "A - move to the left", 200 + 40, 125 + 40 * 3, Color.white, Assets.font36);
        StringDrawFunction.StringDraw(graphics, "S - move down", 200 + 40, 125 + 40 * 4, Color.white, Assets.font36);
        StringDrawFunction.StringDraw(graphics, "D - move to the right", 200 + 40, 125 + 40 * 5, Color.white, Assets.font36);
        StringDrawFunction.StringDraw(graphics, "Q - open/close inventory", 200 + 40, 125 + 40 * 6, Color.white, Assets.font36);
        StringDrawFunction.StringDraw(graphics, "E - take/drink potions", 200 + 40, 125 + 40 * 7, Color.white, Assets.font36);
        StringDrawFunction.StringDraw(graphics, "F - take selected action / end turn", 200 + 40, 125 + 40 * 8, Color.white, Assets.font36);
        StringDrawFunction.StringDraw(graphics, "W/S - select action/potion", 200 + 40, 125 + 40 * 9, Color.gray, Assets.font36);
        StringDrawFunction.StringDraw(graphics, "Inventory: A - inv; D - stats", 200 + 40, 125 + 40 * 10, Color.gray, Assets.font36);

        StringDrawFunction.StringDraw(graphics, "Press F to return to Menu", 200 + 40, 125 + 40 * 11 + 20, Color.red, Assets.font36);
    }
    @Override
    public void setEnemy(Enemy enemy) {

    }
}
