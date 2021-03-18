package PAOO_TrueHero.States;

import PAOO_TrueHero.Entities.MovingEntities.Enemy;
import PAOO_TrueHero.Graphics.Assets;
import PAOO_TrueHero.Handler;
import PAOO_TrueHero.MenuUI.ClickListener;
import PAOO_TrueHero.MenuUI.UIButton;
import PAOO_TrueHero.MenuUI.UIManager;

import java.awt.*;
/*! \class MenuState
    \brief The main menu. Where you start the game from.

 */
public class MenuState extends State {

    protected UIManager uiManager;

    //Entity flame;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = UIManager.getSelf(handler);
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.addObject(new UIButton((int)(handler.getGame().getWidth()/4 + handler.getGame().getWidth()/12), (int)(handler.getGame().getHeight()/3 +handler.getGame().getHeight()/6), 316, 125, Assets.start, new ClickListener(){
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setCurrentState(handler.getGame().getGameState());
            }
        }));
        uiManager.addObject(new UIButton((int)(handler.getGame().getWidth()/4+ handler.getGame().getWidth()/12), (int)(handler.getGame().getHeight()/3 + +handler.getGame().getHeight()/3), 316, 125, Assets.options, new ClickListener(){
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setCurrentState(handler.getGame().getSettingsState());
            }
        }));
        uiManager.addObject(new UIButton((int)(handler.getGame().getWidth()/4+ handler.getGame().getWidth()/12), (int)(handler.getGame().getHeight()/3 + handler.getGame().getHeight()/2), 316, 125, Assets.exit, new ClickListener(){
            @Override
            public void onClick() {
                System.exit(0); //nice.
            }
        }));
    }


    @Override
    public void Update()
    {
        //Acest Update se intampla doar cand State = MenuState. Asta se intampla doar cand incepi jocul sau cand apesi ESC in gameState.
        if(handler.getMouseManager().getUiManager() == null) //daca a fost scos uiManager (ai intrat in gameState, si ai revenit)
            handler.getMouseManager().setUiManager(uiManager); //il resetezi
        uiManager.Update();
        //flame.Update();
    }

    @Override
    public void Draw(Graphics graphics)
    {
        graphics.setColor(Color.black);
        graphics.fillRect(0,0, handler.getGame().getWidth(), handler.getGame().getHeight());
        graphics.drawImage(Assets.title, handler.getGame().getWidth()/4, handler.getGame().getHeight()/5, 1456/3, 359/3, null);
        uiManager.Draw(graphics);
        //flame.setX(handler.getMouseManager().getMouseX());
        //flame.setY(handler.getMouseManager().getMouseY());
        //flame.Draw(graphics);

    }

    @Override
    public void setEnemy(Enemy enemy) {

    }

    public UIManager getUiManager() {
        return uiManager;
    }
}
