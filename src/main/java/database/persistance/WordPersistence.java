package database.persistance;

import database.Database;
import database.domain.Word;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class WordPersistence {

    private final Database database = Database.getInstance();
    private static final String TABLE_NAME = "words";
    private static final String WORD_NAME = "word";
    private static final String DEF_NAME = "definition";

    public void putWord(String word, String definition) {
        String sql = """
        insert into scanword.words
        (word, definition)
        values
        ('%s', '%s')
        """;
        database.execute(String.format(sql, word, definition));
    }

    protected Word convertWord(Map<String, String> fromBd) {
        return new Word(
                fromBd.get(WORD_NAME),
                fromBd.get(DEF_NAME)
        );
    }

    public List<Word> getAll() {
        List<Map<String, String>> fromDB = database.selectAll(
                WORD_NAME,
                DEF_NAME
        );

        return fromDB.stream()
                .map(this::convertWord)
                .toList();
    }
}
