import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class helper {

    public static String[] getWords (boolean randomCaps)
    {
        try {
            URL url = new URL("https://raw.githubusercontent.com/jonbcard/scrabble-bot/master/src/dictionary.txt");
            BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
            ArrayList<String> words = new ArrayList<>();
            String txt = "";
            while ((txt = r.readLine()) != null)
            {
                words.add(helper.getBetterForm(txt, randomCaps));
                txt = "";
            }

            r.close();

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
    public static String[] getWordsLimited (boolean randomCaps, int limit)
    {
        try {
            URL url = new URL("https://raw.githubusercontent.com/first20hours/google-10000-english/master/google-10000-english-no-swears.txt");
            BufferedReader r = new BufferedReader(new InputStreamReader(url.openStream()));
            ArrayList<String> words = new ArrayList<>();
            ArrayList<String> limited = new ArrayList<>();
            String txt = "";
            while ((txt = r.readLine()) != null)
            {
                words.add(helper.getBetterForm(txt, randomCaps));
                txt = "";
            }

            r.close();

            if(limit <= 10000)
            {
                for (int i = 0; i < limit; i++) {
                    limited.add(words.get(i));
                }
            }else if(limit > 10000)
            {
                for(String s : words)
                {
                    limited.add(s);
                }
            }

            String[] finalement = new String[limited.size()];
            for(int i = 0; i < finalement.length; i++)
            {
                finalement[i] = limited.get(i);
            }

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

    public static String getChanger (boolean changes)
    {
        if(changes)
            return "Currenly Set to: CHANGES ON NEW PASSWORD";
        else
            return "Currenly Set to: DOES NOT CHANGE ON NEW PASSWORD";

    }

}
