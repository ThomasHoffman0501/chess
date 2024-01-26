package chess;
import java.util.Collection;
import java.util.HashSet;
public class BishopMoveSet {

    public static Collection<ChessMove> getMoves(ChessBoard board, ChessPosition currentPosition, ChessGame.TeamColor pieceColor) {
        HashSet<ChessMove> correctMoves = new HashSet <>();

        int currentRow = currentPosition.getRow();
        int currentColumn = currentPosition.getColumn();

        addDiagonalMoves(correctMoves, board, currentRow, currentColumn, 1, 1, pieceColor); // Diagonal: DR
        addDiagonalMoves(correctMoves, board, currentRow, currentColumn, -1, 1, pieceColor); // Diagonal: UR
        addDiagonalMoves(correctMoves, board, currentRow, currentColumn, 1, -1, pieceColor); // Diagonal: DL
        addDiagonalMoves(correctMoves, board, currentRow, currentColumn, -1, -1, pieceColor); // Diagonal: UL

        return correctMoves;
    }

    private static void addDiagonalMoves(Collection<ChessMove> correctMoves, ChessBoard board, int currentRow, int currentColumn, int rowDirection, int colDirection, ChessGame.TeamColor color) {
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
