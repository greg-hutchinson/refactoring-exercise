package ca.attractors.chess;

public class Chessboard {
    private final ChessPiece[][] pieces = new ChessPiece[8][8];

    public Chessboard() {
    }

    String chessPieceAsString(ChessPiece chessPiece) {
        return chessPiece.getName() + "{" + chessPiece.getColor() + " at: " + getPositionOf(chessPiece) + "}";
    }

    public ChessPiece getPieceAt(Position position) {
        return pieces[position.getXOffset()] [position.getYOffset()];
    }

    void putPieceAt(ChessPiece chessPiece, Position position) {
        pieces[position.getXOffset()][position.getYOffset()] = chessPiece;
    }

    void movePieceTo(ChessPiece chessPiece, Position position) {
        Position old = getPositionOf(chessPiece);
        putPieceAt(chessPiece, position);
        putPieceAt(null, old);
    }

    public Position getPositionOf(ChessPiece piece) {
        for (Position position: Position.values())
            if (getPieceAt(position) == piece)
                return position;
        return null;
    }

    public boolean moveTo(Rook rook, Position targetPosition) {
        if (!validMove(rook, targetPosition)) {
            return false;
        }
        // If we get here - is is a valid move. Physically move the piece and answer true.
        this.movePieceTo(rook, targetPosition);
        return true;
    }

    private boolean validMove(Rook rook, Position targetPosition) {
        Position rookPosition = getPositionOf(rook);
        // If target square is occupied, ensure it's not the same color
        ChessPiece targetPiece = this.getPieceAt(targetPosition);
        if (targetPiece != null && targetPiece.sameColorAs(rook)) {
                return false;
        }

        if (targetPosition.y == rookPosition.y) {
            return validateHorizontal(targetPosition, rookPosition);
        } else if (targetPosition.x == rookPosition.x) {
            return validateVertical(targetPosition, rookPosition);
        }
        return false;
    }

    private boolean validateVertical(Position targetPosition, Position rookPosition) {
        // Ensure all cells between the source and the target are empty
        if (targetPosition.x != rookPosition.x) {
            return false;
        }
        int start = rookPosition.getYOffset();
        int end = targetPosition.getYOffset();
        int increment = start > end ? -1 : 1;
        for (int y = start+increment; y != end; y += increment) {
            Position position = Position.getPositionFor(targetPosition.getXOffset(), y);
            if (this.getPieceAt(position) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean validateHorizontal(Position targetPosition, Position rookPosition) {
        // Ensure all cells between the source and the target are empty
        if (targetPosition.y != rookPosition.y) {
            return false;
        }
        int start = rookPosition.getXOffset();
        int end = targetPosition.getXOffset();
        int increment = start > end ? -1 : 1;
        for (int x = start+increment; x != end; x += increment) {
            Position position = Position.getPositionFor(x, targetPosition.getYOffset());
            if (this.getPieceAt(position) != null) {
                return false;
            }
        }
        return true;
    }
}