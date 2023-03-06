package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.PieceColor.White;
import static ca.attractors.chess.Position.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BishopTest {
    private Board board;

    @BeforeEach
    void initialize() {
        board = new Board();
    }

    @Test
    void moveHorizontally() {
        Bishop bishop = new Bishop(White, board, A1);
        assertFalse(bishop.moveTo(C1));
    }
    @Test
    void moveVertically() {
        Bishop bishop = new Bishop(White, board, A1);
        assertFalse(bishop.moveTo(A4));
    }

    @Test
    void moveDiagonally() {
        Bishop bishop = new Bishop(White, board, A1);
        assertTrue(bishop.moveTo(C3));
    }

    @Test
    void moveToNonHorizontalOrNonVerticalOrNonSpot() {
        Bishop bishop = new Bishop(White, board, A1);
        assertFalse(bishop.moveTo(B3));
    }
}
