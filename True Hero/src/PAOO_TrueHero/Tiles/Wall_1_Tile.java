package PAOO_TrueHero.Tiles;

import PAOO_TrueHero.Graphics.Assets;


public class Wall_1_Tile extends Tile {
    public Wall_1_Tile(int id) {
        super(Assets.wall_1, id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
