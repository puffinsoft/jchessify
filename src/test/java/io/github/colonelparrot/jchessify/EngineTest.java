package io.github.colonelparrot.jchessify;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.move.Move;

/**
 * JUnit Tests for Engine <br />
 * Tests whether Engine will choose the right move
 * 
 * @version 1.0.0
 */
public class EngineTest {
	private Engine engine;

	/**
	 * Create the engine with default evaluator <br /> 
	 * Set engine max depth to 2
	 */
	@Before
	public void setUp() {
		engine = new Engine(new PieceValueEvaluator());
		engine.maxDepth = 2;
	}

	/**
	 * Test whether the engine will choose the right move <b>for white</b> <br />
	 * Since it is only evaluating piece values and the depth is set low, it should take the pawn
	 */
	@Test
	public void testWhiteBestMove() {
		
		/*
		 * rnbqkbnr
		 * ppp.pppp
		 * ........
		 * ...p....
		 * ....P...
		 * ........
		 * PPPP.PPP
		 * RNBQKBNR
		 */
		final Board chessBoard = new Board();
		chessBoard.loadFromFen("rnbqkbnr/ppp1pppp/8/3p4/4P3/8/PPPP1PPP/RNBQKBNR w KQkq - 0 2");
		
		final Move bestMove = this.engine.getBestMove(chessBoard);
		assertEquals(bestMove.toString(), "e4d5");
	}
	
	/**
	 * Test whether the engine will choose the right move <b>for black</b> <br />
	 * Since it is only evaluating piece values and the depth is set low, it should take the pawn
	 */
	@Test
	public void testBlackBestMove() {
		
		/*
		 * rnbqkbnr
		 * ppp.pppp
		 * ........
		 * ...p....
		 * ....P...
		 * ...P....
		 * PPP..PPP
		 * RNBQKBNR
		 */
		final Board chessBoard = new Board();
		chessBoard.loadFromFen("rnbqkbnr/ppp1pppp/8/3p4/4P3/3P4/PPP2PPP/RNBQKBNR b KQkq - 0 2");
		
		final Move bestMove = this.engine.getBestMove(chessBoard);
		assertEquals(bestMove.toString(), "d5e4");
	}

}
