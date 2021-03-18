package PAOO_TrueHero.Utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/*! \class Utility
    \brief Some helpful functions

 */
public class Utility {
    /*! \fn public static String loadFileAsString(String path)
           \brief basically what the title says; loads a file into a string
           */
    public static String loadFileAsString(String path)
    {
        StringBuilder builder = new StringBuilder();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while((line = br.readLine())!=null)
                builder.append(line + "\n");
        }
        catch(IOException oof)
        {
            oof.printStackTrace();
        }
        return builder.toString();
    }
    /*! \fn public static int parseInt(String number)
           \brief Integer.parseInt but with a try catch, just in case.
           */
    public static int parseInt(String number)
    {
        try{
            return Integer.parseInt(number);
        }
        catch(NumberFormatException oof)
        {
            oof.printStackTrace();
            return 0;
        }
    }
}
