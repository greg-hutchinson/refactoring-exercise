package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.Position.*;
import static ca.attractors.chess.PieceColor.*;

import static org.junit.jupiter.api.Assertions.*;

public class RookTest {
    private Chessboard chessboard;

    @BeforeEach
    void initialize() {
        chessboard = new Chessboard();
    }

    @Test
    void moveToNonHorizontalOrVerticalSpot() {
        Rook rook1 = new Rook(White, chessboard, D4);
        assertFalse(rook1.moveTo(B2));
    }

    @Test
    void moveVerticallyWithUnoccupiedCells() {
        Rook rook = new Rook(White, chessboard, D4);
        assertTrue(rook.moveTo(D8));
        assertSame(rook.getPosition(), D8);
    }
    @Test
    void moveVerticallyToCellWithOccupiedCellsInBetween() {
        Rook rook1 = new Rook(White, chessboard, D4);
        Rook rook2 = new Rook(Black, chessboard, D7);
        assertFalse(rook1.moveTo(D8));
    }

    @Test
    void moveHorizontallyWithUnoccupiedCells() {
        Rook rook1 = new Rook(White, chessboard, D4);
        assertTrue(rook1.moveTo(A4));
        assertSame(rook1.getPosition(), A4);
    }


    @Test
    void moveHorizontallyToCellWithOccupiedCellsInBetween() {
        Rook rook1 = new Rook(White, chessboard, D4);
        Rook rook2 = new Rook(Black, chessboard, C4);
        assertFalse(rook1.moveTo(A4));
    }

    @Test
    void moveToOccupiedCellOfSameColor() {
        Rook rook1 = new Rook(White, chessboard, D4);
        Rook rook2 = new Rook(White, chessboard, D3);
        assertFalse(rook1.moveTo(D3));
    }

    @Test
    void moveToOccupiedCellOfDifferentColor() {
        Rook rook1 = new Rook(White, chessboard, D4);
        Rook rook2 = new Rook(Black, chessboard,D1);
        assertTrue(rook1.moveTo(D1));
        assertSame(rook1.getPosition(), D1);
        assertNull(rook2.getPosition());
        assertNull (chessboard.getPieceAt(D4));
    }

}
