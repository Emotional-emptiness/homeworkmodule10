import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        String fileName = "words.txt";
        Map<String, Integer> wordFrequency = countWordFrequency(fileName);
        printWordFrequency(wordFrequency);
    }

    public static Map<String, Integer> countWordFrequency(String fileName) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                for (String word : words) {
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordFrequency;
    }

    public static void printWordFrequency(Map<String, Integer> wordFrequency) {
        Map<String, Integer> sortedFrequency = new TreeMap<>((word1, word2) -> {
            int freq1 = wordFrequency.get(word1);
            int freq2 = wordFrequency.get(word2);
            if (freq1 == freq2) {
                return word1.compareTo(word2);
            } else {
                return Integer.compare(freq2, freq1);
            }
        });

        sortedFrequency.putAll(wordFrequency);

        for (Map.Entry<String, Integer> entry : sortedFrequency.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}

