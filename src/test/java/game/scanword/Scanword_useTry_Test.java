package game.scanword;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import static org.assertj.core.api.Java6BDDAssertions.then;

public class Scanword_useTry_Test {
    private final Scanword scanword  = new Scanword();

    @Test
    @DisplayName("useTry method is passed")
    public void useTryTest() {

        scanword.useTry();

        then(scanword.getTrys() == 4).isTrue();

    }
}
