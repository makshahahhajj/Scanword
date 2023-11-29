package game.frames;

import database.Database;
import database.persistance.WordPersistence;
import game.scanword.Scanword;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    WordPersistence words = new WordPersistence();
    JButton add = new JButton("Добавить");
    JButton play = new JButton("Играть");
    JTextArea description = new JTextArea();
    JTextField word = new JTextField();
    JLabel desc_label = new JLabel("Введите описание");
    JLabel word_label = new JLabel("Введите слово");


//    Words words = new Words();

    private final ActionListener playGame = e -> {
        this.dispose();
        new Scanword(words);
    };
    private final ActionListener addWord = e -> {
        words.putWord(word.getText(), description.getText());
//        words.print();
    };


    public Frame() {
        Database db = Database.getInstance();
        db.clear();

        play.addActionListener(playGame);
        add.addActionListener(addWord);
    }

    public void show_window() {
        add.setBounds(300, 700, 100, 50);
        word.setBounds(100,230, 300, 20);
        word_label.setBounds(150,170,150, 100);
        desc_label.setBounds(150,40,150,100);
        play.setBounds(800,700,100,50);
        description.setBounds(100,100, 300, 100);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000,800);
        this.setVisible(true);
        this.setLayout(null);

        this.add(play);
        this.add(description);
        this.add(desc_label);
        this.add(word_label);
        this.add(word);
        this.add(add);
    }

}
