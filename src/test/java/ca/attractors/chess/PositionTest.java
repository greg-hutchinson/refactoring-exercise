package ca.attractors.chess;

import org.junit.jupiter.api.Test;

import static ca.attractors.chess.Position.A1;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    @Test
    void getters() {
        assertEquals(0, A1.getXOffset());
        assertEquals(0, A1.getYOffset());
        assertEquals('a', A1.getX());
        assertEquals(1, A1.getY());
    }
}
