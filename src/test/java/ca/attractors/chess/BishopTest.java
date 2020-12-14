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
        bishop = new Bishop(chessboard, White);
        chessboard.putPieceAt(bishop, D4);
    }



    @Test
    void moveToNonDiagonalSpot() {
        assertFalse(bishop.moveTo(D6));
    }

    @Test
    void moveToOccupiedCellOfSameColor() {
        Bishop bishop2 = new Bishop(chessboard, White);
        chessboard.putPieceAt(bishop2, D3);
        assertFalse(bishop2.moveTo(D3));
    }

    @Test
    void moveToOccupiedCellOfDifferentColor() {
        Bishop bishop2 = new Bishop(chessboard, Black);
        chessboard.putPieceAt(bishop2, A1);
        assertTrue(bishop.moveTo(A1));
        assertSame(bishop.getPosition(), A1);
        assertNull (chessboard.getPieceAt(D4));
    }

    @Test
    void moveDiagonallyWithUnoccupiedCells() {
        assertTrue(bishop.moveTo(F6));
        assertSame(bishop.getPosition(), F6);
    }
    @Test
    void moveDiagonallyToCellWithOccupiedCellsInBetween() {
        Bishop bishop2 = new Bishop(chessboard, Black);
        chessboard.putPieceAt(bishop2, E5);
        assertFalse(bishop.moveTo(F6));
    }

}
