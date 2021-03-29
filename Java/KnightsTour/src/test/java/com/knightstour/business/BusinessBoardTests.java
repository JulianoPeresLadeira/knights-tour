package com.knightstour.business;

import com.knightstour.chess.Position;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class BusinessBoardTests {

    @Test(expected = IllegalArgumentException.class)
    public void ShouldThrowErrorOnIllegalBoardSize() {
        BusinessBoard board = new BusinessBoard(0,0, new Position(0,0));
    }

    @Test
    public void ShouldHaveNoSolutionForImpossibleBoard() {
        BusinessBoard board = new BusinessBoard(1,2, new Position(0,0));
        var result = board.findSolution();
        assertNull(result);
    }
}
