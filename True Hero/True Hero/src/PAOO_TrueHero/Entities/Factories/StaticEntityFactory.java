package PAOO_TrueHero.Entities.Factories;

import PAOO_TrueHero.Entities.Entity;
import PAOO_TrueHero.Entities.MovingEntities.HornedMage;
import PAOO_TrueHero.Entities.StaticEntities.*;
import PAOO_TrueHero.Handler;
/*! \class StaticEntityFactory
    \brief Not much to say.

 */
public class StaticEntityFactory implements EntityFactory {

    @Override
    public Entity MakeStaticEntity(Handler handler, float x, float y, StaticEntityType type) {
        switch(type)
        {
            case HEALTHPOTION: return new HealthPotion(handler, x, y);
            case BIGHEALTHPOTION: return new BigHealthPotion(handler, x, y);
            case MAXHEALTHPOTION: return new MaxHealthPotion(handler, x, y);
            case MANAPOTION: return new ManaPotion(handler, x, y);
            case BIGMANAPOTION: return new BigManaPotion(handler, x, y);
            case MAXMANAPOTION: return new MaxManaPotion(handler, x, y);
            default: return null;
        }
    }

    public Entity MakeMovingEntity(Handler handler, float x, float y, boolean moveType, MovingEntityType type) {

        return null;
    }
}
