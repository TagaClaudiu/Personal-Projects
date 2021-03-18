package PAOO_TrueHero.Tiles;

import PAOO_TrueHero.Graphics.Assets;

public class MossyWall_3_Tile extends Tile {
    public MossyWall_3_Tile(int id) {
        super(Assets.mossywall_3, id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
