package PAOO_TrueHero.Tiles;

import PAOO_TrueHero.Graphics.Assets;

public class Wall_2_Tile extends Tile {
    public Wall_2_Tile(int id) {
        super(Assets.wall_2, id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
