package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BullsAndCowsInit();

    }

    private static void BullsAndCowsInit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        int secretCodeLength = scanner.nextInt();
        System.out.println("Input the number of possible symbols in the code:");
        int codePossibleSymbolsCount = scanner.nextInt();

        String secretCode = createSecretCode(secretCodeLength, codePossibleSymbolsCount);

        if (!secretCode.isEmpty()) {
            roundRun(secretCode);
        }
    }

    private static void roundRun(String secretCode) {
        Scanner scanner = new Scanner(System.in);
        int turnCounter = 1;
        System.out.println("Okay, let's start a game! Secret code: " + secretCode);
        while (true) {
            System.out.println("Turn " + turnCounter + ":");
            String codeGuess = scanner.next();
            String outcome = checkCowsAndBulls(secretCode, codeGuess);
            System.out.println(outcome);
            if (outcome.contains("Congratulations")) {
                break;
            }
            turnCounter++;
        }
    }

    private static String createSecretCode(int secretNumberCodeLength, int codePossibleSymbolsCount) {


        Random random = new Random();
        String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz";
        String secretCode = "";

        while (secretCode.length() < secretNumberCodeLength) {
            if (secretNumberCodeLength > 36) {
                System.out.println("Error: can't generate a secret number with a length of >36 because there aren't enough unique digits.");
                break;
            }
            int randomIndex = random.nextInt(codePossibleSymbolsCount);
            String symbol = Character.toString(alphabet.charAt(randomIndex));
            if (!secretCode.contains(symbol)) {
                secretCode += symbol;
            }
        }

        String hashes = "";
        for (int i = 0; i < secretNumberCodeLength; i++) {
            hashes += "*";
        }


        if (codePossibleSymbolsCount <= 9) {
            System.out.println("The secret is prepared: " + hashes + " (0-" + alphabet.charAt(codePossibleSymbolsCount) + ").");
        } else if (codePossibleSymbolsCount == 10) {
            System.out.println("The secret is prepared: " + hashes + " (0-9).");
        } else {
            System.out.println("The secret is prepared: " + hashes + " (0-9, " + "a-" + alphabet.charAt(codePossibleSymbolsCount - 1) + ").");
        }
        return secretCode;
    }


    private static String checkCowsAndBulls(String secretCode, String codeGuess) {
        int bullsCounter = 0;
        int cowsCounter = 0;

        for (int i = 0; i < secretCode.length(); i++) {
            for (int j = 0; j < secretCode.length(); j++) {
                if (codeGuess.charAt(i) == secretCode.charAt(j) && i == j) {
                    bullsCounter++;
                    continue;
                } else if (codeGuess.charAt(i) == secretCode.charAt(j)) {
                    cowsCounter++;
                }
            }
        }
        return printScore(secretCode, bullsCounter, cowsCounter);

    }

    private static String printScore(String secretCode, int bullsCounter, int cowsCounter) {
        if (bullsCounter == secretCode.length()) {
            System.out.println(bullsCounter + " bulls");
            return "Congratulations! You guessed the secret code.";
        } else if (cowsCounter > 0 && bullsCounter == 0) {
            return "Grade: " + cowsCounter + " cow(s). The secret code is " + secretCode + ".";
        } else if (cowsCounter == 0 && bullsCounter > 0) {
            return "Grade: " + bullsCounter + " bulls(s). The secret code is " + secretCode + ".";
        } else if (cowsCounter > 0 && bullsCounter > 0) {
            return "Grade: " + bullsCounter + " bull(s) and " + cowsCounter + " cow(s). The secret code is " + secretCode + ".";
        } else {
            return "Grade: None. The secret code is " + secretCode + ".";
        }
    }


}
