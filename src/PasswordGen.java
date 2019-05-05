import java.util.ArrayList;
import java.util.Random;

public class PasswordGen {

//    public static String getPassword (boolean numbersBool, boolean lettersBool, boolean specCharsB, char[] lettersArr, char[] specChars, int numbersN, int lettersN, int specCharsN)
//    {
//        int SCSoFar = 0;
//        int nSoFar = 0;
//        int lSoFar = 0;
//
//        String temp = "";
//
//        Random rnd = new Random();
//
//        int totSc;
//        int totN;
//        int totL;
//
//        if(!specCharsB)
//            totSc = 0;
//        else
//            totSc = specCharsN;
//
//        if(!numbersBool)
//            totN = 0;
//        else
//            totN = numbersN;
//
//        if(!lettersBool)
//            totL = 0;
//        else
//            totL = lettersN;
//
//        for (int i = 0; i < totSc + totN + totL; i++)
//        {
//            double d = rnd.nextDouble();
//
//            if(d < 1 / 3.0 && SCSoFar != totSc)
//            {
//                if(specCharsB)
//                {
//                    char ch = specChars[rnd.nextInt(specChars.length)];
//                    temp += ch;
//                    SCSoFar++;
//                }
//            }
//
//            else if(d < 2 / 3.0 && lSoFar != totL)
//            {
//                if(lettersBool) {
//                    char ch = lettersArr[rnd.nextInt(lettersArr.length)];
//                    temp += ch;
//                    SCSoFar++;
//                }
//            }
//
//            else if(nSoFar != totN)
//            {
//                if(numbersBool) {
//                    int j = rnd.nextInt(10);
//                    temp += j;
//                    nSoFar++;
//                }
//            }
//        }
//
//        return temp;
//    }

    public static String getPassword (boolean numbersBool, boolean lettersBool, boolean specCharsBool, boolean upperBool, boolean accentsB, boolean upperAccentsB, boolean wordsB, char[] lettersArr, char[] specChars, char[] upperArr, char[] accents, char[] upperAccents, String[] words, int numbersN, int lettersN, int specCharsN, int upperN, int accentsN, int upperAccentsN, int wordsN, boolean clumpedNums)
    {
        Random rnd = new Random();

        ArrayList<String> endList = new ArrayList<>();

        if(numbersBool)
        {
            int bigN = (int) Math.pow(10, numbersN) - 1;
            int rn = rnd.nextInt(bigN);
            String rnSt = rn + "";

            if(!clumpedNums) {
                char[] rnChs = rnSt.toCharArray();
                for (char c : rnChs) {
                    endList.add(c + "");
                }
            }else
            {
                endList.add(rnSt);
            }
        }
        if(lettersBool)
        {
            for (int i = 0; i < lettersN; i++) {
                int rndN = rnd.nextInt(lettersArr.length);
                char c = lettersArr[rndN];
                endList.add(c + "");
            }
        }
        if(upperBool)
        {
            for (int i = 0; i < upperN; i++)
            {
                int rndNU = rnd.nextInt(upperArr.length);
                char cU = upperArr[rndNU];
                endList.add(cU + "");
            }
        }
        if(specCharsBool)
        {
            for (int i = 0; i < specCharsN; i++) {
                int RndN = rnd.nextInt(lettersArr.length);
                char C = specChars[RndN];
                endList.add(C + "");
            }
        }
        if(accentsB)
        {
            for (int i = 0; i < accentsN; i++)
            {
                int rndNU = rnd.nextInt(accents.length);
                char cU = accents[rndNU];
                endList.add(cU + "");
            }
        }
        if(upperAccentsB)
        {
            for (int i = 0; i < upperAccentsN; i++)
            {
                int rndNU = rnd.nextInt(upperAccents.length);
                char cU = upperAccents[rndNU];
                endList.add(cU + "");
            }
        }
        if(wordsB)
        {
            for (int i = 0; i < wordsN; i++) {
                int rndNU = rnd.nextInt(words.length);
                String w = words[rndNU];
                endList.add(w);
            }
        }
        
        String str = "";
        int size = endList.size();

        for (int i = 0; i < size; i++)
        {
            int rndN = rnd.nextInt(endList.size());
            str += endList.get(rndN);
            endList.remove(rndN);
        }

        System.out.println("numbersBool = [" + numbersBool + "], lettersBool = [" + lettersBool + "], specCharsBool = [" + specCharsBool + "], upperBool = [" + upperBool + "], lettersArr = [" + lettersArr + "], specChars = [" + specChars + "], upperArr = [" + upperArr + "], numbersN = [" + numbersN + "], lettersN = [" + lettersN + "], specCharsN = [" + specCharsN + "], upperN = [" + upperN + "]");
        System.out.println("PasswordGen.getPassword");
        System.out.println("str = " + str);

        return str;

    }

}
