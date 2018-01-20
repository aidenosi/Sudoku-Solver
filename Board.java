package sudokusolver;

public class Board {

    int[][] boardArray;

    /**
     * Constructor.
     *
     * @param array Array containing integer values from input data.
     */
    public Board(int[][] array) {
        boardArray = new int[9][9];
        System.arraycopy(array, 0, boardArray, 0, array.length);
    }

    /**
     * Checks if a location is open.
     *
     * @param x X coordinate of location.
     * @param y Y coordinate of location.
     * @return Boolean indicating whether the location is empty.
     */
    public boolean isOpen(int x, int y) {
        return boardArray[x][y] == 0;
    }

    /**
     * Places a given number at the specified location in the array.
     *
     * @param n Number to place.
     * @param x X coordinate of location.
     * @param y Y coordinate of location.
     */
    public void placeNum(int n, int x, int y) {
        boardArray[x][y] = n;
    }

    /**
     * Clears a specified location in the board array.
     *
     * @param x X coordinate of location.
     * @param y Y coordinate of location.
     */
    public void clearLocation(int x, int y) {
        boardArray[x][y] = 0;
    }

    /**
     * Checks if a board is a valid starting board by ensuring that there are
     * not multiples of the same number in any row, column, or 3x3 square.
     *
     * @return Boolean indicating whether the board is a valid starting board.
     */
    public boolean isValidStartBoard() {
        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray[i].length; j++) {
                if (boardArray[i][j] != 0) {
                    if (!isValidPlacement(boardArray[i][j], i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Method that returns an array containing all valid numbers for a given
     * coordinate on the board.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @return Array containing all valid numbers.
     */
    public int[] getValidNums(int x, int y) {
        int size = 0;
        String nums = ""; //String to store all valid numbers
        //For loop to check all numbers (1-9) at the location to see if they 
        //are valid
        for (int i = 1; i <= boardArray[x].length; i++) {
            //If a number is valid, add it to the string 
            if (isValidPlacement(i, x, y)) {
                size++;
                nums += i;
            }
        }
        int[] valid = new int[size];
        //For loop to put each character from nums into the array
        for (int i = 0; i < size; i++) {
            valid[i] = Character.getNumericValue(nums.charAt(i));
        }
        return valid;
    }

    /**
     * Checks if a value at a coordinate is valid.
     *
     * @param value Value to check.
     * @param x X coordinate
     * @param y Y coordinate
     * @return Boolean indicating whether the value is valid at the location.
     */
    public boolean isValidPlacement(int value, int x, int y) {
        //Check all spaces in the row
        for (int i = 0; i < boardArray[x].length; i++) {
            //Check if a location already contains the value
            if (i != y) { //Don't check the desired location
                if (boardArray[x][i] == value) {
                    return false;
                }
            }
        }
        //Check all spaces in the column
        for (int i = 0; i < boardArray[0].length; i++) {
            //Check if a location already contains the value
            if (i != x) { //Don't check the desired location
                if (boardArray[i][y] == value) {
                    return false;
                }
            }
        }
        //Check 3x3 square
        int cornerX = 3 * (x / 3); //Using integer division to find x and y 
        int cornerY = 3 * (y / 3); //of top right corner of 3x3 square
        //Iterate over all spaces in the 3x3 square
        for (int i = cornerX; i < cornerX + 3; i++) {
            for (int j = cornerY; j < cornerY + 3; j++) {
                //Check if a location already contains the value
                if (i != x && j != y) { //Don't check the desired location
                    if (boardArray[i][j] == value) {
                        return false;
                    }
                }
            }
        }
        //Return true if none of our checks were triggered, since value is valid
        return true;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray[i].length; j++) {
                s += boardArray[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }
}
