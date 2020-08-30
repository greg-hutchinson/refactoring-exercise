package ca.attractors.chess;


import static ca.attractors.chess.PieceColor.White;

public class Chessboard {
    private ChessPiece[][] pieces = new ChessPiece[8][8];

    public Chessboard() {
    }

    public ChessPiece getPieceAt(Position position) {
        return pieces[position.getXOffset()] [position.getYOffset()];
    }

    void putPieceAt(ChessPiece chessPiece, Position position) {
        pieces[position.getXOffset()][position.getYOffset()] = chessPiece;
    }

    void movePieceTo(ChessPiece chessPiece, Position position) {
        putPieceAt(null, chessPiece.getPosition());
        putPieceAt(chessPiece, position);
    }

    Position getPositionOf(ChessPiece pawn) {
        for (Position position: Position.values()) {
            ChessPiece piece = getPieceAt(position);
            if (piece == pawn) {    //Refactor - inline variable - remove braces?
                return position;
            }
        }
        throw new UnsupportedOperationException("This piece is not found on this chessboard");
    }
}
