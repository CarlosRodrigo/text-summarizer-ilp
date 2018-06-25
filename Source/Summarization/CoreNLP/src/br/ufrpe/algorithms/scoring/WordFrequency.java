package br.ufrpe.algorithms.scoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.ufrpe.nlptoolkit.models.Document;
import br.ufrpe.nlptoolkit.models.Sentence;
import br.ufrpe.nlptoolkit.models.Token;

public class WordFrequency implements IScoreAlgorithm {

	public List<Token> calculate(Document document, List<Token> tokens) {
		return calculate(document, tokens, 1);
	}

	public List<Token> calculate(Document document, List<Token> tokens,
			int theta) {

		List<Token> t = new ArrayList<Token>();

		for (Token token : tokens) {

			if (!token.isStopWord() && !t.contains(token)) {

				double value = 0;

				for (Sentence sentence : document.getSentences()) {
					String content = sentence.getContent();

					if (content.contains(token.getString())) {
						value++;
					}
				}

				token.setScore(value / document.getSentences().size());
				t.add(token);
			}
		}

		Collections.sort(t, new Comparator<Token>() {
			@Override
			public int compare(Token o1, Token o2) {
				return o2.getScore().toString()
						.compareTo(o1.getScore().toString());
			}
		});

		return new ArrayList<Token>(t.subList(0, t.size() / theta));

	}

}
