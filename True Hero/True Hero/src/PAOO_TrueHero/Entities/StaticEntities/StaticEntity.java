package PAOO_TrueHero.Entities.StaticEntities;

import PAOO_TrueHero.Entities.Entity;
import PAOO_TrueHero.Handler;
/*! \class StaticEntity
    \brief Objects. Mostly potions. Only potions.

 */
public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height)
    {
        super(handler, x, y, width, height);
    }
}
