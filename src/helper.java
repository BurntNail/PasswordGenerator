import java.io.*;
import java.util.ArrayList;

public class helper {

    public static String[] getWords (String file)
    {
        try {
            BufferedReader r = new BufferedReader(new FileReader(file));
            ArrayList<String> words = new ArrayList<>();
            String txt = "";
            while ((txt = r.readLine()) != null)
            {
                words.add(helper.getBetterForm(txt));
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

    public static String getBetterForm (String Word)
    {
        char[] chars =  Word.toCharArray();
        String txt = "";
        for (int i = 0; i < chars.length; i++) {
            if(i == 0)
            {
                String j = chars[i] + "";
                txt += j.toUpperCase();
            }else
            {
                String j = chars[i]  + "";
                txt += j.toLowerCase();
            }
        }

        return txt;
    }

}
