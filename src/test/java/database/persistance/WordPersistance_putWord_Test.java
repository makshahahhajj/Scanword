package database.persistance;

import database.domain.Word;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Java6BDDAssertions.then;

public class WordPersistance_putWord_Test {
    private final WordPersistence wordPersistence = new WordPersistence();

    @Test
    @DisplayName("putWord method is passed")
    public void pusTes() {
        wordPersistence.deleteAll();

        wordPersistence.putWord("Alex", "love football");
        List<Word> word  = wordPersistence.getAll();

        then(word).isNotNull();
        then(word.size() == 1).isTrue();

    }
}
