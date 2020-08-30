package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.PieceColor.Black;
import static ca.attractors.chess.PieceColor.White;
import static ca.attractors.chess.Position.*;
import static org.junit.jupiter.api.Assertions.*;

public class BishopTest {
    private Chessboard chessboard;
    private Bishop bishop;

    @BeforeEach
    void initialize() {
        chessboard = new Chessboard();
        bishop  = new Bishop(chessboard, White);
        chessboard.putPieceAt(bishop, D4);
    }

    @Test
    void moveToNonDiagonalSpot() {
        assertFalse(bishop.moveTo(B1));
    }
    @Test
    void moveToOccupiedCellOfSameColor() {
        Bishop bishop2 = new Bishop(chessboard, White);
        chessboard.putPieceAt(bishop2, E5);
        assertFalse(bishop.moveTo(E5));
    }

    @Test
    void moveToOccupiedCellOfDifferentColor() {
        Bishop bishop2 = new Bishop(chessboard, Black);
        chessboard.putPieceAt(bishop2, E5);
        assertTrue(bishop.moveTo(E5));
        assertSame(bishop.getPosition(), E5);
        assertNull (chessboard.getPieceAt(D4));
    }

    @Test
    void moveDiagonallyInAllDirectionsWithUnoccupiedCells() {
        assertTrue(bishop.moveTo(A1));
        assertTrue(bishop.moveTo(D4));
        assertTrue(bishop.moveTo(A7));
        assertTrue(bishop.moveTo(D4));
    }

    @Test
    void moveDiagonallyToCellWithOccupiedCellsInBetween() {
        Bishop bishop2 = new Bishop(chessboard, Black);
        chessboard.putPieceAt(bishop2, E5);
        assertFalse(bishop.moveTo(F6));
    }

}
