package assignment_problems;
import java.util.*;

public class WordFrequency {

    static void printFilteredWordFrequency(String feedback) {

        // Convert to lowercase
        String text = feedback.toLowerCase();

        // Remove punctuation
        text = text.replace(".", "");
        text = text.replace(",", "");

        // Split into words
        String[] words = text.split("\\s+");

        String[] stopWords = {
                "the", "was", "and", "a", "is", "of", "in"
        };

        HashMap<String, Integer> frequency = new HashMap<>();

        for (int i = 0; i < words.length; i++) {

            String word = words[i];

            boolean isStopWord = false;

            // Check whether word is a stop word
            for (int j = 0; j < stopWords.length; j++) {

                if (word.equals(stopWords[j])) {
                    isStopWord = true;
                    break;
                }
            }

            // Skip stop words
            if (isStopWord) {
                continue;
            }

            // Count word
            frequency.put(
                    word,
                    frequency.getOrDefault(word, 0) + 1
            );
        }

        // Sort entries by frequency in descending order
        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(frequency.entrySet());

        list.sort(
                (a, b) -> b.getValue().compareTo(a.getValue())
        );

        // Print result
        for (Map.Entry<String, Integer> entry : list) {

            System.out.println(
                    entry.getKey() + ": " + entry.getValue()
            );
        }
    }

    public static void main(String[] args) {

        String feedback =
                "The mentor was great, the session was great and clear.";

        printFilteredWordFrequency(feedback);
    }
}
