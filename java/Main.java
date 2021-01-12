import java.util.List;

public class Main {

    public static void main(String[] args) {
        FileProcessor song = new FileProcessor("src/main/resources/source.txt", "src/main/resources/swear words.txt");
        List<String> allWords = song.getWords();
        System.out.println("Quantity of all words in this song is " + allWords.size());
        System.out.println("Quantity of words with length less than 3 letters is " + song.getExcludedWords().length + ", including " + song.getQuantityOfSwearWords() + " \"swear\" words: " + song.getSwearWords());
        song.showHighFrequencyWord();
    }
}
