package com.knightstour.util;

public class BusinessHelper {
    public static boolean hasSolution(int sizeX, int sizeY) {

        if (sizeX > sizeY) {
            return hasSolution (sizeY, sizeX);
        }

        var bothAreOdd = isOdd(sizeX) && isOdd(sizeY);
        var forbiddenXValues = sizeX == 1 || sizeX == 2 || sizeX == 4;
        var forbiddenYValueForX = (sizeX == 3) && (sizeY == 4 || sizeY == 6 || sizeY == 8);

        if (bothAreOdd || forbiddenXValues || forbiddenYValueForX) return false;
        return true;
    }

    private static boolean isOdd(int x) {
        return x % 2 == 1;
    }
}
