import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Wordle {

    public static void main(String[] args) throws IOException {

        //First get the data from the file using a reader
        Scanner scanner = new Scanner(System.in);

        File file = new File("dict.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String[] words = new String[2317];

        //Then put the words in a String array
        for (int i = 0; i < words.length; i++) {
            words[i] = bufferedReader.readLine();
        }
        bufferedReader.close();

        //To generate a random number use the Random class
        Random random = new Random();
        String answer = words[random.nextInt(2316)];

        //This will be the answer amount
        int wordNumber = 0;
        boolean isFoundAnswer = false;

        //This code only runs for 6 times for 6 inputs
        for (int o = 0; o < 6; o++) {
            //Getting input from the user
            System.out.println("Please enter word " + (wordNumber + 1));
            String word = scanner.nextLine();
            word = word.toUpperCase();

            //Making sure that the input is of length 5, if it is not the user will lose one turn
            if (word.length() != 5) {
                System.out.println("The length of the word must be five! you lost this turn");
                wordNumber++;
                continue;
            }

            //Checking if the user has found the answer, if the answer has been found it will break out from the loop and finish the code
            if (word.equals(answer)) {
                if (wordNumber + 1 == 1) {
                    System.out.println("Congratulations! you guessed the word right in the 1st shot");
                } else if (wordNumber + 1 == 2) {
                    System.out.println("Congratulations! you guessed the word right in the 2nd shot");
                } else if (wordNumber + 1 == 3) {
                    System.out.println("Congratulations! you guessed the word right in the 3rd shot");
                } else {
                    System.out.println("Congratulations! you guessed the word right in the " + (wordNumber + 1) + "th shot");
                }
                isFoundAnswer = true;
                break;
            }

            //Sending the input and the answer to the compareLetters method to check their letters one by one
            //This will run if and only if the word is in the dictionary according to the quiz pdf
            compareLetters(word, answer);

            //Incrementing the answer amount
            wordNumber++;
        }

        scanner.close();

        if (!isFoundAnswer) {
            System.out.println();
            System.out.println("You don't have anymore guesses!");
            System.out.println("The answer was '" + answer + "' best of luck on your next try");
        }

    }

    public static void compareLetters(String guess, String answer) {
        boolean check = true;

        //First loop gets one of the letters from the guessed input
        //Second loop compares the letter with the answer letters

        for (int i = 0; i < guess.length(); i++) {
            for (int j = 0; j < answer.length(); j++) {
                if (i == j && guess.charAt(i) == answer.charAt(j) || guess.charAt(i) == answer.charAt(i)) {
                    System.out.println("The letter " + guess.charAt(i) + " is in the correct position");
                    check = false;
                    break;
                } else if (guess.charAt(i) == answer.charAt(j)) {
                    System.out.println("The answer contains the letter " + guess.charAt(i) + " but it is not in the correct position");
                    check = false;
                    break;
                }
                check = true;
            }
            if (check) {
                System.out.println("The answer does not contain the letter " + guess.charAt(i));
            }
        }
        System.out.println();
    }
}