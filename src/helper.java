import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class helper {

    public static String[] getWords (String file, boolean randomCaps)
    {
        try {
            BufferedReader r = new BufferedReader(new FileReader(file));
            ArrayList<String> words = new ArrayList<>();
            String txt = "";
            while ((txt = r.readLine()) != null)
            {
                words.add(helper.getBetterForm(txt, randomCaps));
                txt = "";
            }

            String[] finalement = words.toArray(new String[words.size()]);
            return finalement;
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            String[] w2 = {"Java", "C#", "Bob", "Car", "Plane"};
            return w2;
        }catch(IOException e) {
            e.printStackTrace();

            String[] w2 = {"Java", "C#", "Bob", "Car", "Plane"};
            return w2;
        }
    }

    public static String getBetterForm (String Word, boolean rndCaps)
    {
        char[] chars =  Word.toCharArray();
        String txt = "";
        if(!rndCaps) {
            for (int i = 0; i < chars.length; i++) {
                String j = chars[i] + "";
                if (i == 0)
                    txt += j.toUpperCase();
                 else
                    txt += j.toLowerCase();
            }
        }else
        {
            for (int i = 0; i < chars.length; i++) {
                String j = chars[i] + "";
                if(helper.getRndBool())
                {
                    txt += j.toUpperCase();
                }else
                {
                    txt += j.toLowerCase();
                }
            }
        }

        return txt;
    }

    public static String getMenuTxtWords (boolean mixes, boolean rndCaps)
    {
        String mixesS;
        String rndCapsS;
        if(mixes)
        {
            mixesS = "MIXING";
        }else
        {
            mixesS = "NOT MIXING";
        }

        if(rndCaps)
        {
            rndCapsS = "RANDOM CAPITALS";
        }else
        {
            rndCapsS = "NON RANDOM CAPITALS";
        }

        return "Currently set to: " + mixesS + " & " + rndCapsS;
    }
    public static String getMenuTxtNums (boolean lumps)
    {
        String clump = "";
        if(lumps)
            clump = "LUMPING";
        else
            clump = "NON LUMPING";

        return "Currently Set to: " + clump;
    }

    public static boolean getRndBool ()
    {
        Random rn = new Random();
        double n = rn.nextDouble();
        if(n > 0.5)
        {
            return true;
        }else
        {
            return false;
        }
    }

}
