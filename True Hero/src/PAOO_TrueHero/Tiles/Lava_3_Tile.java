package PAOO_TrueHero.Tiles;

import PAOO_TrueHero.Graphics.Assets;

public class Lava_3_Tile extends Tile {
    public Lava_3_Tile(int id) {
        super(Assets.lava_3, id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
