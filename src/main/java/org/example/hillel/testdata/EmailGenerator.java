package org.example.hillel.testdata;

import java.util.Random;

public class EmailGenerator {

    public static String generateRandomEmail() {
        String emailPattern = "Autotest%s@mailinator.com";
        Random random = new Random();
        int randomNumber = random.nextInt(100000);
        return String.format(emailPattern, randomNumber);
    }
}
