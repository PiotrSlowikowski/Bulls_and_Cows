package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BullsAndCowsInit();

    }

    private static void BullsAndCowsInit() {
        System.out.println("Please, enter the secret code's length:");
        Scanner scanner = new Scanner(System.in);
        int secretNumberCodeLength = scanner.nextInt();
        String secretCode = createSecretCode(secretNumberCodeLength);

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

    private static String createSecretCode(int secretNumberCodeLength) {

        Random random = new Random();


        String secretCode = "";

        while (secretCode.length() < secretNumberCodeLength) {
            if (secretNumberCodeLength > 10) {
                System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
                break;
            }
            String digit = Integer.toString(random.nextInt(10));
            if (secretCode.length() == 0 && digit.equals("0")) {
                continue;
            }
            if (!secretCode.contains(digit)) {
                secretCode += digit;
            }
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
