package chess;
import java.util.Collection;
import java.util.HashSet;

public class RookMoveSet {
    public Collection<ChessMove> getMoves(ChessBoard board, ChessPosition currentPosition, ChessGame.TeamColor pieceColor) {
        Collection<ChessMove> correctMoves = new HashSet<>();

        int currentRow = currentPosition.getRow();
        int currentColumn = currentPosition.getColumn();

        addStraightMoves(correctMoves, board, currentRow, currentColumn, 1, 0, pieceColor); // Left
        addStraightMoves(correctMoves, board, currentRow, currentColumn, -1, 0, pieceColor); // Right
        addStraightMoves(correctMoves, board, currentRow, currentColumn, 0, 1, pieceColor); // Up
        addStraightMoves(correctMoves, board, currentRow, currentColumn, 0, -1, pieceColor); // Down

        return correctMoves;
    }

    private static void addStraightMoves(Collection<ChessMove> correctMoves, ChessBoard board, int currentRow, int currentColumn, int rowDirection, int colDirection, ChessGame.TeamColor color) {
        int newRow = currentRow + rowDirection;
        int newColumn = currentColumn + colDirection;

        while (ChessBoard.isCorrectPosition(newRow, newColumn)) {
            ChessPiece pieceAtNewPosition = board.getPiece(new ChessPosition(newRow, newColumn));

            if (pieceAtNewPosition == null) {
                // Add the move when the target square is empty
                correctMoves.add(new ChessMove(new ChessPosition(currentRow, currentColumn),
                        new ChessPosition(newRow, newColumn), null));
            } else if (pieceAtNewPosition.getTeamColor() != color) {
                // Add the move when there is an opponent's piece
                correctMoves.add(new ChessMove(new ChessPosition(currentRow, currentColumn),
                        new ChessPosition(newRow, newColumn), null));
                break;
            } else {break;}


            newRow += rowDirection;
            newColumn += colDirection;
        }
    }
}
