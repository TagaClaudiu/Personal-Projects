package PAOO_TrueHero.States;

import PAOO_TrueHero.Entities.MovingEntities.Enemy;
import PAOO_TrueHero.Handler;

import java.awt.*;
/*! \class State
    \brief Baseline class for all States

 */
public abstract class State {

    private static State currentState = null;

    protected Handler handler;

    public State(Handler handler)
    {
        this.handler = handler;
    }

    public static void setCurrentState(State currentState) {
        State.currentState = currentState;
    }

    public static State getCurrentState()
    {
        return currentState;
    }

    public abstract void Update();

    public abstract void Draw(Graphics graphics);

    public abstract void setEnemy(Enemy enemy);
}
