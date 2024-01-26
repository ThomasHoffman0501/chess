package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessGame.TeamColor pieceColor;
    private final PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, PieceType type) {

        this.pieceColor = pieceColor;
        this.type = type;

    }

    /**
     * The various different chess piece options
     * <p>
     * class MyChessPiece implements ChessPiece, ChessBoard, ChessMove {
     * <p>
     * }
     */

    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        HashSet<ChessMove> validMoves = new HashSet<>();
        // getvalidMoveset for each ChessPiece and its Position

        // Checks the type of Chess piece. Gets the MoveSet from that piece
        switch (type) {
            case KING:
                validMoves.addAll(KingMoveSet.getMoves(board, myPosition, this.pieceColor));
                break;
            case QUEEN:
                validMoves.addAll(new QueenMoveSet().getMoves(board, myPosition, this.pieceColor));
                break;
            case ROOK:
                validMoves.addAll(new RookMoveSet().getMoves(board, myPosition, this.pieceColor));
                break;
            case PAWN:
                validMoves.addAll(PawnMoveSet.getMoves(board, myPosition, this.pieceColor));
                break;
            case KNIGHT:
                validMoves.addAll(KnightMoveSet.getMoves(board, myPosition, this.pieceColor));
                break;
            case BISHOP:
                validMoves.addAll(BishopMoveSet.getMoves(board, myPosition, this.pieceColor));
                break;
        }
        return validMoves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }

    @Override
    public String toString() {
        return "CP{" +
                "Color=" + pieceColor +
                ", type=" + type +
                '}';
    }
}
