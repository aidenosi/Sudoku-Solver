package sudokusolver;

import java.io.*;
import java.util.*;

/**
 * @author Aiden Osipenko
 * @description This class is the main driver the program and contains the
 * solve() method which executes the backtracking algorithm used to solve a
 * board. 
 */
public class SudokuSolver {

    Scanner scanner;

    public SudokuSolver() {
        int[][] arr = null; //Array to represent the input
        Board board = null; //Board object
        boolean loop = true;
        while (loop) {
            try { //Get data from file
                arr = SudokuFileHandler.loadBoard();
            } catch (IOException e) {
                System.out.println(e);
                continue;
            }
            board = new Board(arr); //Create board object
            //Check that it is a valid starting board
            if (board.isValidStartBoard()) {
                //Test board to check if there is no solution
                Board test = new Board(board.boardArray);
                solve(board); //Solve board
                //Check if board could not be solved
                if (Arrays.equals(board.boardArray, test.boardArray)) {
                    System.out.println("Error: No solution.");
                    continue;
                } else {
                    //Print original board
                    System.out.println("Board from input:\n" + board);
                    //Print finished board
                    System.out.println("Solved board:\n" + board);
                }
            } else {
                System.out.println("Error: Invalid input.");
                continue;
            }
            scanner = new Scanner(System.in);
            char choice;
            while (true) { //Asking user if they want to solve another board
                System.out.print("Would you like to solve another board? ");
                //Only take first character of entered string and put to upper 
                //case(so user could write "No", "yes", "Yes", "y", etc)
                choice = Character.toUpperCase(scanner.next().charAt(0));
                //Ensure that user has entered either Y or N
                if (choice != 'Y' && choice != 'N') {
                    System.out.println("Error: Invalid selection.");
                } else {
                    break;
                }
            }
            //Only have to check if choice is 'Y' since it's already ensured 
            //that choice is either 'Y' or 'N'
            loop = (choice == 'Y');
        }
        scanner.close();
    }

    /**
     * Recursive backtracking method to solve Sudoku board.
     *
     * @param board Board to be solved.
     * @return
     */
    private boolean solve(Board board) {
        int locX = -1; //Location of open element
        int locY = -1;
        //Iterate over the board to find an open element
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.isOpen(i, j)) { //If this location is empty
                    locX = i; //Set location of open element
                    locY = j;
                    break;
                }
            }
        }
        int[] valid = null;
        //If an open element was not found
        if (locX == -1 && locY == locX) {
            return true;
        } else { //Otherwise, get valid numbers
            valid = board.getValidNums(locX, locY);
            if (valid.length == 0) { //If there are none
                return false;
            }
        }
        //For each valid number
        for (int i : valid) {
            //Place the number in the open location
            board.placeNum(i, locX, locY);
            if (solve(board)) { //Recurisve call - check if board is solved
                return true;
            }
            board.clearLocation(locX, locY);
        }
        return false;
    }

    public static void main(String[] args) {
        SudokuSolver s = new SudokuSolver();
    }

}
