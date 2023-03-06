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

    boolean isHorizontalMove(Position targetPosition) {
        return targetPosition.y == y && targetPosition.x != x;
    }

    boolean isVerticalMove(Position targetPosition) {
        return targetPosition.x == x && targetPosition.y != y;
    }

    boolean isDiagonalMove(Position targetPosition) {
        return targetPosition.x != x && targetPosition.y != y
                && Math.abs(targetPosition.x - x) == Math.abs(targetPosition.y - y);
    }

    List<Position> getPositionsForDiagonalPath(Position targetPosition) {
        int startX = this.getXOffset();
        int endX = targetPosition.getXOffset();
        int startY = this.getYOffset();
        int endY = targetPosition.getYOffset();

        int incrementX;
        if (startX == endX)
            incrementX = 0;
        else if (startX > endX)
            incrementX = -1;
        else
            incrementX = 1;

        int incrementY;
        if (startY == endY)
            incrementY = 0;
        else if (startY > endY)
            incrementY = -1;
        else
            incrementY = 1;

        List<Position> positions = new ArrayList<>();
        for (int x = startX + incrementX; x != endX; x = x + incrementX) {
            for (int y = startY + incrementY; y != endY; y = y + incrementY) {
                positions.add(Position.getPositionFor(x, y));
            }
        }
        return positions;
    }

    List<Position> getPositionsForStraightPath(Position targetPosition) {
        // Assume we are moving horizontally by default
        int start = this.getYOffset();
        int end = targetPosition.getYOffset();

        // If Y position hasn't changed, then we are moving in the X direction
        if (this.isHorizontalMove(targetPosition)) {
            start = this.getXOffset();
            end = targetPosition.getXOffset();
        }

        int increment;
        if (start > end)
            increment = -1;
        else
            increment = 1;

        List<Position> positions = new ArrayList<>();
        for (int i = start +increment; i != end; i = i + increment) {
            if (this.isHorizontalMove(targetPosition)) {
                positions.add(Position.getPositionFor(i, targetPosition.getYOffset()));
            } else {
                positions.add(Position.getPositionFor(targetPosition.getXOffset(), i));
            }
        }
        return positions;
    }

    public List<Position> getPositionsForPath(Position targetPosition) {
        if (this.isVerticalMove(targetPosition) || this.isHorizontalMove(targetPosition)) {
            return getPositionsForStraightPath(targetPosition);
        }
        if (this.isDiagonalMove(targetPosition)) {
            return getPositionsForDiagonalPath(targetPosition);
        }
        throw new IllegalArgumentException("Unable to determine the direction of the move.");
    }
}
