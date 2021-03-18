package PAOO_TrueHero.Entities.Factories;

import PAOO_TrueHero.Entities.Entity;
import PAOO_TrueHero.Handler;

public interface EntityFactory {
    public Entity MakeStaticEntity(Handler handler, float x, float y, StaticEntityType type);

    public Entity MakeMovingEntity(Handler handler, float x, float y, boolean moveType, MovingEntityType type);
}
