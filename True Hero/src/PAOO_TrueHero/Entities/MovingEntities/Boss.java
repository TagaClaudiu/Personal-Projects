package PAOO_TrueHero.Entities.MovingEntities;

import PAOO_TrueHero.Graphics.Animations;
import PAOO_TrueHero.Graphics.Assets;
import PAOO_TrueHero.Handler;
import PAOO_TrueHero.States.State;

import java.awt.*;

public class Boss extends Enemy {

    private Handler handler;
    private int stage;

    public Boss(Handler handler, float x, float y, boolean moveType) {
        super(handler, x, y, 85*3/2, 94*3/2, moveType);
        this.handler = handler;
        health = 80;
        max_health = 80;
        general = new Animations(90, Assets.boss1);
        speed = 0;
        hitbox.x = 85*3/2 - 80;
        hitbox.y = 94*3/2 - 30;
        hitbox.width = 32;
        hitbox.height = 32;
        damage = 7;
        defense = 3;
        name = "Ancient Arch-Mage";
        stage = 1;
    }

    @Override
    public void drawinCombat(Graphics graphics) {
        graphics.drawImage(general.getCurrentFrame(), 45 , 0 , width*2, height*2, null);
    }

    @Override
    public void GetKilled() {

        stage++;
        if (stage == 2)
        {
            health = 65;
            max_health = 65;
            general = new Animations(120, Assets.boss2);
            damage = 10;
            defense = 10;
            name = "The Last Keyhole";
        }
        else if (stage == 3)
        {
            health = 200;
            max_health = 200;
            general = new Animations(90, Assets.boss3);
            damage = 15;
            defense = 0;
            name = "Legendary Evil";
        }
        else
        {
            active = false;
            diedinCombat = true;
            handler.getWorld().getEntityManager().getPlayer().setScore(handler.getWorld().getEntityManager().getPlayer().getScore()+5000);
            System.out.println("You win!\nFinal score: " + handler.getWorld().getEntityManager().getPlayer().getScore());
            //System.exit(0);
        }
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
