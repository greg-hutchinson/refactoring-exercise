package ca.attractors.chess;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.attractors.chess.Position.*;
import static org.junit.jupiter.api.Assertions.*;

public class BishopTest {
    private Chessboard chessboard;
    private Bishop bishop;

    @BeforeEach
    void initialize() {
        chessboard = new Chessboard();
        bishop = new Bishop(chessboard);
        chessboard.putPieceAt(bishop, D4);
    }

}
