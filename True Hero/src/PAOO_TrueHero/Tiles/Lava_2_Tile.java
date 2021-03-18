package PAOO_TrueHero.Tiles;

import PAOO_TrueHero.Graphics.Assets;

public class Lava_2_Tile extends Tile {
    public Lava_2_Tile(int id) {
        super(Assets.lava_2, id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
