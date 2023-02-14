package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.PieceColor.Black;
import static ca.attractors.chess.PieceColor.White;
import static ca.attractors.chess.Position.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BishopTest {
    private Board board;

    @BeforeEach
    void initialize() {
        board = new Board();
    }

    @Test
    void moveHorizontally() {
        Bishop bishop = new Bishop(White, board, C1);
        assertFalse(bishop.moveTo(E1));
    }
    @Test
    void moveVertically() {
        Bishop bishop = new Bishop(White, board, C1);
        assertFalse(bishop.moveTo(C4));
    }

    @Test
    void moveDiagonally() {
        Bishop bishop = new Bishop(White, board, C1);
        assertTrue(bishop.moveTo(F4));
    }

    @Test
    void moveToNonHorizontalOrNonVerticalOrNonSpot() {
        Bishop bishop = new Bishop(White, board, C1);
        assertFalse(bishop.moveTo(E2));
    }

    @Test
    void moveHorizontallyToCellWithOccupiedCellsInBetween() {
        Bishop bishop1 = new Bishop(White, board, C1);
        new Bishop(Black, board, E3);
        assertFalse(bishop1.moveTo(F4));
    }

    @Test
    void moveToOccupiedCellOfSameColor() {
        Bishop bishop1 = new Bishop(White, board, C1);
        new Bishop(White, board, F4);
        assertFalse(bishop1.moveTo(F4));
    }

    @Test
    void moveToOccupiedCellOfDifferentColor() {
        Bishop bishop1 = new Bishop(White, board, C1);
        Bishop bishop2 = new Bishop(Black, board,F4);
        assertTrue(bishop1.moveTo(F4));
        assertSame(bishop1.getPosition(), F4);
        assertNull(bishop2.getPosition());
        assertEquals(bishop1, board.getPieceAt(F4));
    }
}
