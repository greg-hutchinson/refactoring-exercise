package ca.attractors.chess;


import ca.attractors.chess.ChessPiece;
import ca.attractors.chess.Chessboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.Position.*;
import static ca.attractors.chess.PieceColor.*;

import static org.junit.jupiter.api.Assertions.*;

public class RookTest {
    private Chessboard chessboard;
    private ChessPiece rook;

    @BeforeEach
    void initialize() {
        chessboard = new Chessboard();
        rook = new RookPiece(White, D4, chessboard);
        chessboard.addPieces(rook);
    }

    @Test
    void illustrateRefactorSignature() {
        RookPiece rook1 = new RookPiece(White, null, chessboard);
    }

    @Test
    void moveToNonHorizontalOrVerticalSpot() {
        assertFalse(rook.moveTo(B2));
    }
    @Test
    void moveToOccupiedCellOfSameColor() {
        RookPiece rook2 = new RookPiece(White, D3,  chessboard);
        chessboard.addPieces(rook2);
        assertFalse(rook.moveTo(D3));
    }

    @Test
    void moveToOccupiedCellOfDifferentColor() {
        RookPiece rook2 = new RookPiece(Black, D1, chessboard);
        chessboard.addPieces(rook2);
        assertTrue(rook.moveTo(D1));
        assertSame(rook.getPosition(), D1);
        assertNull (chessboard.getPieceAtPosition(D4));
    }

    @Test
    void moveVerticallyWithUnoccupiedCells() {
        assertTrue(rook.moveTo(D8));
        assertSame(rook.getPosition(), D8);
    }

    @Test
    void moveVerticallyToCellWithOccupiedCellsInBetween() {
        RookPiece rook2 = new RookPiece(Black, D7, chessboard);
        chessboard.addPieces(rook2);
        assertFalse(rook.moveTo(D8));
    }

    @Test
    void moveHorizontallyWithUnoccupiedCells() {
        assertTrue(rook.moveTo(A4));
        assertSame(rook.getPosition(), A4);
    }

    @Test
    void moveHorizontallyToCellWithOccupiedCellsInBetween() {
        RookPiece rook2 = new RookPiece(Black, C4, chessboard);
        chessboard.addPieces(rook2);
        assertFalse(rook.moveTo(A4));
    }

}
