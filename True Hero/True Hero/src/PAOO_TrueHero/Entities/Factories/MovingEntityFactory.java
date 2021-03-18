package PAOO_TrueHero.Entities.Factories;

import PAOO_TrueHero.Entities.Entity;
import PAOO_TrueHero.Entities.MovingEntities.*;
import PAOO_TrueHero.Handler;
/*! \class MovingEntityFactory
    \brief Not much to say.

 */
public class MovingEntityFactory implements EntityFactory {

    @Override
    public Entity MakeMovingEntity(Handler handler, float x, float y, boolean moveType, MovingEntityType type) {

        switch(type)
        {
            case HORNEDMAGE: return new HornedMage(handler, x, y, moveType);
            case SHADOW: return new Shadow(handler, x, y, moveType);
            case MINION: return new Minion(handler, x, y, moveType);
            case TENTACLE: return new Tentacle(handler, x, y, moveType);
            case BOSS: return new Boss(handler, x, y, moveType);
            default: return null;
        }
    }
    @Override
    public Entity MakeStaticEntity(Handler handler, float x, float y, StaticEntityType type)
    {
        return null;
    }

}
