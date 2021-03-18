package PAOO_TrueHero.Entities.MovingEntities;

import PAOO_TrueHero.Graphics.Animations;
import PAOO_TrueHero.Graphics.Assets;
import PAOO_TrueHero.Handler;
import PAOO_TrueHero.States.State;

import java.awt.*;

public class HornedMage extends Enemy {

    private Handler handler;

    public HornedMage(Handler handler, float x, float y, boolean moveType) {
        super(handler, x, y, 180, 150, moveType);
        this.handler = handler;
        health = 20;
        max_health = 20;
        general = new Animations(60, Assets.horned_mage);
        speed = 2;
        hitbox.x = 58; //90-32
        hitbox.y = 106; //150-44
        hitbox.width = 40;
        hitbox.height = 32;
        damage = 4;
        defense = 1;
        name = "Horned Magus";
    }

    @Override
    public void GetKilled()
    {
        active = false;
        diedinCombat = true;
        handler.getWorld().getEntityManager().getPlayer().gainExp(55);
        handler.getWorld().getEntityManager().getPlayer().setScore(handler.getWorld().getEntityManager().getPlayer().getScore()+50);
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

    @Override
    public void drawinCombat(Graphics graphics)
    {
        graphics.drawImage(general.getCurrentFrame(), 0 , 0 , width*2, height*2, null);
    }
}

