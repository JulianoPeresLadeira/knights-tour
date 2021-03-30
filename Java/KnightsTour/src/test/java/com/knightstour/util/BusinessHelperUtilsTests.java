package com.knightstour.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BusinessHelperUtilsTests {
    @Test
    public void shouldHaveSolution_3_10() {
        hasSolution(3, 10);
    }

    @Test
    public void shouldHaveSolution_10_3() {
        hasSolution(10, 3);
    }

    @Test
    public void shouldHaveSolution_10_8() {
        hasSolution(10, 8);
    }

    @Test
    public void shouldHaveSolution_8_10() {
        hasSolution(8, 10);
    }

    @Test
    public void shouldHaveSolution_8_8() {
        hasSolution(8, 8);
    }

    @Test
    public void shouldNotHaveSolution_9_9() {
        hasNoSolution(9, 9);
    }

    @Test
    public void shouldNotHaveSolution_3_4() {
        hasNoSolution(3, 4);
    }

    @Test
    public void shouldNotHaveSolution_4_3() {
        hasNoSolution(4, 3);
    }

    private void hasSolution(int x, int y) {
        assertTrue(BusinessHelper.hasSolution(x, y));
    }

    private void hasNoSolution(int x, int y) {
        assertFalse(BusinessHelper.hasSolution(x, y));
    }
}
