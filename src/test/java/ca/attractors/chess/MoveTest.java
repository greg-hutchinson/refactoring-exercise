package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.PieceColor.White;
import static ca.attractors.chess.Position.*;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {
    private Chessboard chessboard;
    private Move move;

    @BeforeEach
    void initialize() {
        chessboard = new Chessboard();
    }

    @Test
    void isPathBlocked() {
        Rook rook1 = new Rook(chessboard, White);
        chessboard.putPieceAt(rook1, A1);
        move = new Move(rook1, A6);
        assertFalse(move.isPathBlocked());

        Rook rook2 = new Rook(chessboard, White);
        chessboard.putPieceAt(rook2, A2);
        assertTrue(move.isPathBlocked());
    }
}
