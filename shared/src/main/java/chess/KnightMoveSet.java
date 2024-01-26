package chess;
import java.util.Collection;
import java.util.HashSet;

public class KnightMoveSet {
    public static Collection<ChessMove> getMoves(ChessBoard board, ChessPosition currentPosition, ChessGame.TeamColor pieceColor) {
        HashSet<ChessMove> correctMoves = new HashSet<>();

        int currentRow = currentPosition.getRow();
        int currentColumn = currentPosition.getColumn();

        // Knight Moves
        addMove(correctMoves, board, currentPosition, currentRow + 2, currentColumn + 1);
        addMove(correctMoves, board, currentPosition,currentRow + 1, currentColumn + 2);
        addMove(correctMoves, board, currentPosition ,currentRow - 1, currentColumn + 2);
        addMove(correctMoves, board, currentPosition, currentRow - 2, currentColumn + 1);
        addMove(correctMoves, board, currentPosition,currentRow - 2, currentColumn - 1);
        addMove(correctMoves, board, currentPosition,currentRow - 1, currentColumn - 2);
        addMove(correctMoves, board, currentPosition,currentRow + 1, currentColumn - 2);
        addMove(correctMoves, board, currentPosition,currentRow + 2, currentColumn - 1);

        return correctMoves;
    }
    private static void addMove(Collection<ChessMove> correctMoves, ChessBoard board, ChessPosition chessPosition, int row, int column) {
        if (ChessBoard.isCorrectPosition(row, column)) {
            ChessPiece pieceAtNewPosition = board.getPiece(new ChessPosition(row, column));
            if (pieceAtNewPosition == null || !pieceAtNewPosition.getTeamColor().equals(board.getPiece(chessPosition).getTeamColor())) {
                correctMoves.add(new ChessMove(chessPosition, new ChessPosition(row, column), null));
            }
        }
    }
}

