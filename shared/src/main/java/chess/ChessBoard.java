package chess;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private ChessPiece[][] squares = new ChessPiece[8][8];
    public ChessBoard() {
        resetBoard();
    }

    public static boolean isValidPosition(int row, int column) {
        return row >= 1 && row <= 8 && column >= 1 && column <= 8;
    }
    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        squares[position.getRow()-1][position.getColumn()-1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return squares[position.getRow()-1][position.getColumn()-1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        // White Piece Layout for Chessboard
        addPiece(new ChessPosition(1,1), new ChessPiece(ChessPiece.PieceType.ROOK, "W"));
        addPiece(new ChessPosition(1,2), new ChessPiece(ChessPiece.PieceType.KNIGHT, "W"));
        addPiece(new ChessPosition(1,3), new ChessPiece(ChessPiece.PieceType.BISHOP, "W"));
        addPiece(new ChessPosition(1,4), new ChessPiece(ChessPiece.PieceType.QUEEN, "W"));
        addPiece(new ChessPosition(1,5), new ChessPiece(ChessPiece.PieceType.KING, "W"));
        addPiece(new ChessPosition(1,6), new ChessPiece(ChessPiece.PieceType.BISHOP, "W"));
        addPiece(new ChessPosition(1,7), new ChessPiece(ChessPiece.PieceType.KNIGHT, "W"));
        addPiece(new ChessPosition(1,8), new ChessPiece(ChessPiece.PieceType.ROOK, "W"));

        // Pawns : White
        for (int i = 1; i <= 8; i++) {
            addPiece(new ChessPosition(2, i), new ChessPiece(ChessPiece.PieceType.PAWN, "W"));
        }

        // Black Piece Layout for Chessboard
        addPiece(new ChessPosition(8,1), new ChessPiece(ChessPiece.PieceType.ROOK, "B"));
        addPiece(new ChessPosition(8,2), new ChessPiece(ChessPiece.PieceType.KNIGHT, "B"));
        addPiece(new ChessPosition(8,3), new ChessPiece(ChessPiece.PieceType.BISHOP, "B"));
        addPiece(new ChessPosition(8,4), new ChessPiece(ChessPiece.PieceType.QUEEN, "B"));
        addPiece(new ChessPosition(8,5), new ChessPiece(ChessPiece.PieceType.KING, "B"));
        addPiece(new ChessPosition(8,6), new ChessPiece(ChessPiece.PieceType.BISHOP, "B"));
        addPiece(new ChessPosition(8,7), new ChessPiece(ChessPiece.PieceType.KNIGHT, "B"));
        addPiece(new ChessPosition(8,8), new ChessPiece(ChessPiece.PieceType.ROOK, "B"));

        // Pawns: Black
        for (int i = 1; i <= 8; i++) {
            addPiece(new ChessPosition(7, i), new ChessPiece(ChessPiece.PieceType.PAWN, "B"));
        }
    }
}
