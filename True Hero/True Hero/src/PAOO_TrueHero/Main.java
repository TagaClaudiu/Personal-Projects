package PAOO_TrueHero;


/*! \class Main
    \brief Clasa de start

 */
public class Main {

    public static void main(String[] args)
    {
        Game TrueHero = Game.getSelf("True Hero", 1024,768);
        TrueHero.start();
    }
}
