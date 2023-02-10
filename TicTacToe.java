/**  
 * The following contains detailed information on a tictactoe game
 * @author Santeri Seppala
 */

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    /**
     * Some public variables used while making the game
     */
    public static int height = 0;
    public static int length = 0;
    public static int row = 0;
    public static int column = 0;

    /**
     * Prints the board
     * @param board the future gameboard
     */
    public static void printBoard(char[][] board) {
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

    }
    
    /**
     * Fills the soon to be printed board with '-' and makes the structure using that symbol
     * @param board the future gameboard
     */
    public static void fillBoard(char[][] board) {
        //Makes the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '-';
            }
        }
    }

    /**
     * Asks the player if he/she is ready to play the game and asks confirmation 
     */
    public static void ready() {
        Scanner input = new Scanner(System.in);
        System.out.println(
                "You are playing against the computer. Are you READY?! Score 3 in a row to WIN!!(type 'ready' when you want to start, anything else will quit the game). ");
        String userInput = input.nextLine();
        if (userInput.equals("ready")) {
            System.out.println("Good luck!");
        } else {
            System.out.println("Game over!");
            System.exit(0);
        }

    }

    /**
     * Randomly gives a number on a specific range
     * @param range while using the board measurements
     * @return a random integer
     */
    public static int randomInt(int range) {
        Random rand = new Random();
            return rand.nextInt(range);
            
    }
    /** Getting the enemy to insert a symbol to an empty space
     * on the board
     * @param gameBoard the board used in the current game
      */
    public static void enemyAi(char[][] gameBoard) {
        int X = randomInt(height);
        int Y = randomInt(length);
        
        while (gameBoard[X][Y] != '-') {
             X = randomInt(height);
             Y = randomInt(length);
        }
        gameBoard[X][Y] = 'o';
        
        
    }

    /**
     * This method checks for the winner of the current game
     * by each row and column seperately and also diagonally 
     * @param gameBoard the board used in the current game
     */
    public static void hasWon(char[][] gameBoard) {
        /**
         * Checking the rows for 3 back to back symbols 
         */
        int inARow = 0;
        char streak = '-';
        boolean win = false;
        for (int i = 0; i < gameBoard.length; i++) {
            inARow = 0;
            if (win) {
            break;
            }
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == '-') {
                    inARow = 0;
                    continue;
                } else {
                    if (gameBoard[i][j] == streak) {
                        inARow++;
                        if (inARow == 3) {
                            win = true;
                            break;
                        }
                    } else {
                        inARow = 1;
                        streak = gameBoard[i][j];
                    }
                
                }

            }

        }
        /**
         * Checking the columns for back to back symbols.
         */
        for (int i = 0; i < gameBoard[0].length; i++) {
            inARow = 0;
            if (win) {
            break;
            }
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[j][i] == '-') {
                    inARow = 0;
                    continue;
                } else {
                    if (gameBoard[j][i] == streak) {
                        inARow++;
                        if (inARow == 3) {
                            win = true;
                            break;
                        }
                    } else {
                        inARow = 1;
                        streak = gameBoard[j][i];
                    }
                
                }

            }

        }
        

        /** 
         * Top left diagonal check for back to back symbols
         */
        for(int i = 0; i < height+length-1; i++ ) {
            inARow = 0;
            if (win) {
            break;
            }
            int x = Math.max(0,i-height-1);
            int y = Math.max(0,height-i-1);
            for(int j = 0; j < Math.min(Math.min(i+1,length),Math.min(height+length-i, height)); j++) {
                if (gameBoard[y+j][x+j] == '-') {
                    inARow = 0;
                    continue;
                } else {
                    if (gameBoard[y+j][x+j]  == streak) {
                        inARow++;
                        if (inARow == 3) {
                            win = true;
                            break;
                        }
                    } else {
                        inARow = 1;
                        streak = gameBoard[y+j][x+j];
                    }
                
                }

            }
        }
        

        /**
         * Top right diagonal check for back to back symbols
         */
        for(int i = 0; i < height+length-1; i++ ) {
            inARow = 0;
            if (win) {
            break;
            }
            int x = Math.max(Math.min(length-1,height+length-i-2),0);
            int y = Math.max(height-1-i,0);
            for(int j = 0; j < Math.min(Math.min(i+1,length),Math.min(height+length-i-1, height)); j++) {
                
                if (gameBoard[y+j][x-j] == '-') {
                    inARow = 0;
                    continue;
                } else {
                    if (gameBoard[y+j][x-j]  == streak) {
                        inARow++;
                        if (inARow == 3) {
                            win = true;
                            break;
                        }
                    } else {
                        inARow = 1;
                        streak = gameBoard[y+j][x-j];
                    }
                
                }

            }
        }
        
        /**
         * This method checks if the condition achieved, congratulates and ends the game
         */
        if (win) {
            System.out.println("Congratulations! You have won!!");
            System.exit(0);
        } 
        

    }

    
    /**
     * Main method contains things like asking the players name
     * and deciding the size of the gameboard
     * also whose turn it is and if the player move is correctly set in the gameboard 
     * Methods used in the main method are listed above the main method
     * @param args command line parameter, not used.
     */
    public static void main(String[] args) {
        
        /**
         * Implementing scanner to read user inputs
         */
        Scanner input = new Scanner(System.in);

        /**
         * Asking the users name.
         */
        System.out.println("Player 1, whats's your name? ");
        String p1 = input.nextLine();
        /**
         * Getting ready, (see method above)
        */
        ready();
        /**
         * Generating the board by asking the user for specifics
         */
        System.out.print("Enter gameboard height:");

        height = input.nextInt();

        System.out.print("Enter gameboard length:");

        length = input.nextInt();

        char[][] gameBoard = new char[height][length];

        /** Makes the board.
         */
        fillBoard(gameBoard);
        /**Prints the board.
         */
        printBoard(gameBoard);

        /**
         * Keep track of whose turn it is.
         */
        boolean isPlayer1 = true;

        while (true) {
            /**
             * Keeping track of symbols.
             */
            char symbol = ' ';
            if (isPlayer1) {
                symbol = 'x';
            } else {
                symbol = 'o';
            }

            /**
             * Keeping track of symbols.
             */
            if (isPlayer1) {
                System.out.println(p1 + "'s turn (x)!");
            } else {
                System.out.println("Computer is up next (o)!");
            }
            row = 0;
            column = 0;

            while (true) {
                /**
                 * Get row and column from Player 1.
                 */
                System.out.println("Enter a row: (first row is 0, second row is 1 etc..) ");
                row = input.nextInt();

                System.out.println("Enter a column: (first column is 0, second column is 1 etc..");
                column = input.nextInt();

                /**
                 * Checking user input.
                 */
                if (row < 0 || column < 0 || row > height - 1 || column > length - 1) {
                    // Out of bounds.
                    System.out.println("Out of bounds! Try again..");
                } else if (gameBoard[row][column] != '-') {
                    // Desired place is already taken.
                    System.out.println("Place already taken!");
                } else {
                    break;
                }

            }
            /**
             * Getting player symbol to show up on the gameboard
             */
            gameBoard[row][column] = symbol;
            printBoard(gameBoard);
            //Checking the winner, (see method above)
            hasWon(gameBoard);
            //Advising the player when the enemy makes a move
            System.out.println("Computer is up next (o)!");
            //Enemy intelligence, (see method above)
            enemyAi(gameBoard);

            
            printBoard(gameBoard);

            hasWon(gameBoard);

            
            
        }

    }

}
