package com.knightstour.business;
import com.knightstour.chess.Board;
import com.knightstour.chess.Moves;
import com.knightstour.chess.Position;

import java.util.Arrays;
import java.util.List;

public class BusinessBoard {
    private int sizeX;
    private int sizeY;
    private Position knightPosition;
    private int boardSize;
    private Board board;
    private Position[] tour;
    private int backtrackCount;

    public BusinessBoard(int sizeX, int sizeY, Position initialPosition) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        boardSize = sizeX * sizeY;
        knightPosition = initialPosition;
        backtrackCount = 0;
        board = new Board(sizeX, sizeY);
        tour = new Position[boardSize];
    }

    public List<Position> findSolution() {
        int currentMoveOrder = 0;

        while (!boardIsSolved(currentMoveOrder)) {
            int validMove = -1;
            Position nextPosition = new Position(-1, -1);
            for (int moveIndex = (board.getPositionIndex(knightPosition) + 1); moveIndex < Moves.getMoveCount(); moveIndex++) {
                var move = Moves.getMove(moveIndex);
                var newPosition = this.move(knightPosition, move);

                if (board.isValidMove(newPosition)) {
                    validMove = moveIndex;
                    nextPosition = newPosition;
                    break;
                }
            }

            if (validMove == -1) {
                backtrackCount++;
                board.resetPosition(knightPosition);
                currentMoveOrder--;
                if (currentMoveOrder < 0) {
                    System.out.println("Board cannot be solved");
                    return null;
                }
                knightPosition = tour[currentMoveOrder];
            } else {
                board.setPositionIndex(knightPosition, validMove);
                tour[currentMoveOrder] = knightPosition;
                currentMoveOrder++;
                knightPosition = nextPosition;
            }
        }

        tour[currentMoveOrder] = knightPosition;
        System.out.println("Solved!");
        System.out.println(String.format("Backtrack count: %d\n", backtrackCount));
        return Arrays.asList(tour);
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