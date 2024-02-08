package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    private TeamColor teamTurn;
    private ChessBoard chessBoard;
    public ChessGame() {
        this.teamTurn = TeamColor.WHITE;
        this.chessBoard = new ChessBoard();
    }

    /*
     * @return Which team's turn it is
     */

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.teamTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */

    /* public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        Collection<ChessMove> validMoves;
        //validMoves = chessBoard.getPiece(startPosition);
        chessBoard.copy();
        setBoard(chessBoard.copy());
        // for (pieceMoves(chessBoard, startPosition)
        // 1. Create a copy
        // 2. Make the move on the copy
        // 3. Check if that move results in Check (if it does, don't add it to valid Moves)
        ChessPiece piece = chessBoard.getPiece(startPosition);
        if (piece == null) {
            return null;
        }
        return piece.pieceMoves(chessBoard, startPosition);
    }
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        // 1. Create a copy
        ChessBoard copyBoard = chessBoard.copy();
        ChessGame game = new ChessGame();
        game.setBoard(copyBoard);
        // 2. Make the move on the copy
        ChessPiece piece = chessBoard.getPiece(startPosition);
        if (piece == null) {
            return null;
        }
        Collection<ChessMove> validMoves = piece.pieceMoves(copyBoard, startPosition);

        // 3. Check if that move results in Check (if it does, don't add it to valid Moves)
        validMoves.removeIf(move -> {
            ChessPosition endPosition = move.getEndPosition();
            ChessPiece capturedPiece = copyBoard.getPiece(endPosition);

            copyBoard.movePiece(startPosition,endPosition);
            // Sees if move puts the King in Check
            boolean isInCheck = game.isInCheck(piece.getTeamColor());
            // Undo move
            copyBoard.movePiece(endPosition, startPosition);

            if (capturedPiece != null) {
                copyBoard.addPiece(endPosition, capturedPiece);
            }

            return isInCheck;
        });
        return validMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */

    public void makeMove(ChessMove move) throws InvalidMoveException {
        if (move == null || move.getStartPosition() == null || move.getEndPosition() == null) {
            throw new InvalidMoveException();
        }
        ChessPosition start = move.getStartPosition();
        ChessPosition end = move.getEndPosition();

        ChessPiece piece = chessBoard.getPiece(start);
        if (piece == null || piece.getTeamColor() != teamTurn) {
            throw new InvalidMoveException();
        }
        if (!validMoves(start).contains(move)) {
            throw new InvalidMoveException();
        }
        if (piece.getPieceType() == ChessPiece.PieceType.PAWN && (end.getRow() == 1 || end.getRow() == 8)) {
            ChessPiece promotedPiece = new ChessPiece(piece.getTeamColor(), move.getPromotionPiece());
            chessBoard.movePiece(start, end, promotedPiece);
        } else {
            chessBoard.movePiece(start, end, null);
        }
        switchTurn();
    }
    private void switchTurn() {
        teamTurn = (teamTurn == TeamColor.WHITE) ? TeamColor.BLACK : TeamColor.WHITE;
    }

    /**
         * Determines if the given team is in check
         *
         * @param teamColor which team to check for check
         * @return True if the specified team is in check
         */


    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition kingPosition = findKingPosition(teamColor);
        TeamColor enemyColor = (teamColor == TeamColor.WHITE) ? TeamColor.BLACK : TeamColor.WHITE;

        // If Enemy Piece can attack King
        for (int row = 1; row < ChessBoard.numRows; row++) {
            for (int col = 1; col < ChessBoard.numRows; col++) {
                ChessPosition currentPosition = new ChessPosition(row, col);
                ChessPiece piece = chessBoard.getPiece(currentPosition);

                if (piece != null && piece.getTeamColor() == enemyColor) {
                    Collection<ChessMove> moves = piece.pieceMoves(chessBoard, currentPosition);
                    if (moves.stream().anyMatch(move -> move.getEndPosition().equals(kingPosition))) {
                        return true; // King is in Check
                    }
                }
            }
        }
        return false; // King isn't in Check
    }

    private ChessPosition findKingPosition(TeamColor teamColor) {
        for (int row = 1; row < ChessBoard.numRows; row++) {
            for (int col = 1; col < ChessBoard.numCols; col++) {
                ChessPosition currentPosition = new ChessPosition(row, col);
                ChessPiece piece = chessBoard.getPiece(currentPosition);
                if (piece != null && piece.getPieceType() == ChessPiece.PieceType.KING && piece.getTeamColor() == teamColor) {
                    return currentPosition; // Find King piece and return to king position
                }
            }
        }
        return null; // Where the King is not found
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        ChessPosition kingPosition = findKingPosition(teamColor);
        TeamColor enemyColor = (teamColor == TeamColor.WHITE) ? TeamColor.BLACK : TeamColor.WHITE;

        // If Enemy Piece can attack King
        for (int row = 1; row < ChessBoard.numRows; row++) {
            for (int col = 1; col < ChessBoard.numRows; col++) {
                ChessPosition currentPosition = new ChessPosition(row, col);
                ChessPiece piece = chessBoard.getPiece(currentPosition);

                if (piece != null && piece.getTeamColor() == enemyColor) {
                    Collection<ChessMove> moves = piece.pieceMoves(chessBoard, currentPosition);
                    if (moves.stream().anyMatch(move -> move.getEndPosition().equals(kingPosition))) {
                        return true; // King is in Check
                    }
                }

                if (piece != null && piece.getTeamColor() == teamColor) {
                    Collection<ChessMove> moves = validMoves(currentPosition);
                    // If team has any legal moves open
                    if (moves.isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return false; // King isn't in Check
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if (isInCheck(teamColor)) {
            return false; // If a player is in check, it isn't stalemate
        }

        // Checking for legal moves
        for (int row = 1; row < ChessBoard.numRows; row++) {
            for (int col = 1; col < ChessBoard.numRows; col++) {
                ChessPosition currentPosition = new ChessPosition(row, col);
                ChessPiece piece = chessBoard.getPiece(currentPosition);


                if (piece != null && piece.getTeamColor() == teamColor) {
                    Collection<ChessMove> moves = validMoves(currentPosition);
                    // If team has any legal moves open
                    if (!moves.isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true; // If team has no legal moves
    }

    @Override
    public String toString() {
        return "ChessGame{" +
                "teamTurn=" + teamTurn +
                ", chessBoard=" + chessBoard +
                '}'+
                "\n";
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.chessBoard = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return chessBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame = (ChessGame) o;
        return teamTurn == chessGame.teamTurn && Objects.equals(chessBoard, chessGame.chessBoard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamTurn, chessBoard);
    }

}
