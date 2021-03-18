package PAOO_TrueHero;

import PAOO_TrueHero.Graphics.GameCamera;
import PAOO_TrueHero.KeyboardAndMouseInput.KeyManager;
import PAOO_TrueHero.KeyboardAndMouseInput.MouseManager;
import PAOO_TrueHero.Maps.World;
/*! \class Handler
    \brief Helper class. 1 game, 1 world to EVERYONE.

 */

//This guy allows you/me to pass ONLY 1 OBJECT to ALL other classes
//1 game, 1 world to EVERYONE.
public class Handler {

    private Game game; ///< Game object
    private World world; ///< World object

    //Singleton
    private static Handler self; ///< Singleton

    private Handler(Game game) {
        this.game = game;
    }

    public static Handler getSelf(Game game)
    {
        if (self == null)
            self = new Handler(game);
        return self;
    }

    public static void reset()
    {
        self = null;
    }

    public int getWidth() { return game.getWidth(); }

    public int getHeight()
    {
        return game.getHeight();
    }

    public KeyManager getKeyManager()
    {
        return game.getKeyManager();
    }

    public GameCamera getGameCamera()
    {
        return game.getGameCamera();
    }

    public MouseManager getMouseManager() {return game.getMouseManager(); }


    public void setGame(Game game) {
        this.game = game;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Game getGame() {
        return game;
    }

    public World getWorld() {
        return world;
    }


}
