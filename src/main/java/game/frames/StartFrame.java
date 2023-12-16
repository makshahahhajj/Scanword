package game.frames;

import database.persistance.WordPersistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {
    private boolean added = false;
    private WordPersistence dbWords = new WordPersistence();
    private JButton add = new JButton("Добавить");
    private JButton play = new JButton("Играть");
    private JTextArea description = new JTextArea();
    private JTextField word = new JTextField();
    private JLabel desc_label = new JLabel("Введите описание");
    private JLabel word_label = new JLabel("Введите слово");

    private final ActionListener playGame = e -> {
        if (added) {
            this.dispose();

            ScanwordFrame scanwordFrame = new ScanwordFrame(dbWords);
            scanwordFrame.showScanword();
        }
    };
    private final ActionListener addWord = e -> {
        String word_text = word.getText();
        String desc_text = description.getText();

        if (word_text.matches("\\S+") && !desc_text.matches("(\\s)+|(^$)")) {
            added = true;
            dbWords.putWord(word.getText(), description.getText());
        }

    };


    public StartFrame() {
        dbWords.deleteAll();

        play.addActionListener(playGame);
        add.addActionListener(addWord);
    }

    public void show_window() {
        setElementsParams();

        setFrameParams();

        addElements();
    }

    private void setElementsParams() {
        add.setBounds(100, 300, 100, 50);
        word.setBounds(100,230, 300, 40);
        word_label.setBounds(150,170,150, 100);
        desc_label.setBounds(150,40,150,100);
        play.setBounds(300,300,100,50);
        description.setBounds(100,100, 300, 100);

        word.setFont(new Font("Consolas", Font.PLAIN, 16));
        description.setFont(new Font("Consolas", Font.PLAIN, 18));

        description.setLineWrap(true);
    }

    private void setFrameParams() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500,400);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
    }

    private void addElements() {
        this.add(play);
        this.add(description);
        this.add(desc_label);
        this.add(word_label);
        this.add(word);
        this.add(add);
    }

}
