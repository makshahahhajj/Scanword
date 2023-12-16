package game.frames;

import consts.Consts;

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

        message.setFont(new Font("Consolas", Font.PLAIN, Consts.END_FRAME_FONTSIZE));
        this.add(message, constraint);

        constraint.insets = new Insets(
                Consts.END_FRAME_INSETS_TOP,
                Consts.END_FRAME_INSETS_LEFT,
                Consts.END_FRAME_INSETS_BOTTOM,
                Consts.END_FRAME_INSETS_RIGHT
        );

        updateConstraint(0,1, 1 ,1);
        this.add(close, constraint);

        updateConstraint(1, 1, 1, 1);
        this.add(again, constraint);

        close.addActionListener(closeClick);
        again.addActionListener(newClick);

    }

    public void show_window() {
        this.setSize(Consts.END_FRAME_WINDOWWIDTH, Consts.END_FRAME_WINDOWHEIGHT);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void setStartConstraint() {
        constraint.gridheight = Consts.END_FRAME_GRIDHEIGHT;
        constraint.gridwidth = Consts.END_FRAME_GRIDWIDTH;
    }

    private void updateConstraint(int gridx, int gridy, int gridHeight, int gridWidth) {
        constraint.gridx = gridx;
        constraint.gridy = gridy;
        constraint.gridheight = gridHeight;
        constraint.gridwidth = gridWidth;
    }

}
