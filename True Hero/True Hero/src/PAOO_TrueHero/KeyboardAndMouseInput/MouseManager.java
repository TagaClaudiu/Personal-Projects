package PAOO_TrueHero.KeyboardAndMouseInput;

import PAOO_TrueHero.MenuUI.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
/*! \class MouseManager
    \brief Fancy stuff for Main Menu

 */
public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed; //mouse buttons
    private int mouseX, mouseY; //position of mouse
    private UIManager uiManager;

    //Singleton
    private static MouseManager self;

    private MouseManager() { }

    public static MouseManager getSelf()
    {
        if (self == null)
            self = new MouseManager();
        return self;
    }

    public static void reset()
    {
        self = null;
    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1)
            leftPressed=true;
        else if(mouseEvent.getButton() ==MouseEvent.BUTTON2)
            rightPressed=true;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseEvent.BUTTON1)
            leftPressed=false;
        else if(mouseEvent.getButton() == MouseEvent.BUTTON2)
            rightPressed=false;

        if(uiManager !=null)
            uiManager.onMouseRelease(mouseEvent);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getX();
        mouseY = mouseEvent.getY();

        if(uiManager !=null)
            uiManager.onMouseMove(mouseEvent);
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
