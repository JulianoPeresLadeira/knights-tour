package com.knightstour.business;

import com.knightstour.chess.Board;
import com.knightstour.chess.Moves;
import com.knightstour.chess.Position;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class BusinessBoard {
    private int sizeX;
    private int sizeY;
    private int boardSize;

    private Position[] tour;
    private Position knightPosition;
    private Board board;

    private long timesBacktracked;
    private int currentMoveOrder;

    private boolean displayProgress = false;
    private int timerInterval = 5000;
    private Timer lastTimer = null;

    @Getter @Setter
    private Position initialPosition;

    public BusinessBoard(int sizeX, int sizeY, Position initialPosition) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        boardSize = sizeX * sizeY;
        setInitialPosition(initialPosition);
    }

    public Solution findSolution() {
        this.setup();

        if (displayProgress) {
            printProgress();
        }

        while (!boardIsSolved(currentMoveOrder)) {
            int performedMoveIndex = this.findNextValidMove();

            if (performedMoveIndex == -1) {
                this.backtrack();
                if (currentMoveOrder < 0) {
                    return new Solution(timesBacktracked);
                }
                timesBacktracked++;
            } else {
                this.advanceKnight(performedMoveIndex);
            }
        }

        tour[currentMoveOrder] = knightPosition;
        finalizeExecution();
        return new Solution(timesBacktracked, Arrays.asList(tour), board);
    }

    public void displayProgress() {
        this.displayProgress = true;
    }

    private void printProgress() {
        this.lastTimer = new Timer();
        lastTimer.schedule(new TimerTask() {
            @Override()
            public void run() {
                System.out.println(String.format("Calculating tour. Times backtracked: %d", timesBacktracked));
                printProgress();
            }
        }, timerInterval);
    }

    private void setup() {
        currentMoveOrder = 0;
        timesBacktracked = 0;
        knightPosition = initialPosition;
        tour = new Position[boardSize];
        board = new Board(sizeX, sizeY);
    }

    private Position move(Position initial, Position move) {
        return new Position (initial.getX() + move.getX(), initial.getY() + move.getY());
    }

    private boolean boardIsSolved(int currentMoveOrder) {
        return currentMoveOrder == boardSize - 1;
    }

    private int findNextValidMove() {
        int moveIndex = (board.getPositionIndex(knightPosition) + 1);

        while (moveIndex < Moves.getMoveCount()) {
            var move = Moves.getMove(moveIndex);
            var newPosition = this.move(knightPosition, move);

            if (board.isValidMove(newPosition)) {
                return moveIndex;
            }

            moveIndex++;
        }

        return -1;
    }

    private void advanceKnight(int moveIndex) {
        board.setPositionValue(knightPosition, moveIndex);
        tour[currentMoveOrder] = knightPosition;
        currentMoveOrder++;
        var nextMove = Moves.getMove(moveIndex);
        knightPosition = getNextKnightPosition(nextMove);
    }

    private Position getNextKnightPosition(Position move) {
        return new Position(move.getX() + knightPosition.getX(), move.getY() + knightPosition.getY());
    }

    private void backtrack() {
        board.resetPosition(knightPosition);
        currentMoveOrder--;
        if (currentMoveOrder >= 0) {
            knightPosition = tour[currentMoveOrder];
        }
    }

    private void finalizeExecution() {
        if (lastTimer != null) {
            lastTimer.cancel();
        }
    }
}