package ca.attractors.chess;

import org.junit.jupiter.api.BeforeEach;

import static ca.attractors.chess.PieceColor.White;
import static ca.attractors.chess.Position.D4;

public class BishopTest {
    private Chessboard chessboard;
    private Bishop bishop;

    @BeforeEach
    void initialize() {
        chessboard = new Chessboard();
        bishop = new Bishop(White);
        chessboard.putPieceAt(bishop, D4);
    }

}
