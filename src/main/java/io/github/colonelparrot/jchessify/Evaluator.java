package io.github.colonelparrot.jchessify;

import com.github.bhlangonijr.chesslib.Board;

/**
 * Evaluator for jChessify chess engine
 */
public interface Evaluator {
	/**
	 * Advantage score for the current player to play <br />
	 * A <b>positive score</b> means an advantage <br />
	 * A <b>negative score</b> means a disadvantage
	 * 
	 * @param board board to evaluate
	 * @return advantage score
	 */
	int evaluate(Board board);
}
