package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
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
		// TODO: Implement this method
		// First, break into words, or else this is not going to be manageable
		ArrayList<String> words = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile("[a-zA-Z]+");
		Matcher m = tokSplitter.matcher(sourceText);			
		while (m.find()) {
			words.add(m.group());
		}
		if(words.size()>0){
			starter = words.remove(0);
			ListNode prevWord = new ListNode(starter);
			wordList.add(prevWord);
			Boolean inList = false;
			for(String w:words){
				prevWord.addNextWord(w);
				inList=false;
				for(ListNode node : wordList){
					if(node.getWord().equalsIgnoreCase(w)){
						prevWord = node;
						inList=true;
					}
				}
				if(!inList){
					prevWord = new ListNode(w);
					wordList.add(prevWord);
				}
			}
			prevWord.addNextWord(starter);
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		/*set "currWord" to be the starter word
		set "output" to be ""
		add "currWord" to output
		while you need more words
		   find the "node" corresponding to "currWord" in the list
		   select a random word "w" from the "wordList" for "node"
		   add "w" to the "output"
		   set "currWord" to be "w" 
		   increment number of words added to the list*/
		String output = "";
		if(wordList.size()>0 && numWords>0){
			ListNode currWord = wordList.get(0);
			String nextWord = "";
			int addedWords = 0;
			Random generator = new Random();
			output = currWord.getWord();
			addedWords += 1;
			while(addedWords<numWords){
				nextWord = currWord.getRandomNextWord(generator);
				for(ListNode node : wordList){
					if(node.getWord().equalsIgnoreCase(nextWord)){
						currWord = node;
						break;
					}
				}
				output = output + " " + currWord.getWord();
				addedWords +=1;
			}
		}
		return output;
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
		train(sourceText);
		// TODO: Implement this method.
	}
	
	// TODO: Add any private helper methods you need here.
	
	
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
		int index = 0;
		if(nextWords.size()>1){
			index = generator.nextInt((nextWords.size()-1));
		}
	    return nextWords.get(index);
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


