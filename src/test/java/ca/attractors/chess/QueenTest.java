package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.PieceColor.Black;
import static ca.attractors.chess.PieceColor.White;
import static ca.attractors.chess.Position.A1;
import static ca.attractors.chess.Position.A3;
import static ca.attractors.chess.Position.A4;
import static ca.attractors.chess.Position.B1;
import static ca.attractors.chess.Position.B3;
import static ca.attractors.chess.Position.C1;
import static ca.attractors.chess.Position.C3;
import static ca.attractors.chess.Position.D1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenTest {
    private Board board;

    @BeforeEach
    void initialize() {
        board = new Board();
    }


    @Test
    void moveHorizontally() {
        Queen queen = new Queen(White, board, A1);
        assertTrue(queen.moveTo(C1));
    }

    @Test
    void moveVertically() {
        Queen queen = new Queen(White, board, A1);
        assertTrue(queen.moveTo(A4));
    }

    @Test
    void moveDiagonally() {
        Queen queen = new Queen(White, board, A1);
        assertTrue(queen.moveTo(C3));
    }

    @Test
    void moveToNonHorizontalOrNonVerticalOrNonSpot() {
        Queen queen = new Queen(White, board, A1);
        assertFalse(queen.moveTo(B3));
    }

    @Test
    void moveHorizontallyToCellWithOccupiedCellsInBetween() {
        Queen queen1 = new Queen(White, board, A1);
        new Queen(Black, board, B1);
        assertFalse(queen1.moveTo(C1));
    }

    @Test
    void moveToOccupiedCellOfSameColor() {
        Queen queen1 = new Queen(White, board, A1);
        new Queen(White, board, A3);
        assertFalse(queen1.moveTo(A3));
    }

    @Test
    void moveToOccupiedCellOfDifferentColor() {
        Queen queen1 = new Queen(White, board, A1);
        Queen queen2 = new Queen(Black, board, D1);
        assertTrue(queen1.moveTo(D1));
        assertSame(queen1.getPosition(), D1);
        assertNull(queen2.getPosition());
        assertEquals(queen1, board.getPieceAt(D1));
    }

}
