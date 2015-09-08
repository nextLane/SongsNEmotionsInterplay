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

import synesketch.emotion.EmotionalState;
import synesketch.emotion.Empathyscope;


public class SentSense {
	
	private static String fileName = "test/test/inputBaseline/surprised.txt";
	
	private static String outputFileName = "test/test/outputBaseline/resultsSurpriseBaseline.txt";

	private static List<EmotionalState> sents = new ArrayList<EmotionalState>();
	
	private static PrintWriter output;

	public static void main(String[] args) throws Exception {
		String line = "";
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		File outputFile = new File(outputFileName);
		output = new PrintWriter(new FileOutputStream(outputFile));
		do {
			line = in.readLine();
			if (line == null) break;
			if (line.contains("As much as I hated Otoya through the first half of the series, I love him to pieces now.")) {
				line = "As much as I hated Otoya through the first half of the series, I love him to pieces now.";
			}
			
			EmotionalState sentenceState = Empathyscope.getInstance().feel(line);
			
			writeSentenceState(sentenceState);
			computeAccuracy(sentenceState);


			sents.add(sentenceState);
		} while (line != null);
		in.close();
		
		System.out.println("total: " + totalSentenceCount);
		System.out.println("accurate: " + accurateSentenceCount);
		double acc = accurateSentenceCount/totalSentenceCount;
		System.out.println("ACCURACY: " + acc);
	}
	
	public static void writeSentenceState(EmotionalState arg) throws Exception {
		double h = arg.getHappinessWeight();
		double sd = arg.getSadnessWeight();
		double a = arg.getAngerWeight();
		double f = arg.getFearWeight();
		double d = arg.getDisgustWeight();
		double su = arg.getSurpriseWeight();
		
		//int v = arg.getValence();
		output.println(h + " " + sd + " " + a + " " + f + " " + d + " " + su);
		output.flush();
	}
	
	private static double totalSentenceCount = 0;
	private static double accurateSentenceCount = 0;

	public static void computeAccuracy(EmotionalState arg) throws Exception {
		double[] state = new double[6];
		state[0] = arg.getHappinessWeight();
		state[1] = arg.getSadnessWeight();
		state[2] = arg.getAngerWeight();
		state[3] = arg.getFearWeight();
		state[4] = arg.getDisgustWeight();
		state[5] = arg.getSurpriseWeight();
		
		totalSentenceCount++;
		if (state[5] == state[getMaxIndex(state)]) {
			accurateSentenceCount++;
		}
	}
	
	public static int getMaxIndex(double[] arg) {
		int value = 0;
		for (int i = 0; i < arg.length; i++) {
			if (arg[i] > arg[value]) {
				value = i;
			}
		}
		return value;
	}
	
}
