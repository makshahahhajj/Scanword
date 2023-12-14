package game.scanword;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Java6BDDAssertions.then;

public class Scanword_isAllGuessed_Test {
    private final Scanword scanword  = new Scanword();

    @Test
    @DisplayName("isAllGuessed method is passed")
    public void isAllGuessedTest() {
        scanword.guess("Max", true);
        scanword.guess("Razin", true);
        scanword.guess("Alexandrovich", true);

        then(scanword.isAllGuessed()).isTrue();

        scanword.guess("Female", false);

        then(scanword.isAllGuessed()).isFalse();


    }
}
