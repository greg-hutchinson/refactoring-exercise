package ca.attractors.chess;

import org.junit.jupiter.api.Test;

import static ca.attractors.chess.Position.*;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    @Test
    void getters() {
        assertEquals(0, A1.getXOffset());
        assertEquals(0, A1.getYOffset());
        assertEquals('a', A1.x);
        assertEquals(1, A1.y);
    }
    @Test
    void isHorizontalTo() {
        assertTrue(A1.isHorizontalTo(D1));
        assertFalse(A1.isHorizontalTo(A5));
    }
    @Test
    void isVerticalTo() {
        assertTrue(A1.isVerticalTo(A5));
        assertFalse(A1.isVerticalTo(D1));
    }
    @Test
    void isDiagonalTo() {
        assertTrue(A1.isDiagonalTo(D4));
        assertFalse(A1.isDiagonalTo(D2));
    }
}
