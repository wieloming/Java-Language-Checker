import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.*;

public class LanguageEngine {
    private String text;
    private ArrayList<String> words = new ArrayList<String>();
    private Map<String, Double> english = new HashMap<String, Double>();
    private Map<String, Double> spanish = new HashMap<String, Double>();

    public LanguageEngine(String nText) {
        text = nText;
        createLanguageMaps();
        splitTextIntoWordsArr();
    }

    private void splitTextIntoWordsArr() {
        words.addAll(Arrays.asList(text.split(" ")));
    }

    public String dictionaryTranslate() {
        ArrayList<String> englishDictionary = new ArrayList<String>();
        ArrayList<String> spanishDictionary = new ArrayList<String>();
        int englishWords = 0;
        int spanishWords = 0;
        try {

            Scanner englishScanner = new Scanner(new File("src/english.csv"));
            while (englishScanner.hasNext()) {
                englishDictionary.add(englishScanner.next());
            }

            Scanner spanishScanner = new Scanner(new File("src/spanish.txt"));
            while (spanishScanner.hasNext()) {
                String spanishWord = spanishScanner.next().split(" ")[0];
                spanishDictionary.add(spanishWord);
            }
            for (String word : words) {


                for (String englishWord : englishDictionary) {

                    if (englishWord.equalsIgnoreCase(word)) {
                        englishWords++;
                    }
                }
                for (String spanishWord : spanishDictionary) {
                    if (spanishWord.equalsIgnoreCase(word)) {
                        spanishWords++;
                    }
                }
            }

        } catch (FileNotFoundException ups) {
            ups.printStackTrace();
        }

        int sumResult = englishWords + spanishWords;
        if (sumResult == 0) {
            sumResult = 1;
        }
        System.out.println(englishWords);
        System.out.println(spanishWords);
        double englishResultPerc = englishWords * 100 / sumResult;
        double spanishResultPerc = spanishWords * 100 / sumResult;
        DecimalFormat df = new DecimalFormat("#.00");

        return "English probability " + df.format(englishResultPerc) + "%" + "                 " + "Spanish probability " + df.format(spanishResultPerc) + "%";
    }

    public String translateByLetterFreq() {
        Map<String, Double> map = textToFreqArray(text);

        double englishDistance = 0;
        double spanishDistance = 0;

        for (Map.Entry<String, Double> pair : map.entrySet()) {

            for (Map.Entry<String, Double> englishPair : english.entrySet()) {
                if (englishPair.getKey().equals(pair.getKey())) {
                    double distance = Math.abs(englishPair.getValue() - pair.getValue());
                    englishDistance += distance;
                }
            }
            for (Map.Entry<String, Double> spanishPair : spanish.entrySet()) {
                if (spanishPair.getKey().equals(pair.getKey())) {
                    double distance = Math.abs(spanishPair.getValue() - pair.getValue());
                    spanishDistance += distance;
                }
            }

        }
        double addedDistance = englishDistance + spanishDistance;

        double englishDistancePerc = englishDistance * 100 / addedDistance;
        double spanishDistancePerc = spanishDistance * 100 / addedDistance;
        DecimalFormat df = new DecimalFormat("#.00");

        return "English probability " + df.format(spanishDistancePerc) + "%" + "                 " + "Spanish probability " + df.format(englishDistancePerc) + "%";
    }

    private Map<String, Double> textToFreqArray(String text) {
        char[] textArray = text.toCharArray();
        Map<String, Double> map = new HashMap<String, Double>();
        double textLength = text.length();
        for (char letter : textArray) {
            int letterFrequency = 0;
            for (char ltr : textArray) {
                if (ltr == letter) {
                    letterFrequency++;
                }
            }

            double freqInPercent = letterFrequency / textLength * 100;
            map.put("" + letter, freqInPercent);
        }
        return map;
    }

    private void createLanguageMaps() {
        english = new HashMap<String, Double>();
        english.put("e", 12.60);
        english.put("a", 8.34);
        english.put("o", 7.70);
        english.put("n", 6.80);

        spanish = new HashMap<String, Double>();
        spanish.put("e", 13.72);
        spanish.put("a", 11.72);
        spanish.put("o", 8.44);
        spanish.put("n", 6.83);


    }
}
