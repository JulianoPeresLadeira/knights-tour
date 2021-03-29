package com.knightstour.chess;

import lombok.Getter;

public class Position {
    @Getter
    private final int x;

    @Getter
    private final int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return String.format("[%d, %d]", this.x, this.y);
    }
}