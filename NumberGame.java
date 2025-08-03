import javax.swing.*;
import java.util.Random;

public class NumberGame {

    public static void main(String[] args) {
        Random random = new Random();
        int bestScore = Integer.MAX_VALUE;
        int totalWins = 0;

        JOptionPane.showMessageDialog(null, "Welcome to the Number Guessing Game!");

        boolean playAgain = true;

        while (playAgain) {
            String[] options = {"Easy (10)", "Medium (7)", "Hard (5)"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Choose a difficulty level:",
                    "Select Difficulty",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            int maxAttempts = switch (choice) {
                case 0 -> 10;
                case 1 -> 7;
                case 2 -> 5;
                default -> 7;
            };

            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;

            while (attempts < maxAttempts) {
                String input = JOptionPane.showInputDialog(
                        "Guess a number between 1 and 100\nAttempt " + (attempts + 1) + " of " + maxAttempts + ":");

                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Game cancelled.");
                    return;
                }

                int guess;
                try {
                    guess = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                    continue;
                }

                attempts++;

                if (guess == numberToGuess) {
                    JOptionPane.showMessageDialog(null, "Correct! You guessed it in " + attempts + " attempts.");
                    totalWins++;
                    if (attempts < bestScore) {
                        bestScore = attempts;
                    }
                    guessedCorrectly = true;
                    break;
                } else if (guess < numberToGuess) {
                    JOptionPane.showMessageDialog(null, "Too low!");
                } else {
                    JOptionPane.showMessageDialog(null, "Too high!");
                }
            }

            if (!guessedCorrectly) {
                JOptionPane.showMessageDialog(null, "Out of attempts! The number was: " + numberToGuess);
            }

            int replay = JOptionPane.showConfirmDialog(null,
                    "Do you want to play again?", "Play Again",
                    JOptionPane.YES_NO_OPTION);
            playAgain = (replay == JOptionPane.YES_OPTION);
        }

        String resultMessage = "Game Over!\nTotal Wins: " + totalWins;
        resultMessage += bestScore != Integer.MAX_VALUE
                ? "\nBest (Fewest) Attempts: " + bestScore
                : "\nNo correct guesses.";
        JOptionPane.showMessageDialog(null, resultMessage);
    }
}
