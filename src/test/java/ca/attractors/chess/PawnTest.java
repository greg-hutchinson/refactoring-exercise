package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.PieceColor.*;
import static ca.attractors.chess.Position.*;
import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {
    private Chessboard chessboard;
    private Pawn pawn;

    @BeforeEach
    void initialize() {
        chessboard = new Chessboard();
        pawn = new Pawn(chessboard, White);
        chessboard.putPieceAt(pawn, A2);
    }

    @Test
    void moveOneCellForward() {
        assertTrue(pawn.moveTo(A3));
    }
    @Test
    void moveTwoCellsForward() {
        assertTrue(pawn.moveTo(A4));
    }

    @Test
    void moveTwoCellsForwardAfterFirstMove() {
        assertTrue(pawn.moveTo(A4));
 //       assertFalse(pawn.moveTo(A6));
    }

}
