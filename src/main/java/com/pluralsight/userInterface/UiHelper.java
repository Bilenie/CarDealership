package com.pluralsight.userInterface;

import java.util.Scanner;

public class UiHelper {
    // ... your other methods (displayMenu, handleUserInput, etc.)

    // gives the user a visual spinner for loading. Useful and looks professional.
    public static void showLoadingSpinner(int durationMillis) {
        char[] spinner = {'|', '/', '-', '\\'};
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < durationMillis) {
            for (char ch : spinner) {
                System.out.print("\rLoading " + ch);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    System.out.println("\nLoading interrupted.");
                    return;
                }
            }
        }

        System.out.print("\rLoading done!         \n");
    }

    //Method waits until user presses Enter gives control to user.
    public static void waitForEnter() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    //Method to assist with making the first letter of our string to capital to avoid error
    public static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static void showLoadingDots(int durationMillis) {
        int dotCount = 0;
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < durationMillis) {
            String dots = ".".repeat(dotCount % 4);
            System.out.print("\rLoading" + dots + "   ");
            dotCount++;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("\nLoading interrupted.");
                return;
            }
        }

        System.out.print("\rLoading complete!      \n");
    }

   // Waits for a specific time in milliseconds (like 2000 ms = 2 seconds).Then continues automatically.
    public static void pauseBeforeContinuing(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Pause interrupted.");
        }
    }


}
