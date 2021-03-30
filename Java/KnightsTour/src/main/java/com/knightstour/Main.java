package com.knightstour;

import com.knightstour.business.BusinessBoard;
import com.knightstour.chess.Position;

public class Main {
    public static void main(String[] args) {
        var board = new BusinessBoard(8,8, new Position(0,0));
        board.displayProgress();
        var solution = board.findSolution();

        if (solution.getTour() == null) {
            System.out.println(String.format("Board has no solution. Backtrack count: %d\n", solution.getTimesBacktracked()));
        } else {
            System.out.println(String.format("Solution found!. Backtrack count: %d\n", solution.getTimesBacktracked()));
            System.out.println(solution);
        }
    }
}