package PAOO_TrueHero.Entities.MovingEntities;

import PAOO_TrueHero.Graphics.Animations;
import PAOO_TrueHero.Handler;
import PAOO_TrueHero.States.CombatState;
import PAOO_TrueHero.States.GameState;
import PAOO_TrueHero.States.State;

import java.awt.*;

public abstract class Enemy extends MovingEntity {

    private Handler handler;
    private long oldTime, timer; //timers pt miscare
    private boolean moveType; //true = <---->  false = ^ v
    protected boolean diedinCombat; //true = died in combat. (pt cand iesi din combat, sa nu reintri in combat)
    protected String name;
    protected int damage;
    protected Animations general;

    public Enemy(Handler handler, float x, float y, int width, int height, boolean moveType) {
        super(handler, x, y, width, height);
        this.moveType = moveType;
        this.handler = handler;
        timer = 0;
        oldTime = System.currentTimeMillis();
        diedinCombat = false;
        name = "Default Monster";
        damage = 1;
    }


    public void Movement()
    {
        xMove = 0;
        yMove = 0;
        timer += System.currentTimeMillis() - oldTime; //timer gets the time since the last update got called
        oldTime = System.currentTimeMillis();

        if (moveType) //<--->
        {
            if(timer < 1500)
            {
                xMove+=speed;
                Move();
            }
            else if (timer > 1500 && timer < 3000)
            {
                xMove-=speed;
                Move();
            }
            else if (timer>3000)
            {
                timer = 0;
            }
        }
        else
        {
            if(timer < 1500)
            {
                yMove+=speed;
                Move();
            }
            else if (timer > 1500 && timer < 3000)
            {
                yMove-=speed;
                Move();
            }
            else if (timer>3000)
            {
                timer = 0;
            }
        }
    }

    private void enterCombat()
    {
        if (State.getCurrentState() == handler.getGame().getGameState())
        {
            handler.getGame().getCombatState().setEnemy(this);
            State.setCurrentState(handler.getGame().getCombatState());
        }
    }

    public void inPlayerRange() //checks if monster is in a certain range of the player
    {
        float playerX = getHandler().getWorld().getEntityManager().getPlayer().getX() + 18 + 16; //hitbox x, hitbox width/2
        float playerY = getHandler().getWorld().getEntityManager().getPlayer().getY() + 32 + 16;//hitbox y, hitbox height/2
        if (x + hitbox.x + hitbox.width/2. > playerX )
        {
            if (y+ hitbox.y + hitbox.height/2. > playerY)
            {
                if ((x + hitbox.x + hitbox.width/2.-playerX < 80) && (y+ hitbox.y + hitbox.height/2.-playerY < 80))
                {
                    if (!diedinCombat) {
                        handler.getGame().getCombatState().setEnemy(this);
                        State.setCurrentState(handler.getGame().getCombatState());
                    }
                }
            }
            else if (y <= playerY)
            {
                if ((x + hitbox.x + hitbox.width/2.-playerX < 80) && (playerY - y - hitbox.y - hitbox.height/2. < 80))
                {
                    if (!diedinCombat) {
                        handler.getGame().getCombatState().setEnemy(this);
                        State.setCurrentState(handler.getGame().getCombatState());
                    }
                }
            }

        }
        else if (x + hitbox.x + hitbox.width/2. <= playerX)
        {
            if (y+ hitbox.y + hitbox.height/2. > playerY)
            {
                if ((playerX - x - hitbox.x - hitbox.width/2. < 80) && (y + hitbox.y + hitbox.height/2. - playerY < 80))
                {
                    if (!diedinCombat) {
                        handler.getGame().getCombatState().setEnemy(this);
                        State.setCurrentState(handler.getGame().getCombatState());
                    }
                }
            }
            else if (y+ hitbox.y + hitbox.height/2. <= playerY)
            {
                if ((playerX - x - hitbox.x - hitbox.width/2. < 80) && (playerY - y -  hitbox.y - hitbox.height/2. < 80))
                {
                    if (!diedinCombat) {
                        handler.getGame().getCombatState().setEnemy(this);
                        State.setCurrentState(handler.getGame().getCombatState());
                    }
                }
            }
        }
    }

    public abstract void drawinCombat(Graphics graphics);

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public long getOldTime() {
        return oldTime;
    }

    public void setOldTime(long oldTime) {
        this.oldTime = oldTime;
    }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }

    public boolean isMoveType() {
        return moveType;
    }

    public void setMoveType(boolean moveType) {
        this.moveType = moveType;
    }

    public boolean isDiedinCombat() {
        return diedinCombat;
    }

    public void setDiedinCombat(boolean diedinCombat) {
        this.diedinCombat = diedinCombat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Animations getGeneral() {
        return general;
    }

    public void setGeneral(Animations general) {
        this.general = general;
    }
}
