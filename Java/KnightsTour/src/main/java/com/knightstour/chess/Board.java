package com.knightstour.chess;

import lombok.Getter;

import java.util.Arrays;

public class Board {
    @Getter
    private final int sizeX;
    @Getter
    private final int sizeY;
    @Getter
    private final int[][] board;

    public Board(int sizeX, int sizeY) {

        if (sizeX <= 0 || sizeY <= 0) {
            throw new IllegalArgumentException("Board must have sizes greater than 0");
        }

        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.board = new int[sizeX][sizeY];
        this.resetBoard();
    }

    public void resetBoard() {
        Arrays.stream(board).forEach(row -> Arrays.fill(row, -1));
    }

    public int getPositionIndex(Position p) {
        return this.board[p.getX()][p.getY()];
    }

    public void setPositionIndex(Position p, int value) {
        this.board[p.getX()][p.getY()] = value;
    }

    public void resetPosition(Position p) {
        this.board[p.getX()][p.getY()] = -1;
    }

    public boolean isValidMove(Position p) {
        return p.getX() >= 0 &&
            p.getX() < sizeX &&
            p.getY() >= 0 &&
            p.getY() < sizeY &&
            this.getPositionIndex(p) == -1;
    }
}
