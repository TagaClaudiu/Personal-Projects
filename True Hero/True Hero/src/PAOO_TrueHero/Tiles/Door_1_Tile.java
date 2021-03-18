package PAOO_TrueHero.Tiles;

import PAOO_TrueHero.Graphics.Assets;


public class Door_1_Tile extends Tile {
    public Door_1_Tile(int id) {
        super(Assets.door_1, id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
