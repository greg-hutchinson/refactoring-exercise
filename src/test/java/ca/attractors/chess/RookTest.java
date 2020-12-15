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
    void illustrateRefactorSignature() {
        Rook rook1 = ChessPiece.newRookOnChessboard(chessboard);
    }
    @Test
    void moveToNonHorizontalOrVerticalSpot() {
        assertFalse(rook.moveToPosition(B2));
    }
    @Test
    void moveToOccupiedCellOfSameColor() {
        Rook rook2 = new Rook(chessboard, White);
        chessboard.putPieceAt(rook2, D3);
        assertFalse(rook.moveToPosition(D3));
    }

    @Test
    void moveToOccupiedCellOfDifferentColor() {
        Rook rook2 = new Rook(chessboard, Black);
        chessboard.putPieceAt(rook2, D1);
        assertTrue(rook.moveToPosition(D1));
        assertSame(rook.getPosition(), D1);
        assertNull (chessboard.getPieceAt(D4));
    }

    @Test
    void moveVerticallyWithUnoccupiedCells() {
        assertTrue(rook.moveToPosition(D8));
        assertSame(rook.getPosition(), D8);
    }
    @Test
    void moveVerticallyToCellWithOccupiedCellsInBetween() {
        Rook rook2 = new Rook(chessboard, Black);
        chessboard.putPieceAt(rook2, D7);
        assertFalse(rook.moveToPosition(D8));
    }

    @Test
    void moveHorizontallyWithUnoccupiedCells() {
        assertTrue(rook.moveToPosition(A4));
        assertSame(rook.getPosition(), A4);
    }


    @Test
    void moveHorizontallyToCellWithOccupiedCellsInBetween() {
        Rook rook2 = new Rook(chessboard, Black);
        chessboard.putPieceAt(rook2, C4);
        assertFalse(rook.moveToPosition(A4));
    }

}
