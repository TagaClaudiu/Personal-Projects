package PAOO_TrueHero.Entities.Factories;

import PAOO_TrueHero.Entities.Entity;
import PAOO_TrueHero.Handler;
/*! \class EntityFactory
    \brief Interface for static and moving entity factories. 2 functions for 2 different types of factories.

 */
public interface EntityFactory {
    public Entity MakeStaticEntity(Handler handler, float x, float y, StaticEntityType type);

    public Entity MakeMovingEntity(Handler handler, float x, float y, boolean moveType, MovingEntityType type);
}
