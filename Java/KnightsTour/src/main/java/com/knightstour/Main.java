package com.knightstour;

import com.knightstour.business.BusinessBoard;
import com.knightstour.chess.Position;

import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) {
        var board = new BusinessBoard(8,8, new Position(0,0));
        board.findSolution();
    }
}
