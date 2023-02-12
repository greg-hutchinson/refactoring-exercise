package ca.attractors.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.PieceColor.White;
import static ca.attractors.chess.Position.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;



public class BoardTest {
    private Board board;
    private Rook rook;

        @BeforeEach
        void initialize() {
            board = new Board();
            rook  = new Rook(White, board, D4);
        }

        @Test
        void getPieceAt() {
            assertSame(rook, board.getPieceAt(D4));
        }

        @Test
        void movePieceTo() {
            board.movePieceTo(rook, A1);
            assertNull(board.getPieceAt(D4));
            assertSame(rook, board.getPieceAt(A1));
        }

        @Test
        void getPositionOf() {
            assertSame(D4, board.getPositionOf(rook));
        }
    }

