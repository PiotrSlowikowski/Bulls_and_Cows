package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BullsAndCowsInit();
    }

    private static void BullsAndCowsInit() {
        int secretCode = 9305;
        checkCowsAndBulls(secretCode);
    }

    private static void checkCowsAndBulls(int secretCode) {
        Scanner scanner = new Scanner(System.in);
        String codeString = Integer.toString(secretCode);
        String inputString = scanner.nextLine();
        int bullsCounter = 0;
        int cowsCounter = 0;

        for (int i = 0; i < codeString.length(); i++) {
            for (int j = 0; j < codeString.length(); j++) {
                if (inputString.charAt(i) == codeString.charAt(j) && i == j) {
                    bullsCounter++;
                } else if (inputString.charAt(i) == codeString.charAt(j)) {
                    cowsCounter++;
                }
            }
        }

        if (cowsCounter > 0 && bullsCounter == 0) {
            System.out.format("Grade: %s cow(s). The secret code is " + secretCode + ".", cowsCounter);
        } else if (cowsCounter == 0 && bullsCounter > 0) {
            System.out.format("Grade: %s bulls(s). The secret code is " + secretCode + ".", bullsCounter);
        } else if (cowsCounter > 0 && bullsCounter > 0) {
            System.out.format("Grade: %s bull(s) and %s cow(s). The secret code is " + secretCode + ".", bullsCounter, cowsCounter);
        } else {
            System.out.format("Grade: None. The secret code is " + secretCode + ".");
        }
    }

    private static int createSecretCode() {
        Random random = new Random();
        String codeString = "";
        for (int i = 0; i < 4; i++) {
            String digit = Integer.toString(random.nextInt(10));
            codeString += digit;
        }
        int secretNumber = Integer.parseInt(codeString);
        return secretNumber;
    }

}
