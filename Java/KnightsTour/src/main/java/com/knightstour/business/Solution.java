package com.knightstour.business;

import com.knightstour.chess.Board;
import com.knightstour.chess.Position;
import lombok.Getter;

import java.util.List;

public class Solution {
    @Getter
    private final long timesBacktracked;

    @Getter
    private final List<Position> tour;

    private final Board board;

    private boolean hasSetSolutionOnBoard = false;

    public Solution(long timesBacktracked, List<Position> tour, Board board) {
        this.timesBacktracked = timesBacktracked;
        this.tour = tour;
        this.board = board;
    }

    public Solution (long timesBacktracked) {
        this.timesBacktracked = timesBacktracked;
        this.tour = null;
        this.board = null;
    }

    public String toString() {
        if (!hasSetSolutionOnBoard) {
            for (var tourCounter = 0; tourCounter < tour.size(); tourCounter++) {
                var position = tour.get(tourCounter);
                board.setPositionValue(position, tourCounter);
            }
        }

        return board.toString();
    }
}
