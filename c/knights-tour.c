#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

#define true 1
#define false 0

#define x 8
#define y 8

const static int boardSize = y * x;

int getPositionX (int position) {
    return position % x;
}

int getPositionY (int position) {
    return position / x;
}

int calculateMove(int dRow, int dColumn) {
    return dRow + x * dColumn;
}

bool boardIsSolved(int currentMoveOrder) {
    return (currentMoveOrder == (boardSize - 1));
}

bool dXIsValid(int position, short dX) {
    int positionX = getPositionX(position);
    return (positionX + dX) >= 0 && (positionX + dX) < x;
}

bool dYIsValid(int position, short dY) {
    int positionY = getPositionY(position);
    return (positionY + dY) >= 0 && (positionY + dY) < y;
}

void printPosition(int position) {
    int px = position % x;
    int py = position / x;

    printf("%d\t(%d, %d)\n", position, px, py);
}

int main () {
    int backtrackCount = 0;
    short* board = (short *) malloc (boardSize * sizeof (short));

    short* tour = (short *) malloc (boardSize * sizeof (short));
    int currentMoveOrder = 0;

    for (int i = 0; i < boardSize; i++) {
        board[i] = -1;
    }

    short moves[8][2] = {
        {1, 2},
        {2, 1},
        {2, -1},
        {1, -2},
        {-1, -2},
        {-2, -1},
        {-2, 1},
        {-1, 2}
    };

    int knightPosition = 0;

    while (!boardIsSolved(currentMoveOrder)) {
        int validMove = -1;
        int nextPosition = -1;
        for (int moveIndex = (board[knightPosition] + 1); moveIndex < 8; moveIndex++) {
            short * move = moves[moveIndex];
            int newPosition = knightPosition + calculateMove(move[0], move[1]);
            
            if (newPosition >= 0 && newPosition < boardSize && dXIsValid(knightPosition, move[0]) && dYIsValid(knightPosition, move[1]) && board[newPosition] == -1) {
                validMove = moveIndex;
                nextPosition = newPosition;
                break;
            }
        }

        if (validMove == -1) {
            // No moves found, backtracking
            backtrackCount++;
            board[knightPosition] = -1;
            currentMoveOrder--;
            if (currentMoveOrder < 0) {
                printf("Board can't be solved (%d)\n", backtrackCount);
                return 0;
            }
            knightPosition = tour[currentMoveOrder];
        } else {
            board[knightPosition] = validMove;
            tour[currentMoveOrder] = knightPosition;
            currentMoveOrder++;
            knightPosition = nextPosition;
        }
    }

    tour[currentMoveOrder] = knightPosition;

    printf("Solved!\nBacktrack count: %d\n", backtrackCount);
    for (int i = 0; i < boardSize; i++) {
        printPosition(tour[i]);
    }
}
