package PAOO_TrueHero.Entities.MovingEntities;

import PAOO_TrueHero.Graphics.Animations;
import PAOO_TrueHero.Graphics.Assets;
import PAOO_TrueHero.Handler;
import PAOO_TrueHero.States.State;

import java.awt.*;

public class Tentacle extends Enemy {

    private Handler handler;

    public Tentacle(Handler handler, float x, float y, boolean moveType) {
        super(handler, x, y, 50, 180, moveType);
        this.handler = handler;
        health = 55;
        max_health = 55;
        general = new Animations(60, Assets.tentacle);
        speed = 0;
        hitbox.x = 10;
        hitbox.y = 90+45;
        hitbox.width = 32;
        hitbox.height = 32;
        damage = 6;
        defense = 3;
        name = "Protector of the Tomb";
    }

    @Override
    public void drawinCombat(Graphics graphics) {
        graphics.drawImage(general.getCurrentFrame(), 105 , 0 , width*2, height*3/2, null);
    }

    @Override
    public void GetKilled() {
        active = false;
        diedinCombat = true;
        handler.getWorld().getEntityManager().getPlayer().gainExp(125);
        handler.getWorld().getEntityManager().getPlayer().setScore(handler.getWorld().getEntityManager().getPlayer().getScore()+125);
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
