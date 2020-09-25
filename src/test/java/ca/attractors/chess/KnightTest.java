package ca.attractors.chess;


import com.sun.org.apache.xpath.internal.functions.Function3Args;
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
        knight = new Knight(chessboard, White);
        chessboard.putPieceAt(knight, D4);
    }

    @Test
    void canMoveToUnoccupiedCell() {
        assertTrue(knight.canMoveTo(F5));
        assertTrue(knight.canMoveTo(F3));
        assertTrue(knight.canMoveTo(B5));
        assertTrue(knight.canMoveTo(B3));
    }

    @Test
    void canMoveToInvalidCell3StepsAway() {
        assertFalse(knight.canMoveTo(D7));
    }
}
