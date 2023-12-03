package com.ilkhamjumatov;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Word {

    private final char[] hiddenWord;
    private final String realWord;
    private final ArrayList<String> wordCollection;

    protected Word() {

        wordCollection = new ArrayList<>();
        fillCollectionWithWords();

        realWord = getRandomWord();
        System.out.println(realWord);
        hiddenWord = new char[realWord.length()];
    }

    private String getRandomWord() {
        Random random = new Random();

        return wordCollection.get(random.nextInt(wordCollection.size()));
    }

    private void fillCollectionWithWords() {

        File file = new File("src/main/resources/words.txt");

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {

                String[] words = line.split("[^a-zA-Z]");
                for (String word : words) {
                    wordCollection.add(word.toLowerCase());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Cannot open file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Cannot read file: " + file.getAbsolutePath());
        }

    }

    protected void displayWord() {

        StringBuilder sb = new StringBuilder();

        for (char c : hiddenWord) {

            if (c == '\u0000') {
                sb.append("_ ");
            } else {
                sb.append(c).append(" ");
            }
        }

        System.out.println(sb + "\n");
    }

    protected void openALetter(char guess) {

        for (int i = 0; i < realWord.length(); i++) {

            if (realWord.charAt(i) == guess) {
                hiddenWord[i] = guess;
            }
        }
    }

    protected String getRealWord() {
        return realWord;
    }

    protected boolean isWordComplete() {

        return String.valueOf(hiddenWord).equals(realWord);
    }
}
