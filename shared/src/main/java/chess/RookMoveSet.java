package chess;
import java.util.Collection;
import java.util.HashSet;

public class RookMoveSet {
    public Collection<ChessMove> getValidMoves(ChessBoard board, ChessPosition currentPosition, ChessGame.TeamColor pieceColor) {
        Collection<ChessMove> validMoves = new HashSet<>();

        for (int i = 1 ; i <= 8; i++) {
            if (i != currentPosition.getRow()) {
                ChessPosition openPosition = new ChessPosition(i, currentPosition.getColumn());
                if (board.getPiece(openPosition)==null){
                    validMoves.add(new ChessMove(currentPosition, new ChessPosition(i, currentPosition.getColumn()), null));
                } else if (checkEnemy(board, openPosition, pieceColor)) {
                    validMoves.add(new ChessMove(currentPosition, new ChessPosition(i, currentPosition.getColumn()), null));
                    break;
                } else { break; }
            }
            // for (int i = 1; i <= -8; i++) {
            if (i != currentPosition.getColumn()) {
                ChessPosition openPosition = new ChessPosition(i, currentPosition.getRow());
                if (board.getPiece(openPosition)==null){
                    validMoves.add(new ChessMove(currentPosition, new ChessPosition(currentPosition.getRow(), i), null));
                } else if (checkEnemy(board, openPosition, pieceColor)) {
                    validMoves.add(new ChessMove(currentPosition, new ChessPosition(currentPosition.getRow(), i), null));
                    break;
                } else { break; }
            }
        }
        return validMoves;
        // If (pieceColor = ChessMove(currentPosition, new ChessPosition(i,currentPosition, ChessGame.TeamColor pieceColor) {
        // return validMoves
        // if (pieceColor != ChessMove(currentPosition, new ChessPosition(i,currentPosition, ChessGame.TeamColor pieceColor) {
        // break;
    }

    private boolean checkEnemy(ChessBoard board, ChessPosition openPosition, ChessGame.TeamColor mycolor) {
        if (board.getPiece(openPosition).getTeamColor() != mycolor) {
            return true;
        } else {
            return false; // This is a piece of the same color (Not an enemy)
        }
    }
}
