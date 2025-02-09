package lab0;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvParser {

    static int counter = 0;

    public static List<String> readFile(String fileName) {
        try {
            Path path = Paths.get(fileName);
            return Files.readAllLines(path);
        } catch (IOException e) {
            try {
                Path path = Paths.get(fileName);
                return Files.readAllLines(path, Charset.forName("Windows-1251"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static List<String> convertTextLinesToWordsList(List<String> lines) {
        List<String> words = new ArrayList<>();

        for (String line : lines) {
            String cleanedLine = line.replaceAll("[^a-zA-Zа-яА-ЯёЁ0-9\\s]", " ");
            String trimmed = cleanedLine.trim();
            String[] splitWords = trimmed.split("\\s+");
            for (String word : splitWords) {
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
        }

        return words;
    }

    public static Map<String, Integer> convertWordsListToMapWithWordsCount(List<String> words) {
        Map<String, Integer> wordsWithCount = new HashMap<>();
        for (String word : words) {
            if (wordsWithCount.containsKey(word)) {
                wordsWithCount.put(word, wordsWithCount.get(word) + 1);
            } else {
                wordsWithCount.put(word, 1);
            }
            counter++;
        }
        return wordsWithCount;
    }

    public static void createCsvFile(Map<String, Integer> wordCountMap, int totalWords, String outputFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.println("Слово, Частота, Частота (%)");

            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                String word = entry.getKey();
                int frequency = entry.getValue();
                double percentage = (frequency * 100.0) / totalWords;

                writer.printf("%s, %d, %.2f%%%n", word, frequency, percentage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
