/*  David Twyman, Andrew LeDawson
 **  dtwyman@calpoly.edu, aledawson@calpoly.edu
 **  CSC 349-03
 **  Project 3
 **  2-12-2018
 *//*


package com.csc349.project3;

import java.io.*;
import java.util.*;

public class GameProblem_input {
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
            solveGame(game.rows, game.cols, game.A);
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


    */
/* Scans the input file into the Game *//*

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

    */
/*
     ** Finds and saves the optimal result by starting at the bottom right
     ** and saving the optimal solution.  It then iterates through the
     ** rest going left and then up and saves each optimal solution there
     ** which would be the value of A there + min(row down, col right)
     *//*

    public static void solveGame(int rows, int cols, int[][] A) {
        int[][] best = new int[rows][cols];
    }

    */
/*
     ** Prints the result by starting at the max score and following
     ** the path of larger values until the end
     *//*

    public static void printResult(int[][] best, int startrow, int startcol) {
        boolean keep_going = true;
    }

    public static void printA(Game game) {
        for (int i = 0; i < game.rows; i++) {
            for (int j = 0; j < game.cols; j++) {
                System.out.print(game.A[i][j] + " ");
            }
            System.out.println("");
        }
    }
}*/
