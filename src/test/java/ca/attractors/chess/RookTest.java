package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.Position.*;
import static ca.attractors.chess.PieceColor.*;

import static org.junit.jupiter.api.Assertions.*;

public class RookTest {
    private Chessboard chessboard;
    private Rook rook;

    @BeforeEach
    void initialize() {
        chessboard = new Chessboard();
        rook  = new Rook(chessboard, White);
        chessboard.putPieceAt(rook, D4);
    }

    @Test
    void moveToNonHorizontalOrVerticalSpot() {
        assertFalse(rook.moveTo(B2));
    }
    @Test
    void moveToOccupiedCellOfSameColor() {
        Rook rook2 = new Rook(chessboard, White);
        chessboard.putPieceAt(rook2, D3);
        assertFalse(rook.moveTo(D3));
    }

    @Test
    void moveToOccupiedCellOfDifferentColor() {
        Rook rook2 = new Rook(chessboard, Black);
        chessboard.putPieceAt(rook2, D1);
        assertTrue(rook.moveTo(D1));
        assertSame(rook.getPosition(), D1);
        assertNull (chessboard.getPieceAt(D4));
    }

    @Test
    void moveVerticallyWithUnoccupiedCells() {
        assertTrue(rook.moveTo(D8));
        assertSame(rook.getPosition(), D8);
    }
    @Test
    void moveVerticallyToCellWithOccupiedCellsInBetween() {
        Rook rook2 = new Rook(chessboard, Black);
        chessboard.putPieceAt(rook2, D7);
        assertFalse(rook.moveTo(D8));
    }

    @Test
    void moveHorizontallyWithUnoccupiedCells() {
        assertTrue(rook.moveTo(A4));
        assertSame(rook.getPosition(), A4);
    }

    @Test
    void moveHorizontallyToCellWithOccupiedCellsInBetween() {
        Rook rook2 = new Rook(chessboard, Black);
        chessboard.putPieceAt(rook2, C4);
        assertFalse(rook.moveTo(A4));
    }

}
