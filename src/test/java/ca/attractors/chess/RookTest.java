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
        rook  = new Rook(White);
        chessboard.putPieceAt(rook, D4);
    }

    @Test
    void moveToNonHorizontalOrVerticalSpot() {
        assertFalse(chessboard.moveTo(rook, B2));
    }
    @Test
    void moveToOccupiedCellOfSameColor() {
        Rook rook2 = new Rook(White);
        chessboard.putPieceAt(rook2, D3);
        assertFalse(chessboard.moveTo(rook, D3));
    }

    @Test
    void moveToOccupiedCellOfDifferentColor() {
        Rook rook2 = new Rook(Black);
        chessboard.putPieceAt(rook2, D1);
        assertTrue(chessboard.moveTo(rook, D1));
        assertSame(chessboard.getPositionOf(rook), D1);
        assertNull(chessboard.getPieceAt(D4));
    }

    @Test
    void moveVerticallyWithUnoccupiedCells() {
        assertTrue(chessboard.moveTo(rook, D8));
        assertSame(chessboard.getPositionOf(rook), D8);
    }
    @Test
    void moveVerticallyToCellWithOccupiedCellsInBetween() {
        Rook rook2 = new Rook(Black);
        chessboard.putPieceAt(rook2, D7);
        assertFalse(chessboard.moveTo(rook, D8));
    }

    @Test
    void moveHorizontallyWithUnoccupiedCells() {
        assertTrue(chessboard.moveTo(rook, A4));
        assertSame(chessboard.getPositionOf(rook), A4);
    }


    @Test
    void moveHorizontallyToCellWithOccupiedCellsInBetween() {
        Rook rook2 = new Rook(Black);
        chessboard.putPieceAt(rook2, C4);
        assertFalse(chessboard.moveTo(rook, A4));
    }

}
