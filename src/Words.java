import java.util.ArrayList;
import java.util.HashMap;

public class Words {
    HashMap<String, String> words = new HashMap<>();
    HashMap<String, Boolean> guessed = new HashMap<>();

    public void addWord(String word, String description) {
        words.put(description, word);
        guessed.put(word, false);
    }

    public void guess(String word, boolean flag) {
        guessed.put(word, flag);
    }

    public HashMap<String, Boolean> getGuessed() {
        return guessed;
    }

    public int size() {
        return words.size();
    }

    public void clear() {
        words.clear();
    }

    public String get(String key) {
        return words.get(key);
    }

    public ArrayList<String> returnDesc() {
       return  new ArrayList<>(words.keySet());
    }

    public void print() {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(words.keySet());
        for (String key: list) {
            System.out.println(key);
            System.out.println(words.get(key));
        }
    }
}
