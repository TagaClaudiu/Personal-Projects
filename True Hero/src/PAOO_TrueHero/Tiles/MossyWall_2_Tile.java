package PAOO_TrueHero.Tiles;

import PAOO_TrueHero.Graphics.Assets;

public class MossyWall_2_Tile extends Tile {
    public MossyWall_2_Tile(int id) {
        super(Assets.mossywall_2, id);
    }

    @Override
    public boolean isSolid()
    {
        return true;
    }
}
