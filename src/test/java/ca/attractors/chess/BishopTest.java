package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;

import static ca.attractors.chess.Position.*;

public class BishopTest {
    private Board board;
    private Bishop bishop;

    @BeforeEach
    void initialize() {
        board = new Board();
        bishop = new Bishop(PieceColor.White, board, D4);
    }

}
