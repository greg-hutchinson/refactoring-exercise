package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

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
        Position rookPosition = getPositionOf(rook);
        //if it is not the same x or y coordinate it is not a rooks valid move at all
        if (targetPosition.x != rookPosition.x && targetPosition.y != rookPosition.y) {
            return false;
        }
        //Next - Check to make sure that if the target square is occupied it is not the same color
        ChessPiece targetPiece = this.getPieceAt(targetPosition);
        if (targetPiece != null) {
            if (targetPiece.getColor() == rook.getColor())
                return false;
        }
        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a horizontal move we need to increment the y coordinate until it is the same as the target's y
        // the increment might be positive or negative.
        if (targetPosition.x == rookPosition.x) {
            int start = rookPosition.getYOffset();
            int end = targetPosition.getYOffset();
            int increment = 0;
            if (start > end)
                increment = -1;
            else
                increment = 1;
            List<Position> positions = new ArrayList<>();
            for (int y = start+increment; y != end; y = y + increment) {
                positions.add(Position.getPositionFor(targetPosition.getXOffset(), y));
            }
            for (Position position: positions) {
                if (this.getPieceAt(position) != null) {
                    return false;
                }
            }
        }
        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a vertical move we need to increment the x coordinate until it is the same as the target's x
        // the increment might be positive or negative.
        if (targetPosition.y == rookPosition.y) {
            int start = rookPosition.getXOffset();
            int end = targetPosition.getXOffset();
            int increment = 0;
            if (start > end)
                increment = -1;
            else
                increment = 1;
            List<Position> positions = new ArrayList<>();
            for (int x = start+increment; x != end; x = x + increment) {
                positions.add(Position.getPositionFor(x, targetPosition.getYOffset()));
            }
            for (Position position: positions) {
                if (this.getPieceAt(position) != null) {
                    return false;
                }
            }
        }
        //If we get here - is is a valid move. Physically move the piece and answer true.
        this.movePieceTo(rook, targetPosition);
        return true;
    }
}