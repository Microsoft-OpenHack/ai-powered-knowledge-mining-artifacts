package com.microsoft.openhack.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetTopWords {

	public static void main(String[] args) {
		// Test the function
		String test_text = "Four score and seven years ago our fathers brought forth on this continent, a new nation, conceived in Liberty, and dedicated to the proposition that all men are created equal. Now we are engaged in a great civil war, testing whether that nation, or any nation so conceived and so dedicated, can long endure. We are met on a great battlefield of that war.  We have come to dedicate a portion of that field, as a final resting place for those who here gave their lives that that nation might live. It is altogether fitting and proper that we should do this. But, in a larger sense, we can not dedicate, we can not consecrate, we can not hallow this ground. The brave men, living and dead, who struggled here, have consecrated it, far above our poor power to add or detract. The world will little note, nor long remember what we say here, but it can never forget what they did here. It is for us the living, rather, to be dedicated here to the unfinished work which they who fought here have thus far so nobly advanced. It is rather for us to be here dedicated to the great task remaining before us that from these honored dead we take increased devotion to that cause for which they gave the last full measure of devotion, that we here highly resolve that these dead shall not have died in vain; that this nation, under God, shall have a new birth of freedom and that government of the people, by the people, for the people, shall not perish from the earth.";

		word_list test_result = get_top_ten_words(test_text);

		// print the property containing the array of words
		System.out.println(test_result.words);

	}

	public static word_list get_top_ten_words(String text) {

		// split words into an array
		String[] wordsArray = text.split(" ");

		// remove any non alphabetic characters and convert all to lowercase
		Arrays.setAll(wordsArray, i -> wordsArray[i].replaceAll("[^a-zA-Z]", "").toLowerCase());

		// convert to a list for subsequent processing
		List<String> words = Arrays.asList(wordsArray);

		// Array of stop words to be ignored
		List<String> stopwords = Arrays.asList("", "i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you",
				"youre", "youve", "youll", "youd", "your", "yours", "yourself", "yourselves", "he", "him", "his",
				"himself", "she", "shes", "her", "hers", "herself", "it", "its", "itself", "they", "them", "thats",
				"their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that", "thatll", "these",
				"those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having", "do",
				"does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while",
				"of", "at", "by", "for", "with", "about", "against", "between", "into", "through", "during", "before",
				"after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under",
				"again", "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any",
				"both", "each", "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own",
				"same", "so", "than", "too", "very", "s", "t", "can", "will", "just", "don", "dont", "should",
				"shouldve", "now", "d", "ll", "m", "o", "re", "ve", "y", "ain", "aren", "arent", "couldn", "couldnt",
				"didn", "didnt", "doesn", "doesnt", "hadn", "hadnt", "hasn", "hasnt", "havent", "isn", "isnt", "ma",
				"mightn", "mightnt", "mustn", "mustnt", "needn", "neednt", "shan", "shant", "shall", "shouldn",
				"shouldnt", "wasn", "wasnt", "weren", "werent", "won", "wont", "wouldn", "wouldnt");

		// remove entries matching any stopwords from collection
		words = words.stream()
				.filter(w -> !stopwords.contains(w))
				.collect(Collectors.toList());

		// count instances of each remaining word
		Map<String, Long> map = words.stream()
				.collect(Collectors.groupingBy(w -> w, Collectors.counting()));

		// sort in descending order and take the top 10
		List<Map.Entry<String, Long>> result = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(10)
				.collect(Collectors.toList());

		// Construct object for results
		word_list json_result = new word_list();

		// collapse the map into a list of just the words...
		List<String> topTenWords = new ArrayList<String>();
		result.forEach(entry -> topTenWords.add(entry.getKey()));
		
		// ...and set the property of the return object
		json_result.words = topTenWords;

		// return the results object
		return json_result;
	}
}
