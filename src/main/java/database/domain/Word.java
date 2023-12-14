package database.domain;

public class Word {
    private int id;
    private String word;
    private String definition;
    public Word(int id, String word, String definition) {
        this.id = id;
        this.word = word;
        this.definition = definition;
    }
    public int getId() {
        return id;
    }
    public String getWord() {
        return word;
    }

    public String getDef() {
        return definition;
    }
}
