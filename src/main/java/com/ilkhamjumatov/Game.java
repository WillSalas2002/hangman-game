package com.ilkhamjumatov;
import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    public static int chances;
    public static int winCount;
    private int round = 1;


    public void run() {
        gameLoop();
    }

    public void gameLoop() {

        System.out.println("Welcome! This is a hangman game.");

        boolean continueGame = false;

        System.out.print("If you want to play, please enter (1) otherwise enter any non-empty string: ");
        String continueGameStr = scanner.next();

        if (continueGameStr.equals("1")) {
            continueGame = true;
        }

        while (continueGame) {

            Word word = new Word();
            UserInputHandler userInputHandler = new UserInputHandler(word);
            chances = 6;

            System.out.println("========================");
            System.out.println("       Round #" + round);
            System.out.println("========================");

            do {
                word.displayWord();
                char guess = userInputHandler.getUserInput();
                if (!userInputHandler.isValid(guess)) {
                    continue;
                }
                userInputHandler.checkUserInput(guess);
                System.out.println("Chances left: " + chances);

            } while (!word.isWordComplete() && (chances > 0));
            System.out.println("The word is: " + word.getRealWord());

            System.out.println();
            System.out.println("========================================");
            System.out.println();

            System.out.print("If you want to continue playing, please enter (1) otherwise enter any non-empty string: ");
            continueGameStr = scanner.next();

            if (!continueGameStr.equals("1")) {
                getStatistics();
                continueGame = false;
            }

            round++;
        }

        scanner.close();
    }

    private void getStatistics() {
        System.out.println();
        System.out.println("--------Your Statistics--------");
        System.out.printf("""
                Overall you have played %d rounds.
                You WON %d times and LOST %d times.
                Thank you for playing.""", round, winCount, (round -winCount));
    }






}