package com.csc349.project3;

/**
 * Created by Andrew LeDawson on 2/12/2018.
 */
public class GameProblem {
    private enum Move {RIGHT, DOWN}

    public void main(){
        // TODO
    }

    public void game(int m, int n, int[][] A){
        int[][] scores = new int[m][n];
        Move[][] moves = new Move[m][n];
        int highestScoreX = -1;
        int highestScoreY = -1;

        // Start scanning at bottom row and right columm, iterate
        for(int column = m - 1, row = n - 1; column >= 0 && row >= 0; column--, row--){
            // Iterate through the row
            for(int xInRow = column; xInRow >= 0; xInRow--){
                Move bestMove = findBestMove(m, n, xInRow, row, A, scores, moves);
                // TODO
            }
            // Iterate through the column
            // TODO
        }
    }

    private Move findBestMove(int numRows, int numCols, int x, int y, int[][] grid, int[][] scores, Move[][] moves){
        if(y >= numRows){ // If no lower cells, must go right (include bottom rightmost cell)
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
}
