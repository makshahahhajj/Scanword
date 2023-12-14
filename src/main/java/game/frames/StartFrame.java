package game.frames;

import database.persistance.WordPersistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {
    private boolean added = false;
    WordPersistence words_to_db = new WordPersistence();
    JButton add = new JButton("Добавить");
    JButton play = new JButton("Играть");
    JTextArea description = new JTextArea();
    JTextField word = new JTextField();
    JLabel desc_label = new JLabel("Введите описание");
    JLabel word_label = new JLabel("Введите слово");

    private final ActionListener playGame = e -> {
        if (added) {
            this.dispose();

            ScanwordFrame scanwordFrame = new ScanwordFrame(words_to_db);
            scanwordFrame.showScanword();
        }
    };
    private final ActionListener addWord = e -> {
        String word_text = word.getText();
        String desc_text = description.getText();

        if (word_text.matches("\\S+") && !desc_text.matches("(\\s)+|(^$)")) {
            added = true;
            words_to_db.putWord(word.getText(), description.getText());
        }

    };


    public StartFrame() {
        words_to_db.deleteAll();

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
