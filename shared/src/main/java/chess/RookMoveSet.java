package chess;
import java.util.Collection;
import java.util.HashSet;

public class RookMoveSet {
    public Collection<ChessMove> getValidMoves(ChessBoard board, ChessPosition currentPosition, ChessGame.TeamColor pieceColor) {
        Collection<ChessMove> validMoves = new HashSet<>();

        int currentRow = currentPosition.getRow();
        int currentColumn = currentPosition.getColumn();

        addStraightMoves(validMoves, board, currentRow, currentColumn, 1, 0, pieceColor); // Left
        addStraightMoves(validMoves, board, currentRow, currentColumn, -1, 0, pieceColor); // Right
        addStraightMoves(validMoves, board, currentRow, currentColumn, 0, 1, pieceColor); // Up
        addStraightMoves(validMoves, board, currentRow, currentColumn, 0, -1, pieceColor); // Down

        return validMoves;
    }

    private static void addStraightMoves(Collection<ChessMove> validMoves, ChessBoard board, int currentRow, int currentColumn, int rowDirection, int colDirection, ChessGame.TeamColor color) {
        int newRow = currentRow + rowDirection;
        int newColumn = currentColumn + colDirection;

        while (ChessBoard.isValidPosition(newRow, newColumn)) {
            ChessPiece pieceAtNewPosition = board.getPiece(new ChessPosition(newRow, newColumn));

            if (pieceAtNewPosition == null) {
                // Add the move when the target square is empty
                validMoves.add(new ChessMove(new ChessPosition(currentRow, currentColumn),
                        new ChessPosition(newRow, newColumn), null));
            } else if (pieceAtNewPosition.getTeamColor() != color) {
                // Add the move when there is an opponent's piece
                validMoves.add(new ChessMove(new ChessPosition(currentRow, currentColumn),
                        new ChessPosition(newRow, newColumn), null));
                break;
            } else {break;}


            newRow += rowDirection;
            newColumn += colDirection;
        }
    }
}
