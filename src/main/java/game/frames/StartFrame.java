package game.frames;

import consts.Consts;
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
        add.setBounds(Consts.ADD_X, Consts.ADD_Y, Consts.ADD_WIDTH, Consts.ADD_HEIGHT);

        word.setBounds(
                Consts.WORD_FIELD_X,
                Consts.WORD_FIELD_Y,
                Consts.WORD_FIELD_WIDTH,
                Consts.WORD_FIELD_HEIGHT
            );

        word_label.setBounds(
                Consts.WORD_LABEL_X,
                Consts.WORD_LABEL_Y,
                Consts.WORD_LABEL_WIDTH,
                Consts.WORD_LABEL_HEIGHT
            );

        desc_label.setBounds(
                Consts.DESC_LABEL_X,
                Consts.DESC_LABEL_Y,
                Consts.DESC_LABEL_WIDTH,
                Consts.DESC_LABEL_HEIGHT
            );

        play.setBounds(
                Consts.PLAY_BUTTON_X,
                Consts.PLAY_BUTTON_Y,
                Consts.PLAY_BUTTON_WIDTH,
                Consts.PLAY_BUTTON_HEIGHT
            );

        description.setBounds(
                Consts.DESC_FIELD_X,
                Consts.DESC_FIELD_Y,
                Consts.DESC_FIELD_WIDTH,
                Consts.DESC_FIELD_HEIGHT
            );

        word.setFont(new Font("Consolas", Font.PLAIN, Consts.WORD_FIELD_FONT));
        description.setFont(new Font("Consolas", Font.PLAIN, Consts.DESC_FIELD_FONT));

        description.setLineWrap(true);
    }

    private void setFrameParams() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Consts.STARTFRAME_WIDTH,Consts.STARTFRAME_HEIGHT);
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
