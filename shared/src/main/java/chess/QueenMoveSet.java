package chess;
import java.util.Collection;
import java.util.HashSet;
public class QueenMoveSet {
    public static Collection<ChessMove> getValidMoves(ChessBoard board, ChessPosition currentPosition, ChessGame.TeamColor pieceColor) {
        HashSet<ChessMove> validMoves = new HashSet<>();

        // Rook Moves
        for (int i = 1; i <= 8; i++) {
            if (i != currentPosition.getRow()) {
                validMoves.add(new ChessMove(currentPosition, new ChessPosition(i, currentPosition.getColumn()), null));
            }
            if (i != currentPosition.getColumn()) {
                validMoves.add(new ChessMove(currentPosition, new ChessPosition(currentPosition.getRow(), i), null));
            }
        }

        // Bishop Moves
        int currentRow = currentPosition.getRow();
        int currentColumn = currentPosition.getColumn();

        addDiagonalMoves(validMoves, board, currentRow, currentColumn, 1, 1); // Diagonal: DR
        addDiagonalMoves(validMoves, board, currentRow, currentColumn, -1, 1); // Diagonal: UR
        addDiagonalMoves(validMoves, board, currentRow, currentColumn, 1, -1); // Diagonal: DL
        addDiagonalMoves(validMoves, board, currentRow, currentColumn, -1, -1); // Diagonal: UL

        return validMoves;
    }

    // Bishop MoveSet
    // Like Queen, Bishop can move to any empty square, capture the enemy's piece and then end movement.
    private static void addDiagonalMoves(Collection<ChessMove> validMoves, ChessBoard board, int currentRow, int currentColumn, int rowDirection, int colDirection) {
        int newRow = currentRow + rowDirection;
        int newColumn = currentColumn + colDirection;

        while (ChessBoard.isValidPosition(newRow,newColumn)) {
            ChessPiece pieceAtNewPosition = board.getPiece(new ChessPosition(newRow, newColumn));
            if (pieceAtNewPosition == null) {
                validMoves.add(new ChessMove(new ChessPosition(currentRow, currentColumn),
                        new ChessPosition(newRow, newColumn), null));
            } else {
                if (!pieceAtNewPosition.getTeamColor().equals(board.getPiece(new ChessPosition(currentRow, currentColumn)).getTeamColor())) {
                    validMoves.add(new ChessMove(new ChessPosition(currentRow, currentColumn),
                            new ChessPosition(newRow,newColumn),null));
                }
                break;
            }
            newRow += rowDirection;
            newColumn += colDirection;
        }
    }

private boolean checkEnemy(ChessBoard board, ChessPosition openPosition, ChessGame.TeamColor mycolor) {
    if (board.getPiece(openPosition) == null) {
        return true;
    }
    if (board.getPiece(openPosition).getTeamColor() != mycolor) {
        return true;
    } else {
        return false; // This is a piece of the same color (Not an enemy)
    }
}
}

