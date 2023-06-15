package io.github.colonelparrot.jchessify;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Side;

/**
 * A basic example of how Evaluator works <br />
 * Uses piece values as evaluation criteria
 * 
 * @version 1.0.0
 */
public class PieceValueEvaluator implements Evaluator {
	/**
	 * Evaluates the board <br />
	 * Uses piece value as evaluation criteria
	 * <br /> <br />
	 * Piece values: <br />
	 * &emsp; pawn is worth 1 point <br />
	 * &emsp; knight &amp; bishop is worth 3 points <br />
	 * &emsp; rook is worth 5 points <br />
	 * &emsp; queen is worth 8 points <br />
	 * &emsp; king is worth 100 points
	 * 
	 * @return advantage
	 */
	public int evaluate(Board board) {
		final Side sideToMove = board.getSideToMove();

		// indices correspond to piece ordinal
		int[] pieceValues = { 1, 3, 3, 5, 8, 100 };

		int evaluation = 0;
		Piece[] pieces = board.boardToArray();

		for (int i = 0; i < 64; i++) {
			Piece piece = pieces[i];

			if (piece != Piece.NONE) {

				evaluation += pieceValues[piece.getPieceType().ordinal()]
						* (piece.getPieceSide() == sideToMove ? 1 : -1);
			}
		}

		return evaluation;
	}
}
