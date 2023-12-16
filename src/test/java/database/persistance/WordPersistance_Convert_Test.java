package database.persistance;

import database.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;


public class WordPersistance_Convert_Test {
    private static final WordPersistence wordpersistance = new WordPersistence();

    @Test
    @DisplayName("convertWord test passed")
    public void convertTest() {

        Map<String, String> input = Map.of(
                "id", "1",
                "word", "Max",
                "definition", "Razin"
        );

        Word word = wordpersistance.convertWord(input);

        then(word.getId()).isEqualTo(1);
        then(word.getDef()).isEqualTo("Razin");
        then(word.getWord()).isEqualTo("Max");
    }



}