/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t100;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mark
 */
public class GameBoard {

    int size = 3;
    Board board;

    Board player1board;
    Board player2board;

    Move tempMoveHolder;
    Board tempBoardHolder;
    String reasonHolder = "";

    int move;// = 1; // we start with the first move, so not zero

    ArrayList<Player> players = new ArrayList();

    Random random = new Random();

    int computer1Identifier = 0;
    int computer2Identifier = 1;
    
    int numOfGames = 10;

    boolean playerOneToMove = true;
    boolean gameOver = false;

    // I didn't want to use only one variable like I did with the playerOneToMove, 
    // I want a win boolean to be triggered by an event, not by default instantiation
    boolean player1win = false;
    boolean player2win = false;

    public GameBoard() {

        createPlayers(2);

        //board.printMoves();
        for (int i = 1; i < numOfGames+1; i++) {
            gameOver = false;
            player1win = false;
            player2win = false;
            System.out.println("\n\ninitializing game board #" + i);
            initializeGameBoard();
            System.out.println("starting game #" + i);
            playGame();
        }

    }// end constructor

    public void createPlayers(int numberOfPlayers) {

        for (int i = 0; i < numberOfPlayers; i++) {
            // should this i + 1 calculation be moved to the player class?
            players.add(new Player(i + 1));//  players.add(new Player(i+1));
        }

    }

    private void playGame() {

        for (int i = 0; i < size * size + 2; i++) {

            executeMove();
            if (gameOver) {
                break;
            }

        }

    }

    private void executeMove() {

       // System.out.println("\n\nmove #" + move);
        if (isOpenSpace()) {

            if (playerOneToMove) {
                computer1SelectMove();
               // printBoard();
                playerOneToMove = false;
                if (isWinCondition()) {

                    gameOver = true;
                    player1win = true;
                    System.out.println("Player one wins.");
                    gameOver();
                }
            } else {
                computer2SelectMove();
               // printBoard();
                playerOneToMove = true;
                if (isWinCondition()) {
                    gameOver = true;
                    player2win = true;
                    System.out.println("Player two wins.");
                    gameOver();
                }
            }

        } else {
            gameOver = true;
            reasonHolder = "No available move.";
            gameOver();
            //break;
        }

        move++;

    }

    public void computer1SelectMove() {

        int column = random.nextInt(size);
        int row = random.nextInt(size);

        if (board.grid[column][row] == 0) {//  this doesn't work! -->  if(board.grid[column][row]!=null){
            tempBoardHolder = board;
            board.grid[column][row] = players.get(computer1Identifier).identifier;
            tempMoveHolder = new Move(row, column);
            board.removeFromAvailableMoves(tempMoveHolder);
            // board.printMoves();
        } else {
            computer1SelectMove();
        }

    }  // end computer select move

    public void computer2SelectMove() {

        int column = random.nextInt(size);
        int row = random.nextInt(size);

        if (board.grid[column][row] == 0) {//  this doesn't work! -->  if(board.grid[column][row]!=null){
            tempBoardHolder = board;
            board.grid[column][row] = players.get(computer2Identifier).identifier;
            tempMoveHolder = new Move(row, column);
            board.removeFromAvailableMoves(tempMoveHolder);
            //  board.printMoves();
        } else {
            computer2SelectMove();
        }

    }  // end computer select move 

    private void initializeGameBoard() {

        move = 1;
        board = new Board(size);
        player1board = board;
        player2board = board;
        tempMoveHolder = new Move(0, 0); // just setting to zero to initialize, will be overwritten

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board.grid[i][j] = 0;
                board.addToAvailableMoves(new Move(i, j));
            }
        }

    }

    public void printBoard() {

        System.out.print("\n");

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board.grid[i][j]);
                System.out.print("\t");
            }
            System.out.print("\n");
        }
        board.printMoves();
    }

    private boolean isOpenSpace() {

        boolean temp = false;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.grid[i][j] == 0) {
                    temp = true;
                    break;
                }

            }
        }

        return temp;

    } // end is open space

    private boolean isWinCondition() {

        boolean temp = false;

        if (isHorizontalWin()) {
            temp = true;
        }
        if (isVerticalWin()) {
            temp = true;
        }
        if (isDiagonalWin()) {
            temp = true;
        }

        return temp;

    }

    private boolean isHorizontalWin() {

        boolean temp = false;
        int numberInRow = 1;

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (board.grid[i][j] != 0 && board.grid[i][j] == board.grid[i][j + 1]) {
                    numberInRow++;
                }
            } // end for j 

            if (numberInRow >= size) { //>= doesn't make logical sense, but it's a security against errors that might occur, which we aren't aware of yet
                temp = true;
                reasonHolder = "horizontal win.";
                break;
            } else {
                numberInRow = 1;
            }

        } // end for i

        return temp;
    } // end is horizontal win

    private boolean isVerticalWin() {

        boolean temp = false;
        int numberInRow = 1;

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (board.grid[i][j] != 0 && board.grid[i][j] == board.grid[i + 1][j]) {
                    numberInRow++;
                }
            } // end for j 

            if (numberInRow >= size) { //>= doesn't make logical sense, but it's a security against errors that might occur, which we aren't aware of yet
                temp = true;
                reasonHolder = "vertical win.";
                break;
            } else {
                numberInRow = 1;
            }

        } // end for i

        return temp;
    } // end is vertical win  

    private boolean isDiagonalWin() {

        boolean temp = false;

        if (isSouthEast()) {
            temp = true;
        }
        if (isNorthEast()) {
            temp = true;
        }

        return temp;
    } // end is diagonal win    

    private boolean isSouthEast() {

        boolean temp = false;
        int numberInRow = 1;

        for (int j = 0; j < size - 1; j++) {
            if (board.grid[0][0] != 0 && board.grid[j][j] == board.grid[0 + j + 1][0 + j + 1]) {
                numberInRow++;
            }

        }
        if (numberInRow >= size) { //>= doesn't make logical sense, but it's a security against errors that might occur, which we aren't aware of yet
            temp = true;
            reasonHolder = "southwest diagonal win.";

        }
        return temp;

    }

    private boolean isNorthEast() {

        boolean temp = false;
        int numberInRow = 1;
        int length = size - 1;

        for (int j = 0; j < length; j++) {
            if (board.grid[length][0] != 0 && board.grid[length - j][j] == board.grid[length - j - 1][j + 1]) {
                numberInRow++;
            }

        }

        if (numberInRow >= size) { //>= doesn't make logical sense, but it's a security against errors that might occur, which we aren't aware of yet
            temp = true;
            reasonHolder = "northeast diagonal win.";

        }

        return temp;

    }

    private void gameOver() {

        if (player1win) {
           // System.out.println("yay player 1!!!");
            //  add the grid to the arraylist
            if(players.get(1).isNewLosingCombination(tempBoardHolder)){
                players.get(1).addGridToMemory(tempBoardHolder); 
                       
            // in theory :) the moveHolder is the bad move, so we remove that from the list of available moves
          //  players.get(1).losingCombinations.get(players.get(1).losingCombinations.size() - 1).removeFromAvailableMoves(tempMoveHolder);
            }
    
        }

        if (player2win) {
          //  System.out.println("yay player 2!!!");
            //  add the grid to the arraylist
              if(players.get(0).isNewLosingCombination(tempBoardHolder)){
                players.get(0).addGridToMemory(tempBoardHolder); 
                            // in theory :) the moveHolder is the bad move, so we remove that from the list of available moves
           // players.get(0).losingCombinations.get(players.get(0).losingCombinations.size() - 1).removeFromAvailableMoves(tempMoveHolder);
            }

        }

        System.out.println("game over: " + reasonHolder);
        System.out.println("player 1 memory size : " + players.get(0).losingCombinations.size() + ", player 2 memory size : " + players.get(1).losingCombinations.size());

    }

}// end class
