package database.persistance;

import database.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Java6BDDAssertions.then;

public class WordPersistance_DeleteAll_Test {
    private final WordPersistence wordPersistence = new WordPersistence();

    @Test
    @DisplayName("deleteAll method is passed")
    public void deleteAllTes() {
        wordPersistence.putWord("Alex", "love football");
        wordPersistence.putWord("Max", "love hockey");

        wordPersistence.deleteAll();

        List<Word> words = wordPersistence.getAll();

        then(words.isEmpty()).isTrue();

    }
}
