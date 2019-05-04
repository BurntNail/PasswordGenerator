import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordGenGUI {

    private JFrame frame;
    private JTextField pwordField, lettersNTF, numbersNTF, specCharsNTF, upperLettersNTF;
    private Checkbox lettersB, numbersB, specCharsB, upperLettersB;
    private Dimension frameSize, PWordFieldSize, goButtonSize;
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
        bottomPanel.setLayout(new GridLayout(4, 2));

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

        lettersB = new Checkbox("Do you want Lowercase letters?", true);
        numbersB = new Checkbox("Do you want numbers?", true);
        specCharsB = new Checkbox("Do you want special characters?", true);
        upperLettersB = new Checkbox("Do you want Uppercase letters?", true);

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

                int ni = tryInt(numbersNTF.getText());
                int li = tryInt(lettersNTF.getText());
                int si = tryInt(specCharsNTF.getText());
                int ui = tryInt(upperLettersNTF.getText());

                String temp = PasswordGen.getPassword(nb, lb, sb, ub, getLetters(true),getSCs(), getLetters(false), ni, li, si, ui);
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
