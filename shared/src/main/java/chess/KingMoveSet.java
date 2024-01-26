package chess;
import java.util.Collection;
import java.util.HashSet;

public class KingMoveSet {

    public static Collection<ChessMove> getValidMoves(ChessBoard board, ChessPosition currentPosition,ChessGame.TeamColor pieceColor) {
        HashSet<ChessMove> validMoves = new HashSet <>();

        int currentRow = currentPosition.getRow();
        int currentColumn = currentPosition.getColumn();

        // King Moves
        addMove(validMoves, board, currentPosition,currentRow + 1, currentColumn); // D
        addMove(validMoves, board, currentPosition,currentRow - 1, currentColumn); // U
        addMove(validMoves, board, currentPosition, currentRow, currentColumn + 1); // R
        addMove(validMoves, board, currentPosition, currentRow, currentColumn - 1); // L
        addMove(validMoves, board, currentPosition, currentRow + 1, currentColumn + 1); // Diagonal: DR
        addMove(validMoves, board, currentPosition,currentRow + 1, currentColumn - 1); // Diagonal: DL
        addMove(validMoves, board, currentPosition,currentRow - 1, currentColumn + 1); // Diagonal: UR
        addMove(validMoves, board, currentPosition,currentRow - 1, currentColumn - 1); // Diagonal: UL

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



