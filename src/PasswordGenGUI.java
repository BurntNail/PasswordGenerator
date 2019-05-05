import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordGenGUI {

    private JFrame frame;
    private JTextField pwordField, lettersNTF, numbersNTF, specCharsNTF, upperLettersNTF, accentsNTF, upperAccentsNTF;
    private Checkbox lettersB, numbersB, specCharsB, upperLettersB, accentsB, upperAccentsB;
    private Dimension frameSize, PWordFieldSize;
    private JPanel bottomPanel;
    private JButton go;
    private JMenuBar menuBar;
    private JMenu Options;
    private JMenuItem Copy;


    public PasswordGenGUI (int w, int h)
    {
        //region Mostly init
        frameSize = new Dimension(w, h);
        PWordFieldSize = new Dimension(frameSize.width, frameSize.height / 6);

        frame = new JFrame("Password Generator");
        frame.setPreferredSize(frameSize);
        frame.setLayout(new BorderLayout());

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(6, 2));

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

        lettersB = new Checkbox("Do you want Lowercase letters?", true);
        numbersB = new Checkbox("Do you want numbers?", true);
        specCharsB = new Checkbox("Do you want special characters?", true);
        upperLettersB = new Checkbox("Do you want Uppercase letters?", true);
        accentsB = new Checkbox("Do you want Lowercase characters with accents?", true);
        upperAccentsB = new Checkbox("Do you want Uppercase characters with accents?", true);

        Copy = new JMenuItem("Copy");
        Options = new JMenu("Options");
        Options.add(Copy);
        menuBar = new JMenuBar();
        menuBar.add(Options);
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

        frame.add(pwordField, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.CENTER);
        frame.add(go, BorderLayout.SOUTH);

        frame.setJMenuBar(menuBar);
        //endregion

        //region JFRame BP
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        //endregion

        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean nb = numbersB.getState();
                boolean lb = lettersB.getState();
                boolean sb = specCharsB.getState();
                boolean ub = upperLettersB.getState();
                boolean ab = accentsB.getState();
                boolean aub = upperAccentsB.getState();

                int ni = tryInt(numbersNTF.getText());
                int li = tryInt(lettersNTF.getText());
                int si = tryInt(specCharsNTF.getText());
                int ui = tryInt(upperLettersNTF.getText());
                int ai = tryInt(accentsNTF.getText());
                int uai = tryInt(upperLettersNTF.getText());

                String temp = PasswordGen.getPassword(nb, lb, sb, ub, ab, aub, getLetters(false),getSCs(), getLetters(true), getAccents(false), getAccents(true), ni, li, si, ui, ai, uai);
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
