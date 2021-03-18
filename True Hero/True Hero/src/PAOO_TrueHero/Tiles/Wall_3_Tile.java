package PAOO_TrueHero.Tiles;

import PAOO_TrueHero.Graphics.Assets;

public class Wall_3_Tile extends Tile {
    public Wall_3_Tile(int id) {
        super(Assets.wall_3, id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
