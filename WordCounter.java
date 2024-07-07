import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class WordCounter extends JFrame implements ActionListener {

    private JTextArea textArea;
    private JButton countButton, clearButton;
    private JLabel wordCountLabel, charCountLabel;
    private JMenuBar menuBar;
    private JMenu fileMenu, helpMenu;
    private JMenuItem loadItem, saveItem, exitItem, aboutItem;

    public WordCounter() {
        super("Word Counter");

        // Create text area and buttons
        textArea = new JTextArea(10, 30);
        countButton = new JButton("Count");
        clearButton = new JButton("Clear");

        // Create labels with initial values
        wordCountLabel = new JLabel("Word count: 0");
        charCountLabel = new JLabel("Character count: 0");

        // Create menu bar
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");
        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");
        aboutItem = new JMenuItem("About");

        // Add menu items to menus
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        helpMenu.add(aboutItem);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        // Add components to frame
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.add(countButton);
        southPanel.add(clearButton);

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(wordCountLabel, BorderLayout.WEST);
        northPanel.add(charCountLabel, BorderLayout.EAST);

        panel.add(southPanel, BorderLayout.SOUTH);
        panel.add(northPanel, BorderLayout.NORTH);
        add(panel);

        // Register action listeners
        countButton.addActionListener(this);
        clearButton.addActionListener(e -> textArea.setText(""));
        loadItem.addActionListener(e -> loadTextFromFile());
        saveItem.addActionListener(e -> saveTextToFile());
        exitItem.addActionListener(e -> System.exit(0));
        aboutItem.addActionListener(e -> new AboutDialog(this));

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Get text from text area
        String text = textArea.getText().trim();

        // Split text into words
        String[] words = text.split("\\s+");

        // Count number of words and characters
        int wordCount = text.isEmpty() ? 0 : words.length;
        int charCount = text.length();

        // Update labels
        wordCountLabel.setText("Word count: " + wordCount);
        charCountLabel.setText("Character count: " + charCount);
    }

    private void loadTextFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                String content = FileUtils.readFile(fileChooser.getSelectedFile().getAbsolutePath());
                textArea.setText(content);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveTextToFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                FileUtils.writeFile(fileChooser.getSelectedFile().getAbsolutePath(), textArea.getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WordCounter());
    }
}
