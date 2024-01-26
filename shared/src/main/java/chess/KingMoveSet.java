package chess;
import java.util.Collection;
import java.util.HashSet;

public class KingMoveSet {

    public static Collection<ChessMove> getMoves(ChessBoard board, ChessPosition currentPosition, ChessGame.TeamColor pieceColor) {
        HashSet<ChessMove> correctMoves = new HashSet <>();

        int currentRow = currentPosition.getRow();
        int currentColumn = currentPosition.getColumn();

        // King Moves
        addMove(correctMoves, board, currentPosition,currentRow + 1, currentColumn); // D
        addMove(correctMoves, board, currentPosition,currentRow - 1, currentColumn); // U
        addMove(correctMoves, board, currentPosition, currentRow, currentColumn + 1); // R
        addMove(correctMoves, board, currentPosition, currentRow, currentColumn - 1); // L
        addMove(correctMoves, board, currentPosition, currentRow + 1, currentColumn + 1); // Diagonal: DR
        addMove(correctMoves, board, currentPosition,currentRow + 1, currentColumn - 1); // Diagonal: DL
        addMove(correctMoves, board, currentPosition,currentRow - 1, currentColumn + 1); // Diagonal: UR
        addMove(correctMoves, board, currentPosition,currentRow - 1, currentColumn - 1); // Diagonal: UL

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



