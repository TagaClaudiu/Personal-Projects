package PAOO_TrueHero.InventoryAndStatPage;

import PAOO_TrueHero.Graphics.Assets;
import PAOO_TrueHero.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
/*! \class Item
    \brief Items that are stored in the inventory

 */
//Items(Potions) that get put into inventory.
public class Item {

    public static final int ITEMWIDTH = 32;
    public static final int ITEMHEIGHT = 32;
    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected String description;
    protected int amount;
    protected final int id;

    public static Item[] items = new Item[50];
    public static Item health_potion = new Item(Assets.health_potion, "Health Potion", "heals for 10 HP", 0);
    public static Item big_health_potion = new Item(Assets.health_potion_better, "Enhanced Health Potion", "heals for 20 HP", 1);
    public static Item max_health_potion = new Item(Assets.max_health_potion, "Potion of Vigor", "increases total HP by 5", 2);
    public static Item mana_potion = new Item(Assets.mana_potion, "Mana Potion", "restores 10 MP", 3);
    public static Item big_mana_potion = new Item(Assets.mana_potion_better, "Enhanced Mana Potion", "restores 20 MP", 4);
    public static Item max_mana_potion = new Item(Assets.max_mana_potion, "Potion of Spirit","increases total MP by 5", 5);

    public Item(BufferedImage texture, String name, String description, int id)
    {
        this.texture = texture;
        this.name = name;
        this.id = id;
        amount = 1;
        this.description = description;
    }

    public Item createNew() //make a copy. (what gets added to inventory)
    {
        return new Item(texture, name, description, id);
    }

    public void Update()
    {
        if(handler == null)
            return;
        handler.getWorld().getEntityManager().getPlayer().getPlayerInventoryAndStats().addItem(this);
    }

    public void Draw(Graphics graphics, int x, int y)
    {
        graphics.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Handler getHandler() {
        return handler;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
