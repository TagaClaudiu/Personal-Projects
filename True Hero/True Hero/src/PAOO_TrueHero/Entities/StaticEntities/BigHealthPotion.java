package PAOO_TrueHero.Entities.StaticEntities;

import PAOO_TrueHero.Graphics.Assets;
import PAOO_TrueHero.Handler;
import PAOO_TrueHero.InventoryAndStatPage.Item;
import PAOO_TrueHero.Tiles.Tile;

import java.awt.*;

public class BigHealthPotion extends StaticEntity {
    public BigHealthPotion(Handler handler, float x, float y) {

        super(handler, x, y, Tile.TILEWIDTH - 16, Tile.TILEHEIGHT - 16);
        hitbox.x =16;
        hitbox.y = 26;
        hitbox.width = 16;
        hitbox.height = 12;
        max_health = 1;
        health = 1;
    }

    @Override
    public void GetKilled()
    {
        active = false;
        handler.getWorld().getEntityManager().getPlayer().setScore(handler.getWorld().getEntityManager().getPlayer().getScore() + 50);
        handler.getWorld().getEntityManager().getPlayer().getPlayerInventoryAndStats().addItem(Item.big_health_potion.createNew());
    }

    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics graphics) {
        graphics.drawImage(Assets.health_potion_better, (int)(x- handler.getGameCamera().getxOffset()),  (int)(y- handler.getGameCamera().getyOffset()), width, height, null);
        //graphics.setColor(Color.red);
        //graphics.fillRect((int)(x + hitbox.x - handler.getGameCamera().getxOffset()), (int)(y + hitbox.y - handler.getGameCamera().getyOffset()), hitbox.width, hitbox.height);
    }
}