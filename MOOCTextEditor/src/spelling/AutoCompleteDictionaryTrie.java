package spelling;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author UC San Diego Intermediate Programming MOOC Team
 * @author Vu Nguyen
 * Date: Mar 16, 2016
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
		size = 0;
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
		TrieNode curr = root;
		char[] chars = word.toLowerCase().toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if (curr.getChild(chars[i]) == null) {
				curr.insert(chars[i]);
			}
			curr = curr.getChild(chars[i]);
		}
		if (!curr.endsWord()) {
			curr.setEndsWord(true);
			size++;
			return true;
		}

	    return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		TrieNode curr = root;
		char[] chars = s.toLowerCase().toCharArray();
		
		for (int i = 0; i < chars.length; i++) {
			if (curr.getChild(chars[i]) != null) {
				curr = curr.getChild(chars[i]);
			} else {
				return false;
			}
		}
		
		if (curr.endsWord()) {
			return true;
		}
		return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
    	 List<String> returns = new ArrayList<String>();
    	 if (numCompletions == 0) return returns;
    	 
    	 TrieNode curr = root;
    	 char[] chars = prefix.toLowerCase().toCharArray();
    	 LinkedList<TrieNode> queue;
    	 
    	 for (int i = 0; i < chars.length; i++) {
    		 if (curr.getChild(chars[i]) != null) {
    			 curr = curr.getChild(chars[i]);
    		 } else {
    			 return returns; // empty list
    		 }
    	 }

    	 queue = new LinkedList<TrieNode>();
    	 queue.add(curr);
    	 
    	 // at end of stem, what next?!
    	 while (queue.size() > 0 && returns.size() < numCompletions) {
    		TrieNode n = queue.pop();
    		// append n's children to the end of queue
    		if (n.getChildren().size() > 0) { queue.addAll(n.getChildren()); }
    		
    		// now if n is a word, add it to returns
    		if (n.endsWord()) {
    			returns.add(n.getText());
    		}
    	 }
    	  
    	 return returns;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
	
}