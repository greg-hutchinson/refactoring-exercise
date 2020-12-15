package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.Position.*;
import static org.junit.jupiter.api.Assertions.*;

public class BishopTest {
    private Chessboard chessboard;

    @BeforeEach
    void initialize() {
        chessboard = Chessboard.init(BoardSettings.getDefaultSettings());
        ChessPiece c1Piece = chessboard.pick(C1);
        assertTrue(c1Piece.getName().equals(BishopPiece.class.getSimpleName()) && c1Piece.getColor() == PieceColor.White);
    }

}
