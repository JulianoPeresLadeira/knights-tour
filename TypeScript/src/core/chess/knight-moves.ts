import { Move } from "../business/chess/move";

export const KnightMoves: Array<Move> = [
    new Move(1,2),
    new Move(2,1),
    new Move(2, -1),
    new Move(1, -2),
    new Move(-1, -2),
    new Move(-2, -1),
    new Move(-2, 1),
    new Move(-1, 2)
]

// {1, 2},
// {2, 1},
// {2, -1},
// {1, -2},
// {-1, -2},
// {-2, -1},
// {-2, 1},
// {-1, 2}