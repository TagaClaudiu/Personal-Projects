package PAOO_TrueHero.Entities.MovingEntities;

import PAOO_TrueHero.Entities.Entity;
import PAOO_TrueHero.Graphics.Animations;
import PAOO_TrueHero.Graphics.Assets;
import PAOO_TrueHero.Handler;
import PAOO_TrueHero.InventoryAndStatPage.InventoryAndStats;
import PAOO_TrueHero.Maps.World;
import PAOO_TrueHero.Maps.WorldEntities.WorldEntities;
import PAOO_TrueHero.States.State;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends MovingEntity {

    //STATS
    private int mana;
    private int max_mana;
    private int intelligence;


    //Animations
    private Animations WalkDown;
    private Animations WalkUp;
    private Animations WalkLeft;
    private Animations WalkRight;
    private int inLastMove;
    private int score;
    private int exp;
    private int level;
    private int exp_cap;
    private InventoryAndStats playerInventoryAndStats;
    private static Player self;

    //SINGLETON.
    private Player(Handler handler, float x, float y, int speed) {
        super(handler, x, y, MovingEntity.DEFAULT_CREATURE_WIDTH, MovingEntity.DEFAULT_CREATURE_HEIGHT);
        this.speed = speed;
        hitbox.x =18;
        hitbox.y = 32;
        hitbox.width = 32;
        hitbox.height = 32;

        //Animations for walking
        WalkDown = new Animations(250, Assets.player_down);//250 miliseconds
        WalkUp = new Animations(250, Assets.player_up);
        WalkLeft = new Animations(250, Assets.player_left);
        WalkRight = new Animations(250, Assets.player_right);

        inLastMove=4; //start facing down.
        playerInventoryAndStats = InventoryAndStats.getSelf(handler);

        //Stats

        max_health = 40;
        health = 40;
        max_mana = 20;
        mana = 20;
        intelligence = 1;
        defense = 0;

        score = 0;
        level = 1;
        exp = 0;
        exp_cap = 100;


    }

    public static Player getSelf(Handler handler, float x, float y, int speed)
    {
        if (self == null)
            self = new Player(handler, x, y, speed);
        return self;
    }

    public static void reset()
    {
        self = null;
    }

    public void gainExp(int exp)
    {
        this.exp += exp;
        if (this.exp >= exp_cap) //level up
        {
            level++;
            this.exp = this.exp - exp_cap;
            max_health+= 4;
            health+= 4;
            max_mana+= 2;
            mana+= 2;
            score+= level*200;
            exp_cap += exp_cap;
            defense += 1;
            intelligence += 1;
        }
    }

    public void restoreMana(int amount)
    {
        mana += amount;
        if (mana > max_mana)
            mana = max_mana;
        else if (mana < 0)
            mana = 0;
    }

    private void Actions()
    {
        xMove = 0;
        yMove = 0;
        //very important that they get reset;
        if (!playerInventoryAndStats.isActive()) {
            if (handler.getKeyManager().up)
                yMove = -speed;
            if (handler.getKeyManager().down)
                yMove = speed;
            if (handler.getKeyManager().left)
                xMove = -speed;
            if (handler.getKeyManager().right)
                xMove = speed;
            if (handler.getKeyManager().exit_to_menu) {
                State.setCurrentState(handler.getGame().getMenuState());
            }
            if (handler.getKeyManager().take)
                itemTake();
        }
    }

    private void itemTake()
    {
        Rectangle player_hitbox = getHitBox(0,0); //hitbox player
        Rectangle take_range = new Rectangle();
        int trSize = 20;
        take_range.width = trSize;
        take_range.height = trSize;
        switch(inLastMove)
        {
            case 1:{ //left
                take_range.x = player_hitbox.x  - trSize;
                take_range.y = player_hitbox.y + player_hitbox.height / 2 - trSize/2;
                break;
            }
            case 2:{ //right
                take_range.x = player_hitbox.x + player_hitbox.width;
                take_range.y = player_hitbox.y + player_hitbox.height/2 - trSize/2;
                break;
            }
            case 3:{ //up
                take_range.x = player_hitbox.x + player_hitbox.width / 2 - trSize /2;
                take_range.y = player_hitbox.y - trSize;
                break;
            }
            case 4:{ //down
                take_range.x = player_hitbox.x + player_hitbox.width / 2 - trSize /2;
                take_range.y = player_hitbox.y + player_hitbox.height;
                break;
            }
            default:
                return;
        }

        for (Entity entity : handler.getWorld().getEntityManager().getEntities())
        {
            if(!entity.equals(this)) //if it's not the player
            {
                if (entity.getHitBox(0, 0).intersects(take_range)) {
                    entity.GetKilled(); //"kill" the entity, only should happen to items to pick them up
                    return;
                }
            }
        }

    }

    private BufferedImage getCurrentAnimationFrame()
    {

        if(xMove<0){//moving left
            inLastMove=1;
            return WalkLeft.getCurrentFrame();
        }
        else if(xMove>0)//moving right
        {
            inLastMove=2;
            return WalkRight.getCurrentFrame();
        }
        else if(yMove<0)//moving up
        {
            inLastMove=3;
            return WalkUp.getCurrentFrame();
        }
        else if(yMove>0)//moving down
        {
            inLastMove=4;
            return WalkDown.getCurrentFrame();
        }
        else//stopped moving
        {
            switch(inLastMove){
                case 1: return Assets.playeridle_left;
                case 2: return Assets.playeridle_right;
                case 3: return Assets.playeridle_up;
                default: return Assets.playeridle_down;
            }

        }

    }

    @Override
    public void GetKilled()
    {
        active = false; //mark for manager to remove
        System.out.println("You died.\nFinal score: " + handler.getWorld().getEntityManager().getPlayer().getScore());
        //System.exit(0);
    }

    @Override
    public void Update()
    {
        //Animation
        WalkDown.Update();
        WalkUp.Update();
        WalkLeft.Update();
        WalkRight.Update();
        //Movement & Actions
        Actions();
        Move();
        //Camera
        handler.getGameCamera().FocusEntity(this);
        //we want the camera to center on player
        //Inventory+Stats
        playerInventoryAndStats.Update();
    }
    @Override
    public void Draw(Graphics graphics)
    {
        graphics.drawImage(getCurrentAnimationFrame(), (int)(x- handler.getGameCamera().getxOffset()),  (int)(y- handler.getGameCamera().getyOffset()), width, height, null);
        //HITBOX:
        //graphics.setColor(Color.red);
        //graphics.fillRect((int)(x + hitbox.x - handler.getGameCamera().getxOffset()), (int)(y + hitbox.y - handler.getGameCamera().getyOffset()), hitbox.width, hitbox.height);
    }

    public void drawInventoryAndStats(Graphics graphics)
    {
        playerInventoryAndStats.Draw(graphics);
    }
    public InventoryAndStats getPlayerInventoryAndStats() {
        return playerInventoryAndStats;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMax_mana() {
        return max_mana;
    }

    public void setMax_mana(int max_mana) {
        this.max_mana = max_mana;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExp_cap() {
        return exp_cap;
    }

    public void setExp_cap(int exp_cap) {
        this.exp_cap = exp_cap;
    }


}
