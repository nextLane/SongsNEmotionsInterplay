/**
 * @author Uros Krcadinac
 * 18-Sep-10
 * @version 0.1
 */
package test;

import java.io.IOException;

import synesketch.emotion.EmotionalState;
import synesketch.emotion.Empathyscope;

public class TestSentence {
	
	public static void main(String[] args) throws IOException {
		String line = "";
		EmotionalState sentenceState = Empathyscope.getInstance().feel(line);
		//sentenceState.
	}

}
