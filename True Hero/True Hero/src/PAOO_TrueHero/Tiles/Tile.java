package PAOO_TrueHero.Tiles;

import PAOO_TrueHero.Tiles.TileFactory.TileFactory;
import PAOO_TrueHero.Tiles.TileFactory.TileType;

import java.awt.*;
import java.awt.image.BufferedImage;
/*! \class Tile
    \brief The BASELINE for all map tiles

 */
public class Tile {

    //static stuff
    public static Tile[] tiles = new Tile[256];
    public static TileFactory factory = new TileFactory();

    public static Tile floorTile = factory.makeTile(TileType.GROUND_1,0);
    public static Tile floorTile2 = factory.makeTile(TileType.GROUND_2,1);
    public static Tile floorTile3 = factory.makeTile(TileType.GROUND_3,2);
    public static Tile wallTile = factory.makeTile(TileType.WALL_1,3);
    public static Tile wallTile2 = factory.makeTile(TileType.WALL_2,4);
    public static Tile wallTile3 = factory.makeTile(TileType.WALL_3,5);
    public static Tile mossywallTile = factory.makeTile(TileType.MOSSY_1,6);
    public static Tile mossywallTile2 = factory.makeTile(TileType.MOSSY_2,7);
    public static Tile mossywallTile3 = factory.makeTile(TileType.MOSSY_3,8);
    public static Tile doorTile = factory.makeTile(TileType.DOOR_1,9);
    public static Tile doorTile2 = factory.makeTile(TileType.DOOR_2,10);
    public static Tile lavaTile = factory.makeTile(TileType.LAVA_1,11);
    public static Tile lavaTile2 = factory.makeTile(TileType.LAVA_2,12);
    public static Tile lavaTile3 = factory.makeTile(TileType.LAVA_3,13);

    public static final int TILEWIDTH = 64;
    public static final int TILEHEIGHT = 64;

    //class
    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id)
    {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }
    /*! \fn public boolean isSolid()
               \brief if the tile solid or not? (for collision); by default, a tile IS NOT SOLID
               */
    public boolean isSolid() //by default, a tile IS NOT SOLID
    {
        return false;
    }


    public void Update()
    {

    }

    public void Draw(Graphics graphics, int x, int y)
    {
        graphics.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public int getId()
    {
        return id;
    }
}
