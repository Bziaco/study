package study.effective_java_3E.item05;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class SpellChecker {
	private final Lexicon dictionary;
	
	public SpellChecker(Supplier<Lexicon> dictionary) {
		this.dictionary = Objects.requireNonNull(dictionary).get();
	}
	
	public boolean isValid(String word) {
		throw new UnsupportedOperationException();
	}
	
	public List<String> suggestions(String typo) {
		throw new UnsupportedOperationException();
	}
	
	public static void main(String[] args) {
		Lexicon lexicon = new KoreanDictionary();
		//Lexicon lexicon = new EglishDictionary();
		
		SpellChecker spellChecker = new SpellChecker(() -> lexicon);
		spellChecker.isValid("hello");
	}
	
	
//	private final Lexicon dictionary;
//	
//	public SpellChecker(Lexicon dictionary) {
//		this.dictionary = Objects.requireNonNull(dictionary);
//	}
//	
//	public boolean isValid(String word) {
//		throw new UnsupportedOperationException();
//	}
//	
//	public List<String> suggestions(String typo) {
//		throw new UnsupportedOperationException();
//	}
//	
//	public static void main(String[] args) {
//		Lexicon lexicon = new KoreanDictionary();
//		//Lexicon lexicon = new EglishDictionary();
//		
//		SpellChecker spellChecker = new SpellChecker(lexicon);
//		spellChecker.isValid("hello");
//	}
}

interface Lexicon{}

class KoreanDictionary implements Lexicon{}

class EglishDictionary implements Lexicon{}
