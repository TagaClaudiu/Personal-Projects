package PAOO_TrueHero;

import PAOO_TrueHero.GameWindow.GameWindow;
import PAOO_TrueHero.Graphics.Assets;
import PAOO_TrueHero.Graphics.GameCamera;
import PAOO_TrueHero.KeyboardAndMouseInput.KeyManager;
import PAOO_TrueHero.KeyboardAndMouseInput.MouseManager;
import PAOO_TrueHero.States.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
/*! \class Game
    \brief THE class of everything

 */
public class Game implements Runnable { //Game class should have it's own thread


    //THE GameWindow shenanigans
    private GameWindow window; ///<the window with canvas added
    private int width; ///<will copy the width of window. so it can access it
    private int height; ///<will copy the height of window. so it can access it
    private String title; ///<will copy the title of title. so it can access it


    //THE Thread shenanigans
    private Thread thread;
    private boolean running = false; ///<is the thread / Game class running?


    //THE draw shenanigans
    private BufferStrategy bs; ///<a way for the PC to draw stuff on screen
    //uses buffers: basically a 'hidden' pc screen in the pc .... Buffer -> Buffer -> ActualScreen. kinda?
    //so basically: we're done drawing 1 frame. put it in buffer. then move it to next one, because we're drawing again. then move it to actual screen.
    //Iti garanteaza efectiv ca nu vei avea screen flickering (Don't wait for it to finish drawing every time. THINK PIPELINE)
    private Graphics graphics; ///<the paint brush used to paint on that canvas of GameWindow


    //The State shenanigans
    private State gameState; ///<the state in which you move, do stuff
    private State menuState; ///<main menu; has mouse buttons
    private State settingsState; ///<a description of controls
    private State combatState; ///<once you get too close to an enemy, you enter this


    //Input
    private KeyManager keyManager; ///<for almost everything that makes this a game
    private MouseManager mouseManager; ///<for main menu


    //Camera
    private GameCamera gameCamera; ///<the camera


    //Handler
    private Handler handler; ///<helper


    //SINGLETON
    private static Game self; ///<singleton


    private Game(String title, int width, int height)
    {
        this.width = width;
        this.height = height;
        this.title = title;

    }

    public static Game getSelf(String title, int width, int height )
    {
        if(self == null)
            self = new Game(title, width, height);
        return self;
    }

    public static void reset()
    {
        self = null;
    }

    /*! \fn public synchronized void start()
        \brief thread member shenanigans
        */
    //For thread member
    //synchronized: locks the method for 1 thread only at a time
    public synchronized void start(){
        if (running) //game is already running. get out.
            return;

        running = true;
        thread = new Thread(this); //constructor: so, what class you wanna run separately?
        thread.start(); //this little method will call the run() from Game
    }


    //For thread member
    public synchronized void stop(){
        if (!running) //if it's already closed
            return;

        running = false;
        try
        {
            thread.join(); //closes thread
        }
        catch(InterruptedException oof)
        {
            oof.printStackTrace();
        }
    }

    public State getGameState() {
        return gameState;
    }

    public State getMenuState() {
        return menuState;
    }

    public State getSettingsState() {
        return settingsState;
    }

    public State getCombatState() {
        return combatState;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    /*! \fn private void init()
    \brief will be run only once in run; initializes window, graphics, etc.
    */
    //will be run only once in run.
    //will initialize window, graphics, etc
    private void init(){
        window = GameWindow.getSelf(title, width, height);//the window
        keyManager = KeyManager.getSelf();
        mouseManager = MouseManager.getSelf();
        window.getFrame().addKeyListener(keyManager);
        window.getFrame().addMouseListener(mouseManager);
        window.getFrame().addMouseMotionListener(mouseManager);
        window.getCanvas().addMouseListener(mouseManager);
        window.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init(); //initializes assets

        handler = Handler.getSelf(this);
        gameCamera = GameCamera.getSelf(handler,0,0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        settingsState = new SettingsState(handler);
        combatState = new CombatState(handler);

        State.setCurrentState(menuState);
    }


    //updates all variables, positions of objects
    private void update()
    {
        keyManager.Update();

        if(State.getCurrentState()!= null)
        {
            State.getCurrentState().Update();
        }
    }

    /*! \fn private void draw()
        \brief draws everything on the screen; uses buffer strategy to stop flickering
        */
    //renders(draws) everything on the screen
    //to draw stuff, canvas has to be accessed.
    //uses buffer strategy to stop flickering.
    private void draw()
    {
        bs = window.getCanvas().getBufferStrategy();
        if (bs == null)
        {
            window.getCanvas().createBufferStrategy(4); //creates bs with 4 buffers.
            return; //get outta here
        }

        graphics = bs.getDrawGraphics(); //the paint brush for your canvas.

        //before drawing, you gotta clear canvas. make it clean :)
        graphics.clearRect(0, 0, width, height); //from (0.0) it will create a clear rectangle with l and L = width and height; width and height just get added to x and y;
        //In Java: (0,0) is left right. y increases downwards. x is normal (left -> right)

        //time to draw! :)

        if(State.getCurrentState()!= null)
        {
            State.getCurrentState().Draw(graphics);
        }

        //ENOUGH DRAWING!
        bs.show(); //does the magic with buffers.
        graphics.dispose(); //BEGONE FOOL!
    }

    /*! \fn public void run()
        \brief game loop: Update -> Draw -> Update -> Draw -> ... ; is run once
        */
    //game loop: Update -> Draw -> Update -> Draw -> etc. we use a while
    //run is ran only once basically, when you start the Thread.
    public void run() //the main method. our code will be here. will use init, draw and update
    {
        init(); //makes window, and other stuff

        final int fps = 60;
        final double timePerUpdate = 1000000000 / fps; //1 frame in nanoseconds

        double delta = 0;
        long nowTime;
        long oldTime = System.nanoTime();

        while(running) //while the game is running
        {
            nowTime = System.nanoTime();
            delta += (nowTime - oldTime) / timePerUpdate; //tells the pc when/when not to call update/draw
            oldTime = nowTime;


            if(delta >= 1) { //a ajuns la o secunda
                update();
                draw();
                delta--; //s-a rendat, deci readucem la 0
            }
        }

        stop(); //just in case we didn't stop once running = false
    }

}
