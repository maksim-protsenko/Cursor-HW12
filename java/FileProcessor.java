
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileProcessor {
    private final List<String> allWords;
    private final List<String> swearWords;
    private final List<String> withoutSwearWords;
    private int quantityOfSwearWords;
    private String highFrequencyWord;

    public FileProcessor(String filePath, String filePathSwearWords) {
        allWords = getAllWords(filePath);
        swearWords = getSwearWords(filePathSwearWords);
        withoutSwearWords = new ArrayList<>();
        withoutSwearWords.addAll(allWords);
    }

    public List<String> getSwearWords(String filePathSwearWords) {
        List<String> swearWords = getAllWords(filePathSwearWords);
        return swearWords;
    }

    public List<String> getAllWords(String filePath) {
        List<String> allWords = new ArrayList<>();
        String temp;
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNext()) {
                temp = scanner.next().replaceAll("[,. !?()]", "").toLowerCase();
                allWords.add(temp);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File is not found!");
        }
        return allWords;
    }

    public final List<String> getWords() {
        return allWords;
    }

    public final List<String> getSwearWords() {
        return swearWords;
    }

    public final int getQuantityOfSwearWords() {
        return quantityOfSwearWords;
    }

    public String[] getExcludedWords() {
        List<String> excludedWords = new ArrayList<>();
        for (String word : allWords) {
            if (word.length() < 3 || swearWords.contains(word)) {
                excludedWords.add(word);
                if (swearWords.contains(word)) {
                    quantityOfSwearWords++;
                }
            }
        }

        withoutSwearWords.removeAll(excludedWords);
        return excludedWords.toArray(new String[0]);
    }

    public void showHighFrequencyWord() {
        Map<String, Integer> map = new HashMap<>();
        int maxFrequency = 1;
        for (String word : withoutSwearWords) {
            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                int frequencyOfWord = map.get(word);
                map.put(word, frequencyOfWord + 1);
                if (frequencyOfWord > maxFrequency) {
                    maxFrequency = frequencyOfWord;
                    highFrequencyWord = word;
                }
            }
        }
        System.out.println("The most repeated word is \"" + highFrequencyWord + "\" which repeated " + map.get(highFrequencyWord) + " times.");
    }
}
