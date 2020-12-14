So far we have the following problems
- we are duplicating information between the board and the piece. (Violates Dry principal)
- The subscripting is unnatural for the domain.

Now that it has been refactored.
- No duplication of coordinates.
- Subscripting is natural.
- Side effect is that the code is a lot terser.

---

Some good points to thought about during refactoring:

1. Separation of concerns.

    - ChessBoard - responsible for providing the facade, maintaining internal states.
    - ChessPiece - responsible for providing logic shared by all children pieces.
    - XxxxxPiece - responsible for define their own moving and checking rules.
    
2. Defensive programing - Minimize the state mutation by external access.
    - Provided limited public API to consumer
    - Separate the scenarios of reading pieces and moving pieces. (Internal ImmutablePiece)
    - Handle the validation in one place.
    
3. As stateless as possible
    - There has to be many states there. However, at least minimized access of the states from public.
    
4. Readability - Make it more natural
    - Add some helper methods that return a new clone version of given position.
    - Add some shared helper method for scenarios like peeking positions.
    - Make it public API more natural, refer to below example.
    
Example use,
```
        // Init chessboard
        Chessboard chessboard = Chessboard.init(BoardSettings.getDefaultSettings());

        // Whose turn to move currently
        PieceColor currentMoveColor = chessboard.getCurrentMoveColor();
        // Get all current pieces on board as immutable copies
        List<ChessPiece> currentPieces = chessboard.getCurrentPieces();
        // Get all current movable pieces
        List<ChessPiece> currentMovablePieces = chessboard.getCurrentMovablePieces();

        // Pick one movable piece
        ChessPiece selectedPiece = chessboard.pick(currentMovablePieces.get(0).getPosition());
        ChessPiece orSelectedPiece = currentMovablePieces.get(0);
        Position selectedPosition = selectedPiece.getPosition();

        // Peek and move
        List<Position> validMovePositions = chessboard.pick(selectedPosition).getValidMovePositions();
        Position targetPosition = validMovePositions.get(0);
        chessboard.pick(selectedPosition).moveTo(targetPosition);

        // After the move operation, some states changed internally.
        List<ChessPiece> updatedChessPieces = chessboard.getCurrentPieces();
        PieceColor updatedMoveColor = chessboard.getCurrentMoveColor();
```