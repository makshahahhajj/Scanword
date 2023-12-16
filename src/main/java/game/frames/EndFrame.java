package game.frames;

import database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EndFrame extends JFrame {
    JLabel message = new JLabel();
    JButton again = new JButton("Новая игра");
    JButton close = new JButton("Закрыть");
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints constraint = new GridBagConstraints();

    private final ActionListener closeClick= e -> {
        this.dispose();
    };

    private final ActionListener newClick = e -> {
       this.dispose();

       StartFrame frame = new StartFrame();
       frame.show_window();
    };

    public EndFrame(String text) {

        message.setText(text);

        this.setLayout(layout);

        setStartConstraint();

        message.setFont(new Font("Consolas", Font.PLAIN, 25));
        this.add(message, constraint);

        constraint.insets = new Insets(50,50, 0, 50);

        updateConstraint(0,1, 1 ,1);
        this.add(close, constraint);

        updateConstraint(1, 1, 1, 1);
        this.add(again, constraint);

        close.addActionListener(closeClick);
        again.addActionListener(newClick);

    }

    public void show_window() {
        this.setSize(400, 400);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void setStartConstraint() {
        constraint.gridheight = 1;
        constraint.gridwidth = 2;
    }

    private void updateConstraint(int gridx, int gridy, int gridHeight, int gridWidth) {
        constraint.gridx = gridx;
        constraint.gridy = gridy;
        constraint.gridheight = gridHeight;
        constraint.gridwidth = gridWidth;
    }

}
