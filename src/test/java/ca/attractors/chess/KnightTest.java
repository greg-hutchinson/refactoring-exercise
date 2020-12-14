package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.PieceColor.Black;
import static ca.attractors.chess.PieceColor.White;
import static ca.attractors.chess.Position.*;
import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {
    private Chessboard chessboard;
    private Knight knight;

    @BeforeEach
    void initialize() {
        chessboard = new Chessboard();
        knight  = new Knight(chessboard, White);
        chessboard.putPieceAt(knight, D4);
    }

    @Test
    void moveToNonLShapedSpot() {
        assertFalse(knight.moveTo(D7));
    }

    @Test
    void moveToOccupiedCellOfSameColor() {
        Knight knight2 = new Knight(chessboard, White);
        chessboard.putPieceAt(knight2, E6);
        assertFalse(knight.moveTo(E6));
    }

    @Test
    void moveToOccupiedCellOfDifferentColor() {
        Knight knight2 = new Knight(chessboard, Black);
        chessboard.putPieceAt(knight2, E6);
        assertTrue(knight.moveTo(E6));
        assertSame(knight.getPosition(), E6);
        assertNull (chessboard.getPieceAt(D4));
    }

    @Test
    void moveLShapedWithUnoccupiedCell() {
        assertTrue(knight.moveTo(E6));
        assertSame(knight.getPosition(), E6);
    }
}
