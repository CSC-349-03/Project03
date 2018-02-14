package com.csc349.project3;

import java.io.*;
import java.util.*;

/**
 * Created by Andrew LeDawson on 2/12/2018.
 */
public class GameProblem {
    private enum Move {RIGHT, DOWN}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the data file name");
        String fname = scanner.next();
        File file = new File(fname);
        Game game;
        try {
            FileReader inputFile = new FileReader(file);
            BufferedReader lineBuffer = new BufferedReader(inputFile);
            game = scanFile(lineBuffer);
            game(game.rows, game.cols, game.A);
            //printA(game);
        } catch (FileNotFoundException fileError) {
            System.out.println("ERROR: File not found!");
        } catch (IllegalArgumentException argError) {
            System.out.println("ERROR: Could not process game matrix!");
        } catch (IOException readError) {
            System.out.println("ERROR: Could not read file!");
        } catch (InputMismatchException readError) {
            System.out.println("ERROR: Could not parse file!");
        } catch (NoSuchElementException readError) {
            System.out.println("ERROR: Could not parse file!");
        }
    }

    /* Scans the input file into the Game */
    private static Game scanFile(BufferedReader lineBuffer) throws IOException {
        Scanner scanner = new Scanner(lineBuffer.readLine()).useDelimiter(" ");
        int rows_len = scanner.nextInt();
        int cols_len = scanner.nextInt();
        Game game = new Game();
        game.A = new int[rows_len][cols_len];
        game.rows = rows_len;
        game.cols = cols_len;
        for (int i = 0; i < rows_len; i++) {
            Scanner scanner2 = new Scanner(lineBuffer.readLine()).useDelimiter(" ");
            for (int j = 0; j < cols_len; j++) {
                game.A[i][j] = scanner2.nextInt();
            }
        }
        return game;
    }

    public static void game(int n, int m, int[][] A){
        int[][] scores = new int[n][m];
        Move[][] moves = new Move[n][m];
        int highestScoreX = -1;
        int highestScoreY = -1;

        // Start scanning at bottom row and right column, iterate
        for(int column = n - 1, row = m - 1; column >= 0 && row >= 0; column--, row--){
            // Iterate through the row
            for(int xInRow = column; xInRow >= 0; xInRow--){
                Move bestMove = findBestMove(n, m, xInRow, row, A, scores);
                moves[xInRow][row] = bestMove;
                if(bestMove == Move.DOWN){
                    if(xInRow == m - 1){ // No score to add up if at edge
                        scores[xInRow][row] = A[xInRow][row];
                    }else{ // Add up score from bottom
                        scores[xInRow][row] = A[xInRow][row] + scores[xInRow][row + 1];
                    }
                }else{ // Move right
                    // TODO: Copy from above block and modify
                }
            }
            // Iterate through the column
            for(int yInColumn = row; yInColumn >= 0; yInColumn--){
                Move bestMove = findBestMove(n, m, column, yInColumn, A, scores);
                moves[column][yInColumn] = bestMove;
                if(bestMove == Move.DOWN){
                    if(xInRow == m){
                        scores[xInRow][row] = A[xInRow][row];
                    }
                }
            }
            System.out.println("DEBUG: Finished finding best moves");
        }
    }

    private static Move findBestMove(int numRows, int numCols, int x, int y, int[][] grid, int[][] scores){
        if(y >= numRows){ // If no lower cells, must go right (including bottom rightmost cell)
            return Move.RIGHT;
        }
        if(x >= numCols){ // If no right cells, must go down
            return Move.DOWN;
        }
        if(scores[x+1][y] > scores[x][y+1]){ // Compare right and down move scores and return better score
            return Move.RIGHT;
        }else{
            return Move.DOWN;
        }
    }

    private static void printA(Game game) {
        for (int i = 0; i < game.rows; i++) {
            for (int j = 0; j < game.cols; j++) {
                System.out.print(game.A[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
