package database.persistance;

import database.Database;
import database.domain.Word;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class WordPersistence {

    private final Database database = Database.getInstance();
    private static final String ID = "id";
    private static final String WORD_NAME = "word";
    private static final String DEF_NAME = "definition";

    public void putWord(String word, String definition) {
        List<Word> words = this.getAll();
        if (words.isEmpty()) {
            database.insertWord(word, definition);
        } else {
            for(Word w: words) {
                if(!(w.getWord().equals(word) && w.getDef().equals(definition))) {
                    database.insertWord(word, definition);
                }
            }
        }
    }

    protected Word convertWord(Map<String, String> fromBd) {
        return new Word(
                Integer.parseInt(fromBd.get(ID)),
                fromBd.get(WORD_NAME),
                fromBd.get(DEF_NAME)
        );
    }

    public List<Word> getAll() {
        List<Map<String, String>> fromDB = database.selectAll(
                ID,
                WORD_NAME,
                DEF_NAME
        );

        return fromDB.stream()
                .map(this::convertWord)
                .toList();
    }

    public void deleteAll() {
        database.clear();
    }



}
