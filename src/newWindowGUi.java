import javax.swing.*;
import java.awt.*;

public class newWindowGUi {

    private JFrame frame;
    private JTextArea txtArea;
    private boolean hasBeenOn;

    public newWindowGUi( int w, int h) {
        frame = new JFrame("PASSWORD - Don't show to other people...");
        frame.setPreferredSize(new Dimension(w, h));
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        txtArea = new JTextArea();
        frame.add(txtArea);

        frame.pack();
    }

    public void START ()
    {
        hasBeenOn = true;
        frame.setVisible(true);
    }

    public void changeTxt (String pword)
    {
        txtArea.setText(pword);
    }

    public boolean hasItBeenOn() {
        return hasBeenOn;
    }
}
