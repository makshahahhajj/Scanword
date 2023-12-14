package database.persistance;

import database.domain.Word;

import java.util.Map;
import java.util.Objects;

public class WordPersistance_Convert_Test {
    private static final WordPersistence wordpersistance = new WordPersistence();

    public static void main(String[] args) {

        Map<String, String> input = Map.of(
                "word", "Max",
                "definition", "Razin"
        );

        Word word = wordpersistance.convertWord(input);

        if (!Objects.equals(word.getWord(), "Max")) {
            throw new RuntimeException(
                    "Invalid. Actual infl: " + word.getWord()
            );
        }

        if (!Objects.equals(word.getDef(), "Razin")) {
            throw new RuntimeException(
                    "Invalid. Actual infl: " + word.getDef()
            );
        }
    }



}