package io.github.colonelparrot.jchessify;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.move.Move;

/**
 * jChessify core chess engine implementation
 * 
 * @version 1.0.0
 */
public class Engine {
	/**
	 * the evaluator for each position
	 */
	private Evaluator evaluator;

	Engine(Evaluator evaluator) {
		this.evaluator = evaluator;
	}

	/**
	 * Indicates the maximum depth for the minimax algorithm to search <br />
	 * <br />
	 * The greater the depth, the "smarter" the engine will be <br />
	 * Be warned that complexity increases with depth, slowing engine down <br />
	 * Always choose a good balance of depth & speed
	 */
	int maxDepth = 4;

	/**
	 * find the best move based on the side
	 * 
	 * @param board the chess board
	 * @return a Move object with the best move
	 */
	public Move getBestMove(Board board) {
		final Side side = board.getSideToMove();

		Move bestMove = null;

		int bestScore = side == Side.WHITE ? Integer.MIN_VALUE : Integer.MAX_VALUE;

		for (Move move : board.legalMoves()) {
			Board copy = new Board();
			copy.loadFromFen(board.getFen());
			copy.doMove(move);

			int score = minimax(copy, side.flip(), 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
			
			if (side == Side.WHITE) {
				if (score > bestScore) {
					bestMove = move;
					bestScore = score;
				}
			} else {
				if (score < bestScore) {
					bestMove = move;
					bestScore = score;
				}
			}
		}

		return bestMove;
	}

	/**
	 * Alpha-beta pruning recursive minimax algorithm <br />
	 * Calculates the best move to play <br />
	 * <br />
	 * MINIMAX works by going down a move tree <br />
	 * &emsp; each side must play the best move for themselves <br />
	 * &emsp; we simply find the move that, assuming both sides play their best,
	 * yields the best outcome <br />
	 * Alpha-beta pruning is used to eliminate moves from the movement tree which we
	 * know don't need to be searched
	 * 
	 * @param board the chess board
	 * @param side  the current side
	 * @param depth the search depth
	 * @param alpha the best evaluation found so far for the maximizing (white) side
	 * @param beta  the best evaluation found so far for the minimizing (black) side
	 * @return an evaluation of the board position, see comments for more technical
	 *         details
	 */
	public int minimax(Board board, Side side, int depth, int alpha, int beta) {
		if (depth == maxDepth || board.isMated() || board.isDraw()) {
			int score = this.evaluator.evaluate(board);
			if(side == Side.BLACK) {
				return -score;
			}
			return score;
		}

		int bestScore = (side == Side.WHITE) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

		for (Move move : board.legalMoves()) {
			Board copy = new Board();
			copy.loadFromFen(board.getFen());
			copy.doMove(move);

			int score = minimax(copy, side.flip(), depth + 1, alpha, beta);
			
			if (side == Side.WHITE) {
				bestScore = Math.max(bestScore, score);
				alpha = Math.max(alpha, bestScore);
			} else {
				bestScore = Math.min(bestScore, score);
				beta = Math.min(beta, bestScore);
			}

			if (beta <= alpha) {
				break;
			}
		}

		return bestScore;
	}
}
