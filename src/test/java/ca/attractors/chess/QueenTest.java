package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.PieceColor.White;
import static ca.attractors.chess.Position.A1;
import static ca.attractors.chess.Position.A4;
import static ca.attractors.chess.Position.B3;
import static ca.attractors.chess.Position.C1;
import static ca.attractors.chess.Position.C3;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

}
