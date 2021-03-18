package PAOO_TrueHero.Entities.MovingEntities;

import PAOO_TrueHero.Graphics.Animations;
import PAOO_TrueHero.Graphics.Assets;
import PAOO_TrueHero.Handler;
import PAOO_TrueHero.States.State;

import java.awt.*;

public class Minion extends Enemy {

    private Handler handler;

    public Minion(Handler handler, float x, float y, boolean moveType) {
        super(handler, x, y, 45+24, 66+33, moveType);
        this.handler = handler;
        health = 17;
        max_health = 17;
        general = new Animations(90, Assets.minion);
        speed = 3;
        hitbox.x = 45-16;
        hitbox.y = 33+16;
        hitbox.width = 16;
        hitbox.height = 16;
        damage = 6;
        defense = 1;
        name = "Legendary Evil's Servant";
    }

    @Override
    public void drawinCombat(Graphics graphics) {
        graphics.drawImage(general.getCurrentFrame(), 100 , 0 , width*2, height*2, null);
    }

    @Override
    public void GetKilled() {
        active = false;
        diedinCombat = true;
        handler.getWorld().getEntityManager().getPlayer().gainExp(35);
        handler.getWorld().getEntityManager().getPlayer().setScore(handler.getWorld().getEntityManager().getPlayer().getScore()+35);
        //if (State.getCurrentState() == handler.getGame().getCombatState())
        //    State.setCurrentState(handler.getGame().getGameState());
    }

    @Override
    public void Update() {
        general.Update();
        Movement();
        inPlayerRange();
    }

    @Override
    public void Draw(Graphics graphics) {
        graphics.drawImage(general.getCurrentFrame(), (int)(x-handler.getGameCamera().getxOffset()),  (int)(y-handler.getGameCamera().getyOffset()), width, height, null);
        //graphics.setColor(Color.red);
        //graphics.fillRect((int)(x + hitbox.x - handler.getGameCamera().getxOffset()), (int)(y + hitbox.y - handler.getGameCamera().getyOffset()), hitbox.width, hitbox.height);

    }
}
