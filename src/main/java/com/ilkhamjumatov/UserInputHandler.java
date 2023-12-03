package com.ilkhamjumatov;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UserInputHandler {

    private final Scanner scanner;
    private final Set<Character> userGuesses;
    private final Word word;

    public UserInputHandler(Word word) {
        scanner = new Scanner(System.in);
        userGuesses = new HashSet<>();
        this.word = word;
    }

    public char getUserInput() {
        System.out.println("List of letters entered: " + userGuesses);
        System.out.print("Enter your guess: ");
        return scanner.next().toLowerCase().charAt(0);
    }

    public boolean isValid(char guess) {

        if (guess >= 'a' && guess <= 'z') {
            return true;
        } else {
            System.out.println("Invalid input, please enter characters only.");
        }
        return false;
    }

    public void checkUserInput(char guess) {


        if (word.getRealWord().contains(String.valueOf(guess))) {

            if (userGuesses.contains(guess)) {
                Game.chances--;
            }

            userGuesses.add(guess);
            word.openALetter(guess);

            if (word.isWordComplete()) {
                Game.winCount++;
                System.out.println("You Won!");
            }

        }
        else {

            if (!userGuesses.contains(guess)) {
                Game.chances--;
            }
            userGuesses.add(guess);

            if (Game.chances == 0) {
                System.out.println("You lost the game.");
            }
        }
    }
}
