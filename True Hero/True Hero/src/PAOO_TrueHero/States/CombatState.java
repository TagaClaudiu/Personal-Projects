package PAOO_TrueHero.States;

import PAOO_TrueHero.Entities.MovingEntities.Enemy;
import PAOO_TrueHero.Entities.MovingEntities.MovingEntity;
import PAOO_TrueHero.Entities.MovingEntities.Player;
import PAOO_TrueHero.Graphics.Assets;
import PAOO_TrueHero.Graphics.SupportFunctions.StringDrawFunction;
import PAOO_TrueHero.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
/*! \class CombatState
    \brief When you encounter an enemy, you fight it.

 */
public class CombatState extends State{

    private Player player;
    private Enemy enemy;
    private boolean yourTurn = true;
    private int choices = 0;
    private long oldTime, timer;
    boolean didDamage = false;


    public CombatState(Handler handler) {

        super(handler);
        player = handler.getWorld().getEntityManager().getPlayer();
        timer = 0;
    }
    /*! \fn  private void doChoice()
           \brief when it's players turn, he gets to chose an action.
           */
    private void doChoice()
    {
        switch(choices)
        {
            case 0: enemy.takeDamage(4 + player.getIntelligence());
                    yourTurn = false;
                    break;
            case 1: if(player.getMana() >= 5 - player.getIntelligence()/2)
                    {
                        enemy.takeDamage(14 + (int)(player.getIntelligence()*1.2));
                        player.restoreMana(-5 + player.getIntelligence()/2);
                        yourTurn = false;
                    }
                    break;
            case 2: if(player.getMana() >= 10 - player.getIntelligence()/2)
                    {
                        enemy.takeDamage(24 + (int)(player.getIntelligence()*1.4));
                        player.restoreMana(-10 + player.getIntelligence()/2);
                        yourTurn = false;
                    }
                    break;
            default: player.getPlayerInventoryAndStats().setActive(true);
        }

    }

    @Override
    public void Update()
    {
        if(player != null)
        {
            player.getPlayerInventoryAndStats().Update();
            if (!player.isActive())
            {
                if (handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_F))
                    System.exit(0);
            }


        if (yourTurn)
        {
            if (!player.getPlayerInventoryAndStats().isActive()) {
                if (handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_UP) || handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_W))
                    choices--;
                if (handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_DOWN) || handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_S))
                    choices++;
                if(handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_ENTER) || handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_F))
                {
                    doChoice();
                    oldTime = System.currentTimeMillis();
                    timer = 0;
                }


                if (choices < 0)
                    choices = 3;
                else if (choices >= 4)
                    choices = 0;
            }
            else {
                if (handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_ENTER) || handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_F)) { //Drink your potions, then continue.
                    player.getPlayerInventoryAndStats().setActive(false);
                    yourTurn = false;
                }
            }
        }
        else
        {
            timer += System.currentTimeMillis() - oldTime; //timer gets the time since the last update got called
            oldTime = System.currentTimeMillis();
            if (timer > 1500) {
                player.takeDamage(enemy.getDamage());
                yourTurn = true;
                timer = 0;
            }
        }
        }


        if(enemy != null)
        {
            enemy.getGeneral().Update();
            if (enemy.isDiedinCombat())
            {
                if (enemy.getMax_health() != 200) {
                    if (timer > 1350) {
                        yourTurn = true;
                        if (State.getCurrentState() == handler.getGame().getCombatState())
                            State.setCurrentState(handler.getGame().getGameState());
                    }
                }
                else
                {
                    {
                        if (timer < 1350 && timer > 250) {

                            if (handler.getKeyManager().keyPressedOneFrame(KeyEvent.VK_F))
                            System.exit(0);
                        }
                        else if (timer > 1350)
                        {
                            timer = 250;
                        }
                    }
                }
            }
        }

    }

    @Override
    public void Draw(Graphics graphics)
    {
        graphics.setColor(Color.black);
        graphics.fillRect(0,0, handler.getGame().getWidth(), handler.getGame().getHeight());
        graphics.drawImage(Assets.bg_top, handler.getGame().getWidth() - 680, handler.getGame().getHeight() - 240, 640, 240, null);
        graphics.drawImage(Assets.bg_bottom, handler.getGame().getWidth() - 680, 0, 640, 240, null);

        if (choices == 0)
            StringDrawFunction.StringDraw(graphics, ">Mana Flare: " + Integer.toString(4 + player.getIntelligence()) + " DMG / 0 MANA COST", handler.getGame().getWidth() - 680 + 40, handler.getGame().getHeight() - 240 + 40, Color.red, Assets.font36);
        else
            StringDrawFunction.StringDraw(graphics, "Mana Flare", handler.getGame().getWidth() - 680 + 40, handler.getGame().getHeight() - 240 + 40, Color.white, Assets.font36);
        if (choices == 1)
            StringDrawFunction.StringDraw(graphics, ">Blue Fire: " + Integer.toString(14 + (int)(player.getIntelligence()*1.2)) + " DMG / " + Integer.toString(5 - player.getIntelligence()/2) + " MANA COST", handler.getGame().getWidth() - 680 + 40, handler.getGame().getHeight() - 240 + 80, Color.red, Assets.font36);
        else
            StringDrawFunction.StringDraw(graphics, "Blue Fire", handler.getGame().getWidth() - 680 + 40, handler.getGame().getHeight() - 240 + 80, Color.white, Assets.font36);
        if (choices == 2)
            StringDrawFunction.StringDraw(graphics, ">Eclipse: " + Integer.toString(24 + (int)(player.getIntelligence()*1.4)) + " DMG / " + Integer.toString(10 - player.getIntelligence()/2) + " MANA COST", handler.getGame().getWidth() - 680 + 40, handler.getGame().getHeight() - 240 + 120, Color.red, Assets.font36);
        else
             StringDrawFunction.StringDraw(graphics, "Eclipse", handler.getGame().getWidth() - 680 + 40, handler.getGame().getHeight() - 240 + 120, Color.white, Assets.font36);
        if (choices == 3)
            StringDrawFunction.StringDraw(graphics, ">Items: opens inventory.", handler.getGame().getWidth() - 680 + 40, handler.getGame().getHeight() - 240 + 160, Color.red, Assets.font36);
        else
            StringDrawFunction.StringDraw(graphics, "Items", handler.getGame().getWidth() - 680 + 40, handler.getGame().getHeight() - 240 + 160, Color.white, Assets.font36);


        StringDrawFunction.StringDraw(graphics, "Health: " + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getHealth()) + "/" + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getMax_health()), handler.getGame().getWidth() - 680 + 40, handler.getGame().getHeight() - 240 + 200, Color.pink, Assets.font36);
        StringDrawFunction.StringDraw(graphics, "Mana: " + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getMana()) + "/" + Integer.toString(handler.getWorld().getEntityManager().getPlayer().getMax_mana()), handler.getGame().getWidth() - 680 + 40, handler.getGame().getHeight(), Color.cyan, Assets.font36);



        if (!enemy.isDiedinCombat()) {
            StringDrawFunction.StringDraw(graphics, "This fight is unavoidable. Fight will all you've got.", handler.getGame().getWidth()/2 - 425, handler.getGame().getHeight()/2-40, Color.gray, Assets.font36);
            if (yourTurn)
                StringDrawFunction.StringDraw(graphics, "Your turn.", handler.getGame().getWidth() / 2 - 125, handler.getGame().getHeight() / 2, Color.white, Assets.font36);
            else
                StringDrawFunction.StringDraw(graphics, "Their turn.", handler.getGame().getWidth() / 2 - 125, handler.getGame().getHeight() / 2, Color.white, Assets.font36);
        }
        else
        {
            if (enemy.getMax_health() != 200)
                StringDrawFunction.StringDraw(graphics, "Prey slaughtered. You are victorious!", handler.getGame().getWidth() / 2 - 325, handler.getGame().getHeight() / 2, Color.white, Assets.font36);
            else {
                StringDrawFunction.StringDraw(graphics, "The soul of this beast is yours.", handler.getGame().getWidth() / 2 - 275, handler.getGame().getHeight() / 2, Color.white, Assets.font36);
                StringDrawFunction.StringDraw(graphics, "NOONE CAN STAND IN YOUR WAY NOW.", handler.getGame().getWidth() / 2 - 325, handler.getGame().getHeight() / 2 + 40, Color.red, Assets.font36);
                StringDrawFunction.StringDraw(graphics, "Final score: " + handler.getWorld().getEntityManager().getPlayer().getScore(), handler.getGame().getWidth() / 2 - 165, handler.getGame().getHeight() / 2 + 80, Color.white, Assets.font36);
                StringDrawFunction.StringDraw(graphics, "press F to close game.", handler.getGame().getWidth() / 2 - 225, handler.getGame().getHeight() / 2 + 120, Color.gray, Assets.font36);
            }
        }


        if (enemy != null)
        {
            enemy.drawinCombat(graphics);
            StringDrawFunction.StringDraw(graphics, enemy.getName(), handler.getGame().getWidth() - 680 + 40, 40, Color.white, Assets.font36);
            StringDrawFunction.StringDraw(graphics, "Health: " + Integer.toString(enemy.getHealth()) + "/" + Integer.toString(enemy.getMax_health()), handler.getGame().getWidth() - 680 + 40, 120, Color.pink, Assets.font36);
            StringDrawFunction.StringDraw(graphics, "Defense: " + Integer.toString(enemy.getDefense()), handler.getGame().getWidth() - 680 + 40, 160, Color.white, Assets.font36);
            StringDrawFunction.StringDraw(graphics, "Damage: " + Integer.toString(enemy.getDamage()), handler.getGame().getWidth() - 680 + 40, 200, Color.white, Assets.font36);
        }

        if (player != null)
        {
            graphics.drawImage(Assets.playeridle_up, 45, handler.getGame().getWidth() - 460, 256, 256, null); //perfect
            player.getPlayerInventoryAndStats().Draw(graphics);
            if (!player.isActive())
            {
                StringDrawFunction.StringDraw(graphics, "The hunter became the prey. You died!", handler.getGame().getWidth() / 2 - 350, handler.getGame().getHeight() / 2 +40, Color.red, Assets.font36);
                StringDrawFunction.StringDraw(graphics, "Final score: " + handler.getWorld().getEntityManager().getPlayer().getScore(), handler.getGame().getWidth() / 2 - 165, handler.getGame().getHeight() / 2 +80, Color.white, Assets.font36);
                StringDrawFunction.StringDraw(graphics, "press F to close game.", handler.getGame().getWidth() / 2 - 225, handler.getGame().getHeight() / 2 + 120, Color.gray, Assets.font36);
            }
        }



    }

    public MovingEntity getEnemy() {
        return enemy;
    }

    @Override
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
