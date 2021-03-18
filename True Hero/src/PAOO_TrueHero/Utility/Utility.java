package PAOO_TrueHero.Utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utility {

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
