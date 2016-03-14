package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team
 * @author Vu Nguyen
 * Date: Mar 12, 2016 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		String[] tokens = sourceText.split(" ");
		// set starter to be first word, prevW to be starter
		if (starter.equals("")) {
			starter = tokens[0];
		}
		String prevW = starter;
		
		// for word from 2nd place onwards
		for (int i = 1; i < tokens.length; i++) {
			if (this.inList(prevW)) { // if prevW already a node
				this.addWord(prevW, tokens[i]); // add next token to prevW's next word list
			} else { // otherwise create new node and add next w
				wordList.add(new ListNode(prevW, tokens[i]));
			}
			
			prevW = tokens[i];
		}
		
		// add starter to be next word for the last word in source text
		if (this.inList(prevW)) { // if prevW already a node
			this.addWord(prevW, starter); // add next token to prevW's next word list
		} else { // otherwise create new node and add next w
			wordList.add(new ListNode(prevW, starter));
		}
		
		return;
	}
	
	/** Helper method: check to see if word is in list */
	private boolean inList(String w) {
		for (ListNode node : wordList) {
			if (node.getWord().equals(w)) {
				return true;
			}
		}
		
		return false;
	}
	
	/** Helper method: add nextw to w's next word list */
	private void addWord(String w, String nextw) {
		for (ListNode node : wordList) {
			if (node.getWord().equals(w)) {
				node.addNextWord(nextw);
				
				return;
			}
		}
		
		return;
	}
	
	/** Helper method: randomly get the next word */
	private String getNextWord(String w) {
		for (ListNode node : wordList) {
			if (node.getWord().equals(w)) {
				return node.getRandomNextWord(rnGenerator);
			}
		}
		
		return "";
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    String text = "";
	    String nextWord = starter;
	    
	    for (int i = 0; i < numWords; i++) {
	    	text += nextWord + " ";
	    	nextWord = getNextWord(nextWord);
	    }
		
		return text;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		this.train(sourceText);
		
		return;
	}
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	ListNode(String word, String nextw) {
		this(word);
		this.addNextWord(nextw);
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
	    return nextWords.get(generator.nextInt(nextWords.size()));
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


