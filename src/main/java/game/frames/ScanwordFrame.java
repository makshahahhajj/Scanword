package game.frames;

import consts.Consts;
import database.persistance.WordPersistence;
import game.scanword.Scanword;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
                    toColor(i, Consts.COLOR_GREEN);
                    scanword.guess(getPanel(i).getName(), true);
                } else {
                    toColor(i, Consts.COLOR_RED);
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

    private KeyListener type(int currPanel, int currField) {
        return new KeyListener() {
            public void keyTyped(KeyEvent e) {}

            public void keyPressed(KeyEvent e) {
                if (currField < scanword.getWordMean(currPanel).length() - 1 && e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {

                    JTextField nextField = (JTextField) getPanel(currPanel).getComponent(currField + 1);
                    JTextField currentField = (JTextField) getPanel(currPanel).getComponent(currField);

                    nextField.requestFocus();

                    if(nextField.getText().equals("") && !currentField.getText().equals("")) {
                        nextField.setText(Character.toString(e.getKeyChar()));
                    }
                }
                if (currField > 0 && e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    JTextField previousField = (JTextField) getPanel(currPanel).getComponent(currField - 1);
                    previousField.requestFocus();
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

        tries.setFont(new Font("Consolas", Font.PLAIN, Consts.SCANWORDFRAME_FONTSIZE));
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
            updateConstraint(Consts.SECOND_COLUMN, i + 1);
            this.add(panel, constraint);

            updateConstraint(Consts.THIRD_COLUMN, i + 1);
            this.add(label, constraint);

            panels.add(panel);

        }
        int last_row = size + 1;

        updateConstraint(Consts.FIRST_COLUMN, last_row);
        clear.setPreferredSize(new Dimension(
                Consts.SCANWORDFRAME_BUTTON_WIDTH,
                Consts.SCANWORDFRAME_BUTTON_HEIGHT
            )
        );

        clear.addActionListener(clearClick);
        this.add(clear, constraint);

        updateConstraint(Consts.THIRD_COLUMN, last_row);

        check.setPreferredSize(new Dimension(
                Consts.SCANWORDFRAME_BUTTON_WIDTH,
                Consts.SCANWORDFRAME_BUTTON_HEIGHT
            )
        );

        check.addActionListener(checkClick);
        this.add(check, constraint);
    }

    private void updateConstraint(int column, int row) {
        constraint.gridx = column;
        constraint.gridy = row;
    }

    private void setFrameParams() {
        this.setSize(Consts.SCANWORDFRAME_WIDTH, Consts.SCANWORDFRAME_HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        LayoutManager grid = new GridBagLayout();
        this.setLayout(grid);

    }

    private void setConstraint() {
        constraint.gridheight = Consts.SCANWORDFRAME_GRIDHEIGHT;
        constraint.gridwidth = Consts.SCANWORDFRAME_GRIDHWIDTH;

        constraint.insets = new Insets(
                Consts.SCANWORDFRAME_INSETS_TOP,
                Consts.SCANWORDFRAME_INSETS_LEFT,
                Consts.SCANWORDFRAME_INSETS_BOTTOM,
                Consts.SCANWORD_INSETS_RIGHT
                );
    }

    private void toColor(int i, int color) {
        for (Component field : getPanel(i).getComponents()) {
            ((JTextField) field).setBorder(new LineBorder(new Color(color)));
        }

    }

    private PlainDocument setLength (JTextField field){
            return new PlainDocument() {
                public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                    if (str == null || field.getText().length() >= Consts.SCANWORDFRAME_FIELDS_MAXLENGTH) {
                        return;
                    }

                    super.insertString(offs, str, a);
                }
            };
        }
    private JTextField getFormattedField (int i, int j) {

        JTextField field = new JTextField();
        field.setFont(new Font("Consolas", Font.PLAIN, Consts.SCANWORDFRAME_FIELDS_FONTSIZE));

        field.setHorizontalAlignment(JTextField.CENTER);

        field.setDocument(setLength(field));

        field.addKeyListener(type(i, j));

        field.setPreferredSize(new Dimension(
                Consts.SCANWORDFRAME_FIELDS_WIDTH,
                Consts.SCANWORDFRAME_FIELDS_HEIGHT
            )
        );


        return field;
        }
    private JPanel getPanel (int i) {
        return panels.get(i);
    }

}
