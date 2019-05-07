import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class PasswordGenGUI {

    private JFrame frame;
    private JTextField pwordField, lettersNTF, numbersNTF, specCharsNTF, upperLettersNTF, accentsNTF, upperAccentsNTF, wordNTF, mostCommonNTF, limitLengthNTF;
    private Checkbox lettersB, numbersB, specCharsB, upperLettersB, accentsB, upperAccentsB, wordsB, mostCommonB, limitLengthB;
    private Dimension frameSize, PWordFieldSize;
    private JPanel bottomPanel;
    private JButton go;
    private JMenuBar menuBar;
    private JMenu Options, Words, Numbers;
    private JMenuItem Copy, WordsMixWithChars, WordsStayAlone, CurrentSettingsForWords, RandomCapsOn, RandomCapsOff, CurrentSettsForNumbers, numberClumpsOn, numberClumpsOff, newWindow, newWinSetts, newWindowOff, newWidnowOn;
    private boolean wordsMix, randomCaps, clumpedNumbers, newWindowChanges;
    private newWindowGUi newGUi;
    private int gen;


    public PasswordGenGUI (int w, int h, int nww, int nwh)
    {
        //region Mostly init
        wordsMix = false;
        randomCaps = false;
        clumpedNumbers = false;
        newWindowChanges = false;
        newWindowChanges = true;
        gen = 0;
        frameSize = new Dimension(w, h);
        PWordFieldSize = new Dimension(frameSize.width, frameSize.height / 9);

        frame = new JFrame("Password Generator");
        frame.setPreferredSize(frameSize);
        frame.setLayout(new BorderLayout());

        newGUi = new newWindowGUi(nww, nwh);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(9, 2));

        pwordField = new JTextField("Password Goes Here");
        pwordField.setEditable(false);
        pwordField.setPreferredSize(PWordFieldSize);
        go = new JButton("Lets Go!");
        go.setPreferredSize(PWordFieldSize);

        lettersNTF = new JTextField("10");
        lettersNTF.setToolTipText("How many Lowercase letters?");
        upperLettersNTF = new JTextField("10");
        upperLettersNTF.setToolTipText("How many Uppercase letters?");
        numbersNTF = new JTextField("5");
        numbersNTF.setToolTipText("How many numbers?");
        specCharsNTF = new JTextField("5");
        specCharsNTF.setToolTipText("How many Special Charcters?");
        accentsNTF = new JTextField("5");
        accentsNTF.setToolTipText("How many Lowercase charcters with Accents?");
        upperAccentsNTF = new JTextField("5");
        upperAccentsNTF.setToolTipText("How many Uppercase charcters with Accents?");
        wordNTF = new JTextField("5");
        wordNTF.setToolTipText("How many words?");
        mostCommonNTF = new JTextField("1000");
        mostCommonNTF.setToolTipText("The most common how many words? \n From  1 - 10,000");
        limitLengthNTF = new JTextField("7");
        limitLengthNTF.setToolTipText("How many of characters do you limit your word to?");
        

        lettersB = new Checkbox("Do you want Lowercase letters?", false);
        numbersB = new Checkbox("Do you want numbers?", false);
        specCharsB = new Checkbox("Do you want special characters?", false);
        upperLettersB = new Checkbox("Do you want Uppercase letters?", false);
        accentsB = new Checkbox("Do you want Lowercase characters with accents?", false);
        upperAccentsB = new Checkbox("Do you want Uppercase characters with accents?", false);
        wordsB = new Checkbox("How many words do you want?", true);
        mostCommonB = new Checkbox("Most Common Words", true);
        limitLengthB = new Checkbox("Do you want to limit your word length", true);
        //endregion


        //region JMenu
        Copy = new JMenuItem("Copy");
        newWindow = new JMenuItem("Open in new Window");
        newWidnowOn = new JMenuItem("New Window changes on new Password");
        newWindowOff = new JMenuItem("New window does NOT change on new Password");
        newWinSetts = new JMenuItem(helper.getChanger(newWindowChanges));
        Options = new JMenu("Options");
        Options.add(Copy);
        Options.add(newWindow);
        Options.add(newWidnowOn);
        Options.add(newWindowOff);
        Options.add(newWinSetts);

        WordsMixWithChars = new JMenuItem("Words WILL Mix with everything else");
        WordsStayAlone = new JMenuItem("Words will NOT mix");
        RandomCapsOff = new JMenuItem("Random Caps OFF");
        RandomCapsOn = new JMenuItem("Random Caps ON");
        CurrentSettingsForWords = new JMenuItem(helper.getMenuTxtWords(wordsMix, randomCaps));
        Words = new JMenu("Words");
        Words.add(WordsMixWithChars);
        Words.add(WordsStayAlone);
        Words.add(RandomCapsOn);
        Words.add(RandomCapsOff);
        Words.add(CurrentSettingsForWords);

        numberClumpsOn = new JMenuItem("Numbers WILL be lumped together as one");
        numberClumpsOff = new JMenuItem("Numbers will NOT be lumped together");
        CurrentSettsForNumbers = new JMenuItem(helper.getMenuTxtNums(clumpedNumbers));
        Numbers = new JMenu("Numbers");
        Numbers.add(numberClumpsOn);
        Numbers.add(numberClumpsOff);
        Numbers.add(CurrentSettsForNumbers);
        menuBar = new JMenuBar();
        menuBar.add(Options);
        menuBar.add(Words);
        menuBar.add(Numbers);
        //endregion


        //region Mostly adders
        bottomPanel.add(lettersB);
        bottomPanel.add(lettersNTF);
        bottomPanel.add(upperLettersB);
        bottomPanel.add(upperLettersNTF);
        bottomPanel.add(accentsB);
        bottomPanel.add(accentsNTF);
        bottomPanel.add(upperAccentsB);
        bottomPanel.add(upperAccentsNTF);
        bottomPanel.add(numbersB);
        bottomPanel.add(numbersNTF);
        bottomPanel.add(specCharsB);
        bottomPanel.add(specCharsNTF);
        bottomPanel.add(wordsB);
        bottomPanel.add(wordNTF);
        bottomPanel.add(mostCommonB);
        bottomPanel.add(mostCommonNTF);
        bottomPanel.add(limitLengthB);
        bottomPanel.add(limitLengthNTF);

        frame.add(pwordField, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.CENTER);
        frame.add(go, BorderLayout.SOUTH);

        frame.setJMenuBar(menuBar);
        //endregion


        //region JFRame BP
        try {
            URL url = new URL("https://raw.githubusercontent.com/Epacnoss/PasswordGenerator/master/logo.png");
            Image image = ImageIO.read(url);
            frame.setIconImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        //endregion


        //region Action Listeners
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean wb = wordsB.getState();
                boolean mcb = mostCommonB.getState();

                boolean nb = numbersB.getState();
                boolean lb = lettersB.getState();
                boolean sb = specCharsB.getState();
                boolean ub = upperLettersB.getState();
                boolean ab = accentsB.getState();
                boolean aub = upperAccentsB.getState();
                boolean limB = limitLengthB.getState();

                if(!wordsMix && wb)
                {
                    nb = false;
                    lb = false;
                    sb = false;
                    ub = false;
                    ab = false;
                    aub = false;


                    numbersB.setState(false);
                    lettersB.setState(false);
                    specCharsB.setState(false);
                    upperLettersB.setState(false);
                    accentsB.setState(false);
                    upperAccentsB.setState(false);
                }

                int ni = tryInt(numbersNTF.getText());
                int li = tryInt(lettersNTF.getText());
                int si = tryInt(specCharsNTF.getText());
                int ui = tryInt(upperLettersNTF.getText());
                int ai = tryInt(accentsNTF.getText());
                int uai = tryInt(upperLettersNTF.getText());
                int wi = tryInt(wordNTF.getText());
                int hmmc = tryInt(mostCommonNTF.getText());
                int limit = tryInt(limitLengthNTF.getText());

                if(newGUi.hasItBeenOn())
                    gen++;

                String temp = PasswordGen.getPassword(nb, lb, sb, ub, ab, aub, wb, getLetters(false),getSCs(), getLetters(true), getAccents(false), getAccents(true), helper.getWords(randomCaps, limit, limB), helper.getWordsLimited(randomCaps, hmmc, limit, limB), ni, li, si, ui, ai, uai, wi, mcb, clumpedNumbers, newGUi, newWindowChanges, gen);

                pwordField.setText(temp);
            }
        });

        Copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = pwordField.getText();
                StringSelection stringSelection = new StringSelection(word);
                Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
                clpbrd.setContents(stringSelection, null);
                System.out.println(word + " has been copied to the keyboard.");
            }
        });

        WordsMixWithChars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wordsMix = true;
                CurrentSettingsForWords.setText(helper.getMenuTxtWords(wordsMix, randomCaps));
            }
        });
        WordsStayAlone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wordsMix = false;
                CurrentSettingsForWords.setText(helper.getMenuTxtWords(wordsMix, randomCaps));
            }
        });

        RandomCapsOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomCaps = true;
                CurrentSettingsForWords.setText(helper.getMenuTxtWords(wordsMix, randomCaps));
            }
        });
        RandomCapsOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomCaps = false;
                CurrentSettingsForWords.setText(helper.getMenuTxtWords(wordsMix, randomCaps));
            }
        });

        numberClumpsOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clumpedNumbers = true;
                CurrentSettsForNumbers.setText(helper.getMenuTxtNums(clumpedNumbers));
            }
        });
        numberClumpsOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clumpedNumbers = false;
                CurrentSettsForNumbers.setText(helper.getMenuTxtNums(clumpedNumbers));
            }
        });

        newWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gen = 0;
                newGUi.START();
            }
        });
        newWidnowOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newWindowChanges = true;
                newWinSetts.setText(helper.getChanger(newWindowChanges));
            }
        });
        newWindowOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newWindowChanges = false;
                newWinSetts.setText(helper.getChanger(newWindowChanges));
            }
        });
        //endregion

    }

    public char[] getLetters (boolean lowercase)
    {
        char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] upper = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        if(lowercase)
            return letters;
        else
            return upper;
    }
    public char[] getAccents (boolean lowercase)
    {
        char[] letters = {'è', 'é', 'ê', 'ë', 'ē', 'ė', 'ę', 'ÿ', 'û', 'ü', 'ù', 'ú', 'ū', 'î', 'ï', 'í', 'ī', 'į', 'ì', 'ô', 'ö', 'ò', 'ó', 'œ', 'ø', 'ō', 'õ', 'à', 'á', 'â', 'ä', 'æ', 'ã', 'å', 'ā', 'ß', 'ś', 'š', 'ł', 'ž', 'ź', 'ż', 'ç', 'ć', 'č', 'ñ', 'ń'};
        char[] upper = {'È', 'É', 'Ê', 'Ë', 'Ē', 'Ė', 'Ę', 'Ÿ', 'Û', 'Ü', 'Ù', 'Ú', 'Ū', 'Î', 'Ï', 'Í', 'Ī', 'Į', 'Ì', 'Ô', 'Ö', 'Ò', 'Ó', 'Œ', 'Ø', 'Ō', 'Õ', 'À', 'Á', 'Â', 'Ä', 'Æ', 'Ã', 'Å', 'Ā', 'Ś', 'Š', 'Ł', 'Ž', 'Ź', 'Ż', 'Ç', 'Ć', 'Č', 'Ñ', 'Ń'};
        if(lowercase)
            return letters;
        else
            return upper;
    }
    public char[] getSCs ()
    {
        char[] specChars = {'`', '~', '§', '±', '!', '@', '€', '£', '#', '$', '¢', '›', '%', '∞', 'ﬁ', '^', '&', '*', '(', '·', '°', '‡', '¶', '•', 'ª', ')', 'ª', '‚', '-', '_', '+', '=', '+', '{', '[', '“', '}', ']', '‘', ';', ':', '…', '"', '\'', 'æ', '\\', '|', '«', '»', '<', ',', '≤', '¯', '.', '>', '≥', '˘', '?', '/'};
        return specChars;
    }

    public int tryInt (String i)
    {
        try {
            int j = Integer.parseInt(i);
            return j;
        } catch (Exception e) {
            return 0;
        }
    }
}
