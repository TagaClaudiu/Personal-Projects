package PAOO_TrueHero.Maps;

import PAOO_TrueHero.Entities.Entity;
import PAOO_TrueHero.Entities.EntityManager;
import PAOO_TrueHero.Entities.Factories.EntityFactory;
import PAOO_TrueHero.Entities.Factories.MovingEntityFactory;
import PAOO_TrueHero.Entities.Factories.StaticEntityFactory;
import PAOO_TrueHero.Entities.MovingEntities.Player;
import PAOO_TrueHero.Handler;
import PAOO_TrueHero.Maps.WorldEntities.WorldEntities;
import PAOO_TrueHero.Tiles.Tile;
import PAOO_TrueHero.Utility.Utility;

import java.awt.*;

import static PAOO_TrueHero.Tiles.Tile.TILEHEIGHT;
import static PAOO_TrueHero.Tiles.Tile.TILEWIDTH;

/*! \class World
    \brief Another important class. Contains the entities and map

 */

public class World {

    private Handler handler;
    private int width; //map size
    private int height;
    private int player_spawnX; //spawn point for player
    private int player_spawnY;
    private int[][] map; ///<The map of the game
    //Entities
    private EntityManager entityManager; ///<All the entities in a ArrayList
    private static EntityFactory Moving;
    private static EntityFactory Staying;
    private int map_nr;
    //Singleton

    private static World self;


    private World(Handler handler, String path)
    {
        this.handler = handler;
        entityManager = EntityManager.getSelf(handler, Player.getSelf(handler, 100, 100, 6));
        loadWorld(path);
        Staying = new StaticEntityFactory();
        Moving = new MovingEntityFactory();
        WorldEntities.map1Entities(handler, entityManager, Staying, Moving);
        entityManager.getPlayer().setX(player_spawnX);
        entityManager.getPlayer().setY(player_spawnY);
        map_nr = 1;
    }

    public static World getSelf(Handler handler, String path)
    {
        if (self == null)
            self = new World(handler, path);
        return self;
    }

    public static void reset()
    {
        self = null;
    }

    /*! \fn private void loadWorld(String path)
       \brief loads a world from a file (matrix). adds entities.
       */
    private void loadWorld(String path)
    {
        String file = Utility.loadFileAsString(path);
        String[] tokens = file.split("\\s+"); //separa nr cu space
        width = Utility.parseInt(tokens[0]);
        height = Utility.parseInt(tokens[1]);
        player_spawnX = Utility.parseInt(tokens[2]);
        player_spawnY = Utility.parseInt(tokens[3]);


        map = new int[width][height];

        for (int y=0; y<height; y++)
        {
            for (int x=0; x<width; x++)
            {
                map[x][y] = Utility.parseInt(tokens[(y*width + x)+4]);
                //vectorul in sine e "inversat" (latimea e prima, inaltimea a doua)
                //dar la desenat merge ok, ptc avem x si y in pozitiile bune
            }
        }
    }
    /*! \fn private void loadAWorld(String path)
         \brief helpful for changing maps. deletes all entities from current map except player
         */
    public void loadAWorld(String path)
    {
        //mergem pe alta harta, deci toate entitatile de pe harta curenta dispar
        for (int i = 0; i< entityManager.getEntities().size(); i++)
        {
            Entity entity = entityManager.getEntities().get(i);
            if (!entity.equals(entityManager.getPlayer()))  //clear all entities that aren't the player
                entity.setActive(false);
        }

        map_nr = 2;
        map = null; //bye bye map
        loadWorld(path); //hello new map
        Player.getSelf(handler, 100, 100, 6).setX(player_spawnX); //setezi spawn-ul
        Player.getSelf(handler, 100, 100, 6).setY(player_spawnY); //^

        /*
        if (map_nr==2)
        WorldEntities.map2Entities(handler, entityManager, Staying, Moving);


        for (int i = 0; i<entityManager.getEntities().size(); i++)
        {
            System.out.println(i);
            System.out.println(entityManager.getEntities().get(i).isActive());
        }

        Am decis sa las greseala asta aici sa inteleg un lucru. Aceasta metoda NU sterge entitatile din manager. Le pregateste de stergere.
        DOAR UPDATE din Manager sterge entitatile dim Manager, folosind un iterator.
        Daca adaugi alte entitati aici, vei adauga si sterge in acelasi timp, ceea ce cauzeaza ConcurrentModificationException
        */

    }


    public void Update()
    {
        entityManager.Update();
        if (map_nr == 2) //trebuie asa neaparat, altfel va da exceptie ConcurrentModificationException
        {
            map_nr = 1;
            WorldEntities.map2Entities(handler, entityManager, Staying, Moving);
        }
    }

    public void Draw(Graphics graphics)
    {
        //we don't want xStart <0, nor yStart bigger than Map size
        //0 = cea mai stanga pozitie posibila adica tile (0,0)
        int xStart = Math.max(0 , (int) handler.getGameCamera().getxOffset() / TILEWIDTH); //the up left corner of camera screen
        int xEnd = Math.min(width, (int)((handler.getGameCamera().getxOffset() + handler.getWidth())/ Tile.TILEWIDTH + 1)); //the up right corner of camera screen
        int yStart = Math.max(0 , (int) handler.getGameCamera().getyOffset() / TILEHEIGHT); //the down left corner of camera screen
        int yEnd = Math.min(height, (int)((handler.getGameCamera().getyOffset() + handler.getHeight())/ Tile.TILEHEIGHT + 1)); //the down right corner of camera screen
        //this is for efficiency sake. so you don't try to render EVERYTHING EVERYTIME


        for(int y=yStart; y< yEnd; y++)
            for(int x=xStart; x< xEnd; x++)
            {
                getTile(x,y).Draw(graphics, (int)(Tile.TILEWIDTH*x - handler.getGameCamera().getxOffset()) , (int)(Tile.TILEHEIGHT*y - handler.getGameCamera().getyOffset()));
                //THIS IS HOW IT SHOULD WORK.
            }
        //Entities
        entityManager.Draw(graphics);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public int getPlayer_spawnX() {
        return player_spawnX;
    }

    public int getPlayer_spawnY() {
        return player_spawnY;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public static EntityFactory getMoving() {
        return Moving;
    }

    public static void setMoving(EntityFactory moving) {
        Moving = moving;
    }

    public static EntityFactory getStaying() {
        return Staying;
    }

    public static void setStaying(EntityFactory staying) {
        Staying = staying;
    }

    public Tile getTile(int x, int y)
    {
        if(x< 0 || y < 0 || x >=width || y>=height)
            return Tile.floorTile;
        //^ in case player gets out of map
        Tile t = Tile.tiles[map[x][y]];
        //exactly what i said up there
        if (t == null)
            return Tile.floorTile;
        return t;
    }

}
