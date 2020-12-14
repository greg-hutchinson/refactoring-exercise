package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class Chessboard {

    private List<ChessPiece> pieces = new ArrayList<>(32);

    // Internal states
    private PieceColor currentMoveColor;
    private boolean isEnded = false;

    private Chessboard() {}

    public static Chessboard init(BoardSettings settings) {
        // White chess pieces
        Chessboard board = new Chessboard();
        board.pieces.add(new RookPiece(PieceColor.White, Position.A1, board));
        board.pieces.add(new RookPiece(PieceColor.White, Position.H1, board));
        board.pieces.add(new KnightPiece(PieceColor.White, Position.B1, board));
        board.pieces.add(new KnightPiece(PieceColor.White, Position.G1, board));
        board.pieces.add(new BishopPiece(PieceColor.White, Position.B1, board));
        board.pieces.add(new BishopPiece(PieceColor.White, Position.F1, board));
        board.pieces.add(new QueenPiece(PieceColor.White, Position.D1, board));
        board.pieces.add(new KingPiece(PieceColor.White, Position.E1, board));
        board.pieces.add(new PawnPiece(PieceColor.White, Position.A2, board));
        board.pieces.add(new PawnPiece(PieceColor.White, Position.B2, board));
        board.pieces.add(new PawnPiece(PieceColor.White, Position.C2, board));
        board.pieces.add(new PawnPiece(PieceColor.White, Position.D2, board));
        board.pieces.add(new PawnPiece(PieceColor.White, Position.E2, board));
        board.pieces.add(new PawnPiece(PieceColor.White, Position.F2, board));
        board.pieces.add(new PawnPiece(PieceColor.White, Position.G2, board));
        board.pieces.add(new PawnPiece(PieceColor.White, Position.H2, board));
        // Black chess pieces
        board.pieces.add(new RookPiece(PieceColor.Black, Position.A8, board));
        board.pieces.add(new RookPiece(PieceColor.Black, Position.H8, board));
        board.pieces.add(new KnightPiece(PieceColor.Black, Position.B8, board));
        board.pieces.add(new KnightPiece(PieceColor.Black, Position.G8, board));
        board.pieces.add(new BishopPiece(PieceColor.Black, Position.B8, board));
        board.pieces.add(new BishopPiece(PieceColor.Black, Position.F8, board));
        board.pieces.add(new QueenPiece(PieceColor.Black, Position.D8, board));
        board.pieces.add(new KingPiece(PieceColor.Black, Position.E8, board));
        board.pieces.add(new PawnPiece(PieceColor.Black, Position.A7, board));
        board.pieces.add(new PawnPiece(PieceColor.Black, Position.B7, board));
        board.pieces.add(new PawnPiece(PieceColor.Black, Position.C7, board));
        board.pieces.add(new PawnPiece(PieceColor.Black, Position.D7, board));
        board.pieces.add(new PawnPiece(PieceColor.Black, Position.E7, board));
        board.pieces.add(new PawnPiece(PieceColor.Black, Position.F7, board));
        board.pieces.add(new PawnPiece(PieceColor.Black, Position.G7, board));
        board.pieces.add(new PawnPiece(PieceColor.Black, Position.H7, board));

        // Initial state
        board.currentMoveColor = settings.getCurrentMoveColor();

        return board;
    }

    public PieceColor getCurrentMoveColor() {
        return currentMoveColor;
    }

    public List<ChessPiece> getCurrentPieces() {
        return pieces.stream().map(piece -> piece.asImmutable()).collect(Collectors.toList());
    }

    public ChessPiece getPieceAtPosition(Position position) {
        Optional<ChessPiece> existingPieceOccupied = getCurrentPieces().stream()
                .filter(p -> p.getPosition() == position)
                .map(Optional::ofNullable).findFirst().orElse(null);
        return existingPieceOccupied != null ? existingPieceOccupied.get() : null;
    }

    private ChessPiece internalActualGetPieceAtPosition(Position position) {
        Optional<ChessPiece> existingPieceOccupied = pieces.stream()
                .filter(p -> p.getPosition() == position)
                .map(Optional::ofNullable).findFirst().orElse(null);
        return existingPieceOccupied != null ? existingPieceOccupied.get() : null;
    }

    public List<ChessPiece> getCurrentMovablePieces() {
        return pieces.stream().filter(p -> p.getColor() == currentMoveColor).collect(Collectors.toList());
    }

    public ChessPiece pick(Position position) {
        return getCurrentMovablePieces()
                .stream().filter(piece -> piece.getPosition() == position)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public void moveTo(ChessPiece piece, Position position) {
        boolean moveSucceed = piece.movePieceTo(position);
        if (moveSucceed) {
            removeTargetPositionPieceIfExists(position);
            boolean isGameOver = determineGameOver();
            if (isGameOver) return;
            toggleCurrentMoveColor();
        }
    }

    private void removeTargetPositionPieceIfExists(Position position) {
        ChessPiece existingPieceOccupied = internalActualGetPieceAtPosition(position);
        if (existingPieceOccupied != null) {
            pieces.remove(existingPieceOccupied);
        }
    }

    private void toggleCurrentMoveColor() {
        this.currentMoveColor = this.currentMoveColor.toggle();
    }

    private boolean determineGameOver() {
        return false;
    }

}