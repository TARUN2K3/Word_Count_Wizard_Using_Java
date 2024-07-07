import javax.swing.*;

public class AboutDialog extends JDialog {

    public AboutDialog(JFrame parent) {
        super(parent, "About Word Counter", true);
        JLabel aboutLabel = new JLabel("<html><h2>Word Counter</h2><p>Version 1.0</p><p>Developed by [Your Name]</p></html>");
        add(aboutLabel);
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
