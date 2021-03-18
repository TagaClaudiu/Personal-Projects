package PAOO_TrueHero.Maps.WorldEntities;

import PAOO_TrueHero.Entities.EntityManager;
import PAOO_TrueHero.Entities.Factories.EntityFactory;
import PAOO_TrueHero.Entities.Factories.MovingEntityType;
import PAOO_TrueHero.Entities.Factories.StaticEntityType;
import PAOO_TrueHero.Handler;
/*! \class WorldEntities
    \brief Creates entities for both maps, in their respective positions.

 */
public class WorldEntities {

    public static void map1Entities(Handler handler, EntityManager entityManager, EntityFactory Staying, EntityFactory Moving)
    {
        final int tSize = 64;
        //Potions
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*5, tSize*18, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*5, tSize*17, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*8, tSize*15, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*8, tSize*14, StaticEntityType.MANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize, tSize*9, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*2, tSize*9, StaticEntityType.MANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*12, tSize*18, StaticEntityType.BIGHEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*28, tSize*12, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*28, tSize*13, StaticEntityType.MANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*34, tSize*12, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*34, tSize*13, StaticEntityType.MANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*31, tSize*12, StaticEntityType.MAXMANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*20, tSize*5, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*25, tSize*5, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*8, tSize*9, StaticEntityType.BIGHEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*12, tSize*9, StaticEntityType.MAXHEALTHPOTION));

        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*12, tSize*12, StaticEntityType.MANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*12, tSize*17, StaticEntityType.BIGMANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*12, tSize*11, StaticEntityType.HEALTHPOTION));

        //Enemies:

        //entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*2, tSize*11, true, MovingEntityType.BOSS));

        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*2, tSize*11, true, MovingEntityType.HORNEDMAGE));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*15, tSize*12 - 40, false, MovingEntityType.HORNEDMAGE));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*29, tSize*3 - 20, true, MovingEntityType.HORNEDMAGE));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*9, tSize*8 - 30, true, MovingEntityType.HORNEDMAGE));

        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*14 + 10, tSize*16, true, MovingEntityType.MINION));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*8, tSize*17, false, MovingEntityType.MINION));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*24, tSize*15, true, MovingEntityType.MINION));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*36, tSize*15, true, MovingEntityType.MINION));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*21+10, tSize*5-10, true, MovingEntityType.MINION));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*20+20, tSize*10-10, true, MovingEntityType.MINION));

        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*31, tSize*14, true, MovingEntityType.TENTACLE));

        //entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*5, tSize*14, true, MovingEntityType.BOSS));
    }


    public static void map2Entities(Handler handler, EntityManager entityManager, EntityFactory Staying, EntityFactory Moving)
    {
        final int tSize = 64;

        //Potions:
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*17, tSize*59, StaticEntityType.MAXHEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*16, tSize*57, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*18, tSize*57, StaticEntityType.MANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*7, tSize*59, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*9, tSize*59, StaticEntityType.MANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*7, tSize*57, StaticEntityType.MANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*9, tSize*57, StaticEntityType.HEALTHPOTION));

        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*6, tSize*51, StaticEntityType.BIGMANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*5, tSize*41, StaticEntityType.MAXMANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*12, tSize*42, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*12, tSize*40, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*11, tSize*41, StaticEntityType.BIGHEALTHPOTION));

        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*15, tSize*31, StaticEntityType.MAXHEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*16, tSize*31, StaticEntityType.BIGHEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*15, tSize*32, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*16, tSize*32, StaticEntityType.HEALTHPOTION));

        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*15, tSize*36, StaticEntityType.BIGMANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*16, tSize*36, StaticEntityType.MANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*15, tSize*37, StaticEntityType.MAXMANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*16, tSize*37, StaticEntityType.MANAPOTION));

        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize, tSize*33, StaticEntityType.BIGHEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*3, tSize*27, StaticEntityType.BIGMANAPOTION));

        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*4, tSize*20, StaticEntityType.BIGHEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*5, tSize*20, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*6, tSize*20, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*4, tSize*22, StaticEntityType.BIGMANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*5, tSize*22, StaticEntityType.MANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*6, tSize*22, StaticEntityType.MANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*4, tSize*21, StaticEntityType.BIGHEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*5, tSize*21, StaticEntityType.BIGMANAPOTION));

        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*5, tSize*50, StaticEntityType.BIGHEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*7, tSize*50, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*6, tSize*50, StaticEntityType.MANAPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*7, tSize*35, StaticEntityType.HEALTHPOTION));
        entityManager.addEntity(Staying.MakeStaticEntity(handler, tSize*9, tSize*35, StaticEntityType.HEALTHPOTION));


        //Enemies:
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*16 + 16, tSize*55, false, MovingEntityType.SHADOW));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*12, tSize*58, true, MovingEntityType.TENTACLE));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*5, tSize*56, true, MovingEntityType.MINION));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*5, tSize*59, true, MovingEntityType.MINION));

        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*2, tSize*40, false, MovingEntityType.SHADOW));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*9, tSize*40, true, MovingEntityType.SHADOW));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*2, tSize*48, true, MovingEntityType.TENTACLE));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*17, tSize*43, true, MovingEntityType.TENTACLE));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*10 + 16, tSize*50, true, MovingEntityType.MINION));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*9 + 16, tSize*46, false, MovingEntityType.MINION));

        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*8, tSize*35, false, MovingEntityType.TENTACLE));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*4, tSize*28, true, MovingEntityType.TENTACLE));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*2-30, tSize*18, false, MovingEntityType.SHADOW));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*14, tSize*34-40, true, MovingEntityType.SHADOW));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*18-30, tSize*32-32, false, MovingEntityType.SHADOW));

        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*4, tSize*18-10, true, MovingEntityType.MINION));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*12, tSize*18-10, true, MovingEntityType.MINION));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*9, tSize*20, false, MovingEntityType.MINION));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*11, tSize*20, false, MovingEntityType.MINION));
        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*13, tSize*20, false, MovingEntityType.MINION));


        entityManager.addEntity(Moving.MakeMovingEntity(handler, tSize*9, tSize*4, true, MovingEntityType.BOSS));
    }
}
