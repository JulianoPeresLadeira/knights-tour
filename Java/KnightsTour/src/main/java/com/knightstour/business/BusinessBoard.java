package com.knightstour.business;
import com.knightstour.chess.Moves;
import com.knightstour.chess.Position;

import java.util.Arrays;

public class BusinessBoard {
    private int sizeX;
    private int sizeY;
    private Position knightPosition;
    private int boardSize;
    private int[][] board;
    private Position[] tour;
    private int backtrackCount;

    public BusinessBoard(int sizeX, int sizeY, Position initialPosition) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        boardSize = sizeX * sizeY;
        knightPosition = initialPosition;
        backtrackCount = 0;
        board = new int[sizeX][sizeY];
        tour = new Position[boardSize];

        for (int[] row : board) {
            Arrays.fill(row, -1);
        }
    }

    public void findSolution() {
        int currentMoveOrder = 0;

        while (!boardIsSolved(currentMoveOrder)) {
            int validMove = -1;
            Position nextPosition = new Position(-1, -1);
            for (int moveIndex = (board[knightPosition.getX()][knightPosition.getY()] + 1); moveIndex < Moves.getMoveCount(); moveIndex++) {
                Position move = Moves.getMove(moveIndex);
                Position newPosition = this.move(knightPosition, move);

                if (isValidMove(newPosition)) {
                    validMove = moveIndex;
                    nextPosition = newPosition;
                    break;
                }
            }

            if (validMove == -1) {
                backtrackCount++;
                board[knightPosition.getX()][knightPosition.getY()] = -1;
                currentMoveOrder--;
                if (currentMoveOrder < 0) {
                    System.out.println("Board cannot be solved");
                }
                knightPosition = tour[currentMoveOrder];
            } else {
                board[knightPosition.getX()][knightPosition.getY()] = validMove;
                tour[currentMoveOrder] = knightPosition;
                currentMoveOrder++;
                knightPosition = nextPosition;
            }
        }

        tour[currentMoveOrder] = knightPosition;
        System.out.println("Solved!");
        System.out.println(String.format("Backtrack count: %d\n", backtrackCount));
    }

    private boolean isValidMove(Position p) {
        return
                p.getX() >= 0 &&
                        p.getX() < sizeX &&
                        p.getY() >= 0 &&
                        p.getY() < sizeY &&
                        board[p.getX()][p.getY()] == -1;
    }

    private Position move(Position initial, Position move) {
        return new Position (initial.getX() + move.getX(), initial.getY() + move.getY());
    }

    private boolean boardIsSolved(int currentMoveOrder) {
        return currentMoveOrder == boardSize - 1;
    }

    private int calculateNewPosition(Position dp) {
        return dp.getX() + sizeX * dp.getY();
    }
}