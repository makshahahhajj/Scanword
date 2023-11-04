import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.HashMap;

public class Scanword extends JFrame{
    private int size;

    GridBagConstraints constraint = new GridBagConstraints();

    HashMap<JTextField, JLabel> scanword = new HashMap<>();

    JButton clear = new JButton("Сбросить");
    JButton check = new JButton("Проверить");

    Words words = new Words();

    private final ActionListener clearClick = e -> {
        ArrayList<JTextField> list = new ArrayList<>(scanword.keySet());

        for (JTextField field: list) {
            field.setText("");
            field.setBorder(new LineBorder(Color.gray));
        }
    };

   private final ActionListener checkClick = e -> {

        for (JTextField field: new ArrayList<>(scanword.keySet())) {
            if (field.getText().equals(field.getName())) {
                field.setBorder(new LineBorder(Color.green, 1));
                words.guess(field.getText(), true);
            } else {
                field.setBorder(new LineBorder(Color.red, 1));
                words.guess(field.getName(), false);
            }

        }

        boolean isWin = true;

        HashMap<String, Boolean> win = words.getGuessed();

        for (String key: new ArrayList<>(win.keySet())) {
            System.out.println(win.get(key));
           isWin =  isWin && win.get(key);
        }

        if (isWin) {
            this.dispose();
            new WinFrame();
        }



    };

    Scanword(Words words) {
        this.words = words;
        this.size = words.size();

        this.setSize(900, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        LayoutManager grid = new GridBagLayout();
        this.setLayout(grid);
        doScanword();


    }

    private void doScanword() {
        constraint.weightx = 0;
        constraint.weighty = 0;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.gridheight = 1;
        constraint.gridwidth = 1;

        constraint.insets = new Insets(0, 0, 10, 1);

        ArrayList<String> descs = words.returnDesc();
        for (int i = 0; i < size; i++) {
            JLabel label = new JLabel(descs.get(i));
            JTextField field = new JTextField();
            field.setName(words.get(label.getText()));
            constraint.gridx = 0;
            constraint.gridy = i;
            field.setPreferredSize(new Dimension(200,30));
            this.add(field, constraint);
            constraint.gridx = 1;
            constraint.gridy = i;
            this.add(label, constraint);
            scanword.put(field,label);

        }
        constraint.gridx = 0;
        constraint.gridy = size;
        clear.addActionListener(clearClick);
        this.add(clear,constraint);
        constraint.gridx = 1;
        constraint.gridy = size;
        check.addActionListener(checkClick);
        this.add(check,constraint);
    }



}
