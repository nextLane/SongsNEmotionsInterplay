/**
 * @author Uros Krcadinac
 * Oct 8, 2009
 * @version 0.1
 */
package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import synesketch.emotion.EmotionalState;
import synesketch.emotion.Empathyscope;


public class SentSenseRandom {
	
	private static String fileName = "test/test/sentences.txt";
	
	private static List<EmotionalState> sents = new ArrayList<EmotionalState>();
	
	private static String outputFileName = "test/test/resultsRandom.txt";
	
	private static PrintWriter output;
	
	private static int n = 6;
	
	private static int m = 149;
	
	private static double[][] rndm = new double[n][m];

	public static void main(String[] args) throws Exception {
		String line = "";
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		File outputFile = new File(outputFileName);
		output = new PrintWriter(new FileOutputStream(outputFile));
		fillRandom();
		do {
			line = in.readLine();
			if (line == null) break;
			if (line.contains("As much as I hated Otoya through the first half of the series, I love him to pieces now.")) {
				line = "As much as I hated Otoya through the first half of the series, I love him to pieces now.";
			}
			EmotionalState sentenceState = Empathyscope.getInstance().feel(line);
			writeSentenceState(sentenceState);
			//System.out.println(sentenceState.toString("+")); 
			sents.add(sentenceState);
		} while (line != null);
		in.close();
	}
	
	public static void fillRandom() {
		for (int j = 0; j < m; j++) {
			for (int i = 0; i < n; i++) {
				rndm[i][j] = -1;
			}
		}
	}
	
	public static void writeSentenceState(EmotionalState arg) throws Exception {
		double h = arg.getHappinessWeight();
		double sd = arg.getSadnessWeight();
		double a = arg.getAngerWeight();
		double f = arg.getFearWeight();
		double d = arg.getDisgustWeight();
		double su = arg.getSurpriseWeight();
		
		Random r = new Random();
		int x;
		boolean working = true;
		while (working) {
			x = r.nextInt(150);
			if (rndm[0][x] == -1) {
				rndm[0][x] = h;
				rndm[1][x] = h;
				rndm[2][x] = h;
				rndm[3][x] = h;
				rndm[4][x] = h;
				rndm[5][x] = h;
				working = false;
			}
		}
		
		for (int j = 0; j < 149; j++) {
			
		}
		//int v = arg.getValence();
		//output.println(h + " " + sd + " " + a + " " + f + " " + d + " " + su);
		//output.flush();
	}
	
}
