package PAOO_TrueHero.Tiles;

import PAOO_TrueHero.Graphics.Assets;

public class MossyWall_1_Tile extends Tile {
    public MossyWall_1_Tile(int id) {
        super(Assets.mossywall_1, id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
