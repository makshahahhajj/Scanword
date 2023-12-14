package game.scanword;

import database.domain.Word;
import database.persistance.WordPersistence;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scanword {
    private List<Word> words;
    private int trys = 5;

    private HashMap<String, Boolean> guessed = new HashMap<>();

    public Scanword(WordPersistence wordPersistence) {
        this.words = wordPersistence.getAll();
    }
    public Scanword() {

    }
    public boolean checkWord(JPanel panel) {

        String result = "";

        for(Component field : panel.getComponents()) {
            result += ((JTextField)field).getText();
        }

        return panel.getName().equals(result);
    }

    public void guess(String word, boolean isGuessed) {
        guessed.put(word, isGuessed);
    }

    public boolean isAllGuessed() {
        boolean isWin = true;

        for (String key: new ArrayList<>(guessed.keySet())) {
            isWin = isWin && guessed.get(key);
        }

        return isWin;
    }

    public void clearGuessed() {
        guessed.clear();
    }

    public String getWordDef(int i) {
        return words.get(i).getDef();
    }

    public String getWordMean(int i) {
        return words.get(i).getWord();
    }

    public int getWordsSize() {
        return words.size();
    }

    public boolean isNotTries() {
        return trys == 0;
    }

    public void useTry() {
        trys -= 1;
    }

    public int getTrys() {
        return trys;
    }

}
