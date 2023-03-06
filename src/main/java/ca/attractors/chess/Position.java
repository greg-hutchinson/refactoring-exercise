package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

public enum Position {
    A1('a',1), A2('a', 2), A3('a', 3), A4('a', 4), A5('a', 5), A6('a', 6), A7('a', 7), A8('a', 8),
    B1('b',1), B2('b', 2), B3('b', 3), B4('b', 4), B5('b', 5), B6('b', 6), B7('b', 7), B8('b', 8),
    C1('c',1), C2('c', 2), C3('c', 3), C4('c', 4), C5('c', 5), C6('c', 6), C7('c', 7), C8('c', 8),
    D1('d',1), D2('d', 2), D3('d', 3), D4('d', 4), D5('d', 5), D6('d', 6), D7('d', 7), D8('d', 8),
    E1('e',1), E2('e', 2), E3('e', 3), E4('e', 4), E5('e', 5), E6('e', 6), E7('e', 7), E8('e', 8),
    F1('f',1), F2('f', 2), F3('f', 3), F4('f', 4), F5('f', 5), F6('f', 6), F7('f', 7), F8('f', 8),
    G1('g',1), G2('g', 2), G3('g', 3), G4('g', 4), G5('g', 5), G6('g', 6), G7('g', 7), G8('g', 8),
    H1('h',1), H2('h', 2), H3('h', 3), H4('h', 4), H5('h', 5), H6('h', 6), H7('h', 7), H8('h', 8);

    char x;
    int y;
    int increment = 0;

    Position(char x, int y) {
        this.x = x;
        this.y = y;
    }

    int getXOffset() {
        return x - 'a';
    }
    int getYOffset() {
        return y - 1;
    }

    static Position getPositionFor (int xOffset, int yOffset) {
        for (Position position: Position.values()) {
            if (position.getXOffset() == xOffset) {
                if (position.getYOffset() == yOffset)
                    return position;
            }
        }
        throw new IllegalArgumentException("There is no position with these offsets " + xOffset + ":" + yOffset);
    }

    public int getIncrement(int start, int end) {
        if (start > end)
            increment = -1;
        else
            increment = 1;
        return increment;
    }

    public boolean isSquareOccupiedBySameColor(Position targetPosition, ChessPiece piece) {
        ChessPiece targetPiece = piece.getChessboard().getPieceAt(targetPosition);
        if (targetPiece != null)
            return targetPiece.getColor() == piece.getColor();
        return false;
    }

    public boolean isDiagonalMove(Position targetPosition, ChessPiece piece) {
        return targetPosition.x != piece.getPosition().x && targetPosition.y != piece.getPosition().y;
    }

    public boolean isHorizontalMove(Position targetPosition, ChessPiece piece) {
        return targetPosition.x == piece.getPosition().x;
    }

    public boolean isVerticalMove(Position targetPosition, ChessPiece piece) {
        return targetPosition.y == piece.getPosition().y;
    }

    public boolean isInvalidMove(Position targetPosition, int start, int end, ChessPiece piece) {
        int increment = targetPosition.getIncrement(start, end);
        List<Position> positions = new ArrayList<>();
        if(targetPosition.isHorizontalMove(targetPosition, piece))
            positions = getHorizontalPositions(targetPosition, start, end, increment);
        else if(targetPosition.isVerticalMove(targetPosition, piece))
            positions = getVerticalPositions(targetPosition, start, end, increment);
        //need to add diagonal functionality block here for queen next
        for (Position position: positions) {
            if (isSuccessfulMove(position, piece)) return true;
        }
        return false;
    }

    private static List<Position> getVerticalPositions(Position targetPosition, int start, int end, int increment) {
        List<Position> positions = new ArrayList<>();
        for (int x = start + increment; x != end; x = x + increment) {
            positions.add(Position.getPositionFor(x, targetPosition.getYOffset()));
        }
        return positions;
    }

    private static List<Position> getHorizontalPositions(Position targetPosition, int start, int end, int increment) {
        List<Position> positions = new ArrayList<>();
        for (int y = start + increment; y != end; y = y + increment) {
            positions.add(Position.getPositionFor(targetPosition.getXOffset(), y));
        }
        return positions;
    }

    private boolean isSuccessfulMove(Position position, ChessPiece piece) {
        return piece.getChessboard().getPieceAt(position) != null;
    }



}
