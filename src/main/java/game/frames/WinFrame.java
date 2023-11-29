package game.frames;

import database.Database;
import game.frames.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WinFrame extends JFrame {
    JLabel winText = new JLabel("Вы выиграли!");
    JButton again = new JButton("Новая игра");
    JButton close = new JButton("Закрыть");

    private final ActionListener closeClick= e -> {
        this.dispose();
    };

    private final ActionListener newClick = e -> {
       this.dispose();
       new Frame();
    };

    public WinFrame() {

        this.setSize(400, 400);
        this.setVisible(true);

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraint = new GridBagConstraints();

        this.setLayout(layout);

        constraint.weightx = 0;
        constraint.weighty = 0;
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.gridheight = 1;
        constraint.gridwidth = 2;

        winText.setFont(new Font("Consolas", Font.PLAIN, 25));

        this.add(winText, constraint);

        constraint.weightx = 0;
        constraint.weighty = 0;
        constraint.gridx = 0;
        constraint.gridy = 1;
        constraint.gridheight = 1;
        constraint.gridwidth = 1;
        constraint.insets = new Insets(50,50, 0, 50);

        close.addActionListener(closeClick);
        this.add(close, constraint);

        constraint.weightx = 0;
        constraint.weighty = 0;
        constraint.gridx = 1;
        constraint.gridy = 1;
        constraint.gridheight = 1;
        constraint.gridwidth = 1;

        again.addActionListener(newClick);
        this.add(again, constraint);
    }
}
