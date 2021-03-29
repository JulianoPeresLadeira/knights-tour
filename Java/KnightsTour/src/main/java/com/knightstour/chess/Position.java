package com.knightstour.chess;


public class Position {
    private final int x;

    private final int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String toString() {
        return String.format("[%d, %d]", this.x, this.y);
    }
}
