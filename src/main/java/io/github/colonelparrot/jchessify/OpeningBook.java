package io.github.colonelparrot.jchessify;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Top-level API for reading from the opening book <br />
 * Lines beginning with '#' are regarded as comments <br />
 * Each entry must be on a new line, moves in SAN format seperated by space
 * <br /><br />
 * See the default opening book as an example
 * 
 * @version 1.0.0
 */
public class OpeningBook {
	BufferedReader br;
	FileInputStream fIn;

	/**
	 * Creates the opening book
	 * @param path path of the opening book
	 */
	public OpeningBook(String path) {
		try {
			FileInputStream fIn = new FileInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(fIn));
			this.br = br;
			this.fIn = fIn;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * free the resources used
	 */
	public void close() {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Finds the next best move, according to the opening book
	 * @param san the SAN representation of the game
	 * @return a String containing the SAN move, or null if no move was found
	 */
	public String findNextMove(String san) {
		try {
			fIn.getChannel().position(0);
			
			String line = null;
			while ((line = br.readLine()) != null) {
				
				if(line.startsWith("#")) {
					continue;
				}
				
				if (line.startsWith(san)) {
					
					String remaining = line.substring(san.length());
					String nextMove = remaining.trim().split(" ")[0];
					return nextMove;
				}
			}
			
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
