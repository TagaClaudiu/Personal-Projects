package PAOO_TrueHero.Tiles;

import PAOO_TrueHero.Graphics.Assets;

public class Lava_1_Tile extends Tile {
    public Lava_1_Tile(int id) {
        super(Assets.lava_1, id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
