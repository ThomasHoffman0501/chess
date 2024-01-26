package chess;
import java.util.Collection;
import java.util.HashSet;

public class KnightMoveSet {
    public static Collection<ChessMove> getValidMoves(ChessBoard board, ChessPosition currentPosition, ChessGame.TeamColor pieceColor) {
        HashSet<ChessMove> validMoves = new HashSet<>();

        int currentRow = currentPosition.getRow();
        int currentColumn = currentPosition.getColumn();

        // Knight Moves
        addMove(validMoves, board, currentPosition, currentRow + 2, currentColumn + 1);
        addMove(validMoves, board, currentPosition,currentRow + 1, currentColumn + 2);
        addMove(validMoves, board, currentPosition ,currentRow - 1, currentColumn + 2);
        addMove(validMoves, board, currentPosition, currentRow - 2, currentColumn + 1);
        addMove(validMoves, board, currentPosition,currentRow - 2, currentColumn - 1);
        addMove(validMoves, board, currentPosition,currentRow - 1, currentColumn - 2);
        addMove(validMoves, board, currentPosition,currentRow + 1, currentColumn - 2);
        addMove(validMoves, board, currentPosition,currentRow + 2, currentColumn - 1);

        return validMoves;
    }
    private static void addMove(Collection<ChessMove> validMoves, ChessBoard board, ChessPosition chessPosition, int row, int column) {
        if (ChessBoard.isValidPosition(row, column)) {
            ChessPiece pieceAtNewPosition = board.getPiece(new ChessPosition(row, column));
            if (pieceAtNewPosition == null || !pieceAtNewPosition.getTeamColor().equals(board.getPiece(chessPosition).getTeamColor())) {
                validMoves.add(new ChessMove(chessPosition, new ChessPosition(row, column), null));
            }
        }
    }
}

