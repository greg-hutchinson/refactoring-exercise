package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.PieceColor.Black;
import static ca.attractors.chess.PieceColor.White;
import static ca.attractors.chess.Position.*;
import static org.junit.jupiter.api.Assertions.*;

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
