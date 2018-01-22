package sudokusolver;

import java.io.*;
import java.util.Scanner;

/**
 * @author Aiden Osipenko
 * @description This class handles file input for the program.
 */
public class SudokuFileHandler {

    /**
     * Method that loads data from input file into a String and removes
     * whitespace.
     *
     * @return String containing data from input.
     * @throws IOException
     */
    public static int[][] loadBoard() throws IOException {
        int[][] in = null;
        String filePath = "";
        Scanner scanner = new Scanner(System.in);
        FileReader fReader = null;
        BufferedReader bReader = null;
        //Get file name from user
        try {
            System.out.print("Please enter file path: ");
            filePath = scanner.nextLine();
        } catch (Exception e) {
            throw new IOException("Error: Invalid file name.");
        }
        try { //Load file
            fReader = new FileReader(filePath);
            bReader = new BufferedReader(fReader);
        } catch (FileNotFoundException e) {
            throw new IOException("Error: File not found.");
        }
        //Read line and store in String[]
        String[] s;
        s = bReader.readLine().split("\\s");
        in = new int[s.length][s.length];
        //Loop through 2d String array to fill
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s.length; j++) {
                in[i][j] = Integer.parseInt(s[j]);
                if (in[i][j] > 9) {
                    throw new IOException("Error: Invalid input.");
                }
            }
            if (i < 8) {
                //Get new line when done filling previous line into array
                s = bReader.readLine().split("\\s");
            }
        }
        fReader.close();
        bReader.close();
        return in;
    }
}
