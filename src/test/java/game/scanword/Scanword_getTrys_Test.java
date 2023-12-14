package game.scanword;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6BDDAssertions.then;

public class Scanword_getTrys_Test {
    private final Scanword scanword  = new Scanword();

    @Test
    @DisplayName("getTrys method is passed")
    public void getTrysTest() {

        scanword.getTrys();

        then(scanword.getTrys() == 5).isTrue();

        scanword.useTry();

        then(scanword.getTrys() == 4).isTrue();

    }
}
