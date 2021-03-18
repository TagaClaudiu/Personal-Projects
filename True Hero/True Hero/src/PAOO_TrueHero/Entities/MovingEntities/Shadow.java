package PAOO_TrueHero.Entities.MovingEntities;

import PAOO_TrueHero.Graphics.Animations;
import PAOO_TrueHero.Graphics.Assets;
import PAOO_TrueHero.Handler;
import PAOO_TrueHero.States.State;

import java.awt.*;

public class Shadow extends Enemy {

    private Handler handler;


    public Shadow(Handler handler, float x, float y, boolean moveType) {
        super(handler, x, y, 120, 105, moveType);
        this.handler = handler;
        health = 35;
        max_health = 35;
        general = new Animations(60, Assets.shadow);
        speed = 4;
        hitbox.x = 85-32;
        hitbox.y = 35+32;
        hitbox.width = 32;
        hitbox.height = 32;
        damage = 9;
        name = "Demon Born Of Shadows";
    }

    @Override
    public void drawinCombat(Graphics graphics) {
        graphics.drawImage(general.getCurrentFrame(), 45 , 0 , width*2, height*2, null);
    }

    @Override
    public void GetKilled() {
        active = false;
        diedinCombat = true;
        handler.getWorld().getEntityManager().getPlayer().gainExp(100);
        handler.getWorld().getEntityManager().getPlayer().setScore(handler.getWorld().getEntityManager().getPlayer().getScore()+100);
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
