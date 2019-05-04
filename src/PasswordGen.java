import java.util.Random;

public class PasswordGen {

    public static String getPassword (boolean numbersBool, boolean lettersBool, boolean specCharsB, char[] lettersArr, char[] specChars, int numbersN, int lettersN, int specCharsN)
    {
        int SCSoFar = 0;
        int nSoFar = 0;
        int lSoFar = 0;

        String temp = "";

        Random rnd = new Random();

        int totSc;
        int totN;
        int totL;
        if(!specCharsB)
            totSc = 0;
        else
            totSc = specCharsN;
        if(!numbersBool)
            totN = 0;
        else
            totN = numbersN;
        if(!lettersBool)
            totL = 0;
        else
            totL = lettersN;

        for (int i = 0; i < totSc + totN + totL; i++)
        {
            double d = rnd.nextDouble();

            if(d < 1 / 3.0 && SCSoFar != totSc)
            {
                if(specCharsB)
                {
                    char ch = specChars[rnd.nextInt(specChars.length)];
                    temp += ch;
                    SCSoFar++;
                }
            }

            else if(d < 2 / 3.0 && lSoFar != totL)
            {
                if(lettersBool) {
                    char ch = lettersArr[rnd.nextInt(lettersArr.length)];
                    temp += ch;
                    SCSoFar++;
                }
            }

            else if(nSoFar != totN)
            {
                if(numbersBool) {
                    int j = rnd.nextInt(10);
                    temp += j;
                    nSoFar++;
                }
            }
        }

        return temp;
    }
}
