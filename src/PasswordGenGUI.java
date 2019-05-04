import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.StringJoiner;

public class PasswordGenGUI {

    private JFrame frame;
    private JTextField pwordField, lettersNTF, numbersNTF, specCharsNTF;
    private Checkbox lettersB, numbersB, specCharsB;
    private Dimension frameSize, PWordFieldSize;
    private JPanel bottomPanel;
    private JButton go;


    public PasswordGenGUI (int w, int h)
    {
        //region Mostly init
        frameSize = new Dimension(w, h);
        PWordFieldSize = new Dimension(frameSize.width, frameSize.height / 12);

        frame = new JFrame("Password Generator");
        frame.setPreferredSize(frameSize);
        frame.setLayout(new BorderLayout());

        go = new JButton("Lets Go!");
        go.setPreferredSize(PWordFieldSize);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(3, 2));

        pwordField = new JTextField("Password Goes Here");
        pwordField.setEditable(false);
        pwordField.setPreferredSize(PWordFieldSize);

        lettersNTF = new JTextField("2");
        lettersNTF.setToolTipText("How many letters?");
        numbersNTF = new JTextField("2");
        numbersNTF.setToolTipText("How many numbers?");
        specCharsNTF = new JTextField("2");
        specCharsNTF.setToolTipText("How many Special Charcters?");

        lettersB = new Checkbox("Do you want letters?", true);
        numbersB = new Checkbox("Do you want numbers?", true);
        specCharsB = new Checkbox("Do you want special characters?", true);
        //endregion

        //region Mostly adders
        bottomPanel.add(lettersB);
        bottomPanel.add(lettersNTF);
        bottomPanel.add(numbersB);
        bottomPanel.add(numbersNTF);
        bottomPanel.add(specCharsB);
        bottomPanel.add(specCharsNTF);

        frame.add(pwordField, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.CENTER);
        frame.add(go, BorderLayout.SOUTH);
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

                int ni = tryInt(numbersNTF.getText());
                int li = tryInt(lettersNTF.getText());
                int si = tryInt(specCharsNTF.getText());

                String temp = PasswordGen.getPassword(nb, lb, sb, getLetters(), getSCs(), ni, li, si);
                pwordField.setText(temp);
            }
        });


    }

    public char[] getLetters ()
    {
        char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        return letters;
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
