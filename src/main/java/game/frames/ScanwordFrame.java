package game.frames;

import database.persistance.WordPersistence;
import game.scanword.Scanword;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class ScanwordFrame extends JFrame {
    private int size = 0;
    private final int first_column = 0;
    private final int second_column = 1;
    private final int third_column = 2;
    private GridBagConstraints constraint = new GridBagConstraints();
    private List<JPanel> panels = new ArrayList<>();
    private JLabel tries = new JLabel();
    private JButton clear = new JButton("Сбросить");
    private JButton check = new JButton("Проверить");
    private Scanword scanword;

    private final ActionListener clearClick = e -> {
        scanword.clearGuessed();
        for (int i = 0; i < panels.size(); i++) {
            for (Component field : getPanel(i).getComponents()) {
                ((JTextField) field).setText("");
            }
        }

    };

    private final ActionListener checkClick = e -> {
        tries.setText("Попытки: " + scanword.getTrys());

        if (scanword.isNotTries() && !scanword.isAllGuessed()) {
            this.dispose();

            EndFrame loseFrame = new EndFrame("Вы проиграли!");
            loseFrame.show_window();
        } else {
            for (int i = 0; i < panels.size(); ++i) {
                if (scanword.checkWord(getPanel(i))) {
                    toColor(i, 65280);
                    scanword.guess(getPanel(i).getName(), true);
                } else {
                    toColor(i, 16711680);
                    scanword.guess(getPanel(i).getName(), false);

                }
            }

            if (scanword.isAllGuessed()) {
                this.dispose();

                EndFrame winFrame = new EndFrame("Вы выиграли!");
                winFrame.show_window();
            }


        }

        scanword.useTry();
    };

    private KeyListener type(int i, int j) {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            public void keyPressed(KeyEvent e) {
                if (j < scanword.getWordMean(i).length() - 1 && e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {

                    JTextField nextField = (JTextField) getPanel(i).getComponent(j + 1);
                    JTextField currentField = (JTextField) getPanel(i).getComponent(j);

                    nextField.requestFocus();

                    if(nextField.getText().equals("") && !currentField.getText().equals("")) {
                        nextField.setText(Character.toString(e.getKeyChar()));
                    }
                }
                if (j > 0 && e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    getPanel(i).getComponent(j - 1).requestFocus();
                }
            }

            public void keyReleased(KeyEvent e) {}
        };
    }


    public ScanwordFrame(WordPersistence words) {
        scanword = new Scanword(words);

        tries.setText("Попытки: " + scanword.getTrys());

        this.size = scanword.getWordsSize();
    }

    public void showScanword() {
        setFrameParams();
        doScanword();
    }

    private void doScanword() {
        setConstraint();

        tries.setFont(new Font("Consolas", Font.PLAIN, 18));
        this.add(tries, constraint);

        for (int i = 0; i < size; i++) {
            JLabel label = new JLabel(scanword.getWordDef(i));

            JPanel panel = new JPanel();

            panel.setName(scanword.getWordMean(i));

            for (int j = 0; j < scanword.getWordMean(i).length(); j++) {

                JTextField field = getFormattedField(i, j);

                updateConstraint(j, i + 1);

                panel.add(field, constraint);
            }

            updateConstraint(second_column, i + 1);
            this.add(panel, constraint);

            updateConstraint(third_column, i + 1);
            this.add(label, constraint);

            panels.add(panel);

        }

        updateConstraint(first_column, size + 1);
        clear.setPreferredSize(new Dimension(140, 40));
        clear.addActionListener(clearClick);
        this.add(clear, constraint);

        updateConstraint(third_column, size + 1);

        check.setPreferredSize(new Dimension(140, 40));
        check.addActionListener(checkClick);
        this.add(check, constraint);
    }

    private void updateConstraint(int column, int row) {
        constraint.gridx = column;
        constraint.gridy = row;
    }

    private void setFrameParams() {
        this.setSize(800, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        LayoutManager grid = new GridBagLayout();
        this.setLayout(grid);

    }

    private void setConstraint() {
        constraint.gridheight = 1;
        constraint.gridwidth = 1;

        constraint.insets = new Insets(0, 0, 10, 1);
    }

    private void toColor(int i, int color) {
        for (Component field : getPanel(i).getComponents()) {
            ((JTextField) field).setBorder(new LineBorder(new Color(color), 1));
        }

    }

    private PlainDocument setLength (JTextField field){
            return new PlainDocument() {
                public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                    if (str == null || field.getText().length() >= 1) {
                        return;
                    }

                    super.insertString(offs, str, a);
                }
            };
        }
    private JTextField getFormattedField (int i, int j) {

        JTextField field = new JTextField();
        field.setFont(new Font("Consolas", Font.PLAIN, 16));
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setDocument(setLength(field));
        field.addKeyListener(type(i, j));
        field.setPreferredSize(new Dimension(50, 30));


        return field;
        }
    private JPanel getPanel (int i) {
        return panels.get(i);
    }

}
