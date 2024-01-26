package chess;
import java.util.Collection;
import java.util.HashSet;

public class PawnMoveSet {

    public static Collection<ChessMove> getValidMoves(ChessBoard board, ChessPosition currentPosition, ChessGame.TeamColor pieceColor) {
        HashSet<ChessMove> validMoves = new HashSet<>();

        // Pawn Up or Down? Black or White
        int direction = (board.getPiece(currentPosition).getTeamColor()==ChessGame.TeamColor.WHITE) ? 1:-1;
        // boolean direction = (board.getPiece(currentPosition).getTeamColor()==ChessGame.TeamColor.WHITE) || (board.getPiece(currentPosition).getTeamColor()==ChessGame.TeamColor.BLACK);

        // Normal Pawn Movement (single move upward)
        ChessPosition singleMove = new ChessPosition(currentPosition.getRow() + direction, currentPosition.getColumn());
        if (ChessBoard.isValidPosition(singleMove.getRow(), singleMove.getColumn()) && board.getPiece(singleMove)==null) {
            if ((currentPosition.getRow() == 7 && direction == 1) || (currentPosition.getRow() == 2 && direction == -1)) {
                // Pawn Promotion
                validMoves.add(new ChessMove(currentPosition, singleMove, ChessPiece.PieceType.QUEEN));
                validMoves.add(new ChessMove(currentPosition, singleMove, ChessPiece.PieceType.BISHOP));
                validMoves.add(new ChessMove(currentPosition, singleMove, ChessPiece.PieceType.ROOK));
                validMoves.add(new ChessMove(currentPosition, singleMove, ChessPiece.PieceType.KNIGHT));
            }
            else {
                validMoves.add(new ChessMove(currentPosition, singleMove, null));
            }
        }

        // Pawn can capture pieces DL or DR
        for (int colOffset = -1; colOffset <= 1; colOffset += 2) {
            ChessPosition captureMove = new ChessPosition(currentPosition.getRow() + direction, currentPosition.getColumn() + colOffset);

            if (ChessBoard.isValidPosition(captureMove.getRow(),captureMove.getColumn()) && board.getPiece(captureMove) != null
                   && board.getPiece(captureMove).getTeamColor() != board.getPiece(currentPosition).getTeamColor()) {
                if ((captureMove.getRow() == 8) || (captureMove.getRow() == 1)) {
                    // Pawn Promotion
                    validMoves.add(new ChessMove(currentPosition, captureMove, ChessPiece.PieceType.QUEEN));
                    validMoves.add(new ChessMove(currentPosition, captureMove, ChessPiece.PieceType.BISHOP));
                    validMoves.add(new ChessMove(currentPosition, captureMove, ChessPiece.PieceType.ROOK));
                    validMoves.add(new ChessMove(currentPosition, captureMove, ChessPiece.PieceType.KNIGHT));
                } else {
                    validMoves.add(new ChessMove(currentPosition, captureMove, null));
                }
            }

        }

        // Pawns can go double if first turn
        if ((currentPosition.getRow() == 2 && direction == 1) || (currentPosition.getRow() == 7 && direction == -1)) {
            ChessPosition doubleMove = new ChessPosition(currentPosition.getRow() + 2 * direction, currentPosition.getColumn());

            if (ChessBoard.isValidPosition(doubleMove.getRow(), doubleMove.getColumn()) && board.getPiece(doubleMove) == null) {
                if (board.getPiece(new ChessPosition(doubleMove.getRow() - direction, doubleMove.getColumn())) == null) {
                validMoves.add(new ChessMove(currentPosition, doubleMove, null));}
            }

        }
        return validMoves;
    }
}
