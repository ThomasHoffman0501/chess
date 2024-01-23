package chess;

import java.util.Collection;
import java.util.HashSet;
public class BishopMoveSet {
    public static Collection<ChessMove> getValidMoves(ChessBoard board, ChessPosition currentPosition) {
        Collection<ChessMove> validMoves = new HashSet <>();

        int currentRow = currentPosition.getRow();
        int currentColumn = currentPosition.getColumn();

        addDiagonalMoves(validMoves, board, currentRow, currentColumn, 1, 1);
        addDiagonalMoves(validMoves, board, currentRow, currentColumn, -1, 1);
        addDiagonalMoves(validMoves, board, currentRow, currentColumn, 1, -1);
        addDiagonalMoves(validMoves, board, currentRow, currentColumn, -1, -1);

        return validMoves;
    }

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
}
