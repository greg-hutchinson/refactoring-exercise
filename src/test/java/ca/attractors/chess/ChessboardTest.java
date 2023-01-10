package ca.attractors.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.PieceColor.White;
import static ca.attractors.chess.Position.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;



public class ChessboardTest {
    private Chessboard chessboard;
    private Rook rook;

        @BeforeEach
        void initialize() {
            chessboard = new Chessboard();
            rook  = new Rook(White, chessboard, D4);
        }

        @Test
        void getPieceAt() {
            assertSame(rook, chessboard.getPieceAt(D4));
        }

        @Test
        void movePieceTo() {
            chessboard.movePieceTo(rook, A1);
            assertNull(chessboard.getPieceAt(D4));
            assertSame(rook, chessboard.getPieceAt(A1));
        }

        @Test
        void getPositionOf() {
            assertSame(D4, chessboard.getPositionOf(rook));
        }
    }

