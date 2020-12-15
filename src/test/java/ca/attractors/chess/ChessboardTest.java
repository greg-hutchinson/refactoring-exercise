package ca.attractors.chess;

import ca.attractors.chess.Chessboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.PieceColor.White;
import static ca.attractors.chess.Position.*;
import static org.junit.jupiter.api.Assertions.*;


public class ChessboardTest {
    private Chessboard chessboard;
    private ChessPiece rook;

    @BeforeEach
    void initialize() {
        chessboard = Chessboard.init(BoardSettings.getDefaultSettings());
        rook = chessboard.pick(A1);

        // movePawnOutOfWay
        chessboard.pick(A2).moveTo(A3);
        chessboard.pick(A7).moveTo(A6);
        chessboard.pick(A3).moveTo(A4);
        chessboard.pick(A6).moveTo(A5);
    }

    @Test
    void getPieceAt() {
        assertTrue(rook.getColor() == White && rook.getPosition() == A1 && rook.getName().equals(RookPiece.class.getSimpleName()));
    }

    @Test
    void movePieceTo() {
        rook = chessboard.pick(A1);
        assertFalse(rook.moveTo(C3));
        assertTrue(rook.moveTo(A3));
        ChessPiece a3Piece = chessboard.getPieceAtPosition(A3);
        assertTrue(a3Piece.getName().equals(RookPiece.class.getSimpleName()) && a3Piece.getPosition() == A3);
    }

    @Test
    void getCurrentMoveColor() {
        movePieceTo();
        assertSame(chessboard.getCurrentMoveColor(), PieceColor.Black);
    }
}

