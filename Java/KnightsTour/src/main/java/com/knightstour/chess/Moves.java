package com.knightstour.chess;

import java.util.ArrayList;
import java.util.List;

public class Moves {

    private static List<Position> moves;

    static {
        moves = new ArrayList<Position>();
        moves.add(new Position(1, 2));
        moves.add(new Position(2, 1));
        moves.add(new Position(2, -1));
        moves.add(new Position(1, -2));
        moves.add(new Position(-1, -2));
        moves.add(new Position(-2, -1));
        moves.add(new Position(-2, 1));
        moves.add(new Position(-1, 2));
    }

    public static Position getMove(int index) {
        return Moves.moves.get(index);
    }

    public static int getMoveCount() {
        return moves.size();
    }
}