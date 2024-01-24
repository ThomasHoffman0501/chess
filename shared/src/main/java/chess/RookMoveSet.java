package chess;

import java.util.Collection;
import java.util.HashSet;
public class RookMoveSet {
    public static Collection<ChessMove> getValidMoves(ChessBoard board, ChessPosition currentPosition) {
        Collection<ChessMove> validMoves = new HashSet<>();

        for (int i = 1; i <= 8; i++) {
            if (i != currentPosition.getRow()) {
                validMoves.add(new ChessMove(currentPosition, new ChessPosition(i, currentPosition.getColumn()), null));
            }
            if (i != currentPosition.getColumn()) {
                validMoves.add(new ChessMove(currentPosition, new ChessPosition(currentPosition.getRow(), i), null));
            }
        }
        return validMoves;
    }
}
