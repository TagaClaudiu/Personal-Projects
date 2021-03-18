package PAOO_TrueHero.Graphics;

import java.awt.image.BufferedImage;
/*! \class Animations
    \brief Basically an array that keeps cycling through members

 */
public class Animations {
    private int speed, index; //the frequency of the animations, index of array
    private BufferedImage[] frames;
    private long oldTime, timer;

    public Animations(int speed, BufferedImage[] frames)
    {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        oldTime = System.currentTimeMillis();
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BufferedImage getCurrentFrame() {
        return frames[index];
    }

    public void Update()
    {
        timer += System.currentTimeMillis() - oldTime; //timer gets the time since the last update got called
        oldTime = System.currentTimeMillis();

        if(timer > speed) //speed of animations;
        {
            index++;
            timer = 0;
            if(index>=frames.length)
                index = 0;
        }
    }
}
