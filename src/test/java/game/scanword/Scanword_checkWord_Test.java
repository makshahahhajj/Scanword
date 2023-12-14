package game.scanword;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.assertj.core.api.Java6BDDAssertions.then;

public class Scanword_checkWord_Test {
    private final Scanword scanword  = new Scanword();

    @Test
    @DisplayName("checkWord method is passed")
    public void checkWordTest() {
        JPanel panel = new JPanel();
        panel.setName("abcd");

        for (int i = 97; i < 101; ++i) {
            JTextField field = new JTextField();
            field.setText(Character.toString((char)i));
            panel.add(field);
        }

        then(scanword.checkWord(panel)).isTrue();

        panel.setName("egege");
        then(scanword.checkWord(panel)).isFalse();

    }
}
