package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;

import static ca.attractors.chess.Position.*;

public class BishopTest {
    private Chessboard chessboard;
    private Bishop bishop;

    @BeforeEach
    void initialize() {
        chessboard = new Chessboard();
        bishop = new Bishop(PieceColor.White, chessboard, D4);
    }

}
