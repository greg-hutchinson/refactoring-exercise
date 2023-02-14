package ca.attractors.chess;

import java.util.Arrays;
import java.util.stream.Stream;

public class Rook extends ChessPiece {

    public static final MoveDirection[] MOVE_DIRECTIONS = Stream.concat(
                    Arrays.stream(MoveDirection.HORIZONTAL_ONLY),
                    Arrays.stream(MoveDirection.VERTICAL_ONLY))
            .toArray(MoveDirection[]::new);

    protected Rook(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    @Override
    public MoveDirection[] getValidMoveDirections() {
        return MOVE_DIRECTIONS;
    }

    @Override
    public MoveLimit getMoveLimit() {
        return MoveLimit.NoLimit;
    }

}
