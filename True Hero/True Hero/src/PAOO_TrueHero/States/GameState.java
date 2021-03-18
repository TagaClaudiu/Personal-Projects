package PAOO_TrueHero.States;

import PAOO_TrueHero.Entities.MovingEntities.Enemy;
import PAOO_TrueHero.Handler;
import PAOO_TrueHero.Maps.World;


import java.awt.*;
/*! \class GameState
    \brief Where player moves, enemies move, map exists

 */
public class GameState extends State {


    private World map1;

    public GameState(Handler handler)
    {
        super(handler);
        map1 = World.getSelf( handler,"res/worlds/map1.txt");
        handler.setWorld(map1);

    }

    @Override
    public void Update()
    {
        map1.Update();
    }

    @Override
    public void Draw(Graphics graphics)
    {
        map1.Draw(graphics);
    }

    @Override
    public void setEnemy(Enemy enemy) {

    }
}
