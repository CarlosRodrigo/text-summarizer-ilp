package br.ufrpe.algorithms.scoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.ufrpe.nlptoolkit.models.Document;
import br.ufrpe.nlptoolkit.models.Sentence;
import br.ufrpe.nlptoolkit.models.Token;

public class TF_ISF implements IScoreAlgorithm {

	public List<Token> calculate(Document document, List<Token> tokens) {
		return calculate(document, tokens, 1);
	}

	public List<Token> calculate(Document document, List<Token> tokens,
			int theta) {

		List<Token> t = new ArrayList<Token>();

		List<List<String>> s = new ArrayList<List<String>>();
		for (Sentence sentence : document.getSentences()) {
			List<String> strlocal = new ArrayList<String>();
			for (Token tt : sentence.getTokens()) {
				strlocal.add(tt.getString());
			}
			s.add(strlocal);
		}

		for (Token token : tokens) {

			if (!token.isStopWord() && !t.contains(token)) {

				double value = 0;

				for (Sentence sentence : document.getSentences()) {
					String content = sentence.getContent();

					if (content.contains(token.getString())) {
						TF_ISF calculator = new TF_ISF();
						value = calculator.tfIdf(
								Arrays.asList(sentence.getContent().split(
										"\\s+")), s, token.getString());
					}
				}

				token.setScore(value);
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

	public double tf(List<String> doc, String term) {
		double result = 0;
		for (String word : doc) {
			if (term.equalsIgnoreCase(word))
				result++;
		}
		return result / doc.size();
	}

	public double idf(List<List<String>> docs, String term) {
		double n = 0;
		for (List<String> doc : docs) {
			for (String word : doc) {
				if (term.equalsIgnoreCase(word)) {
					n++;
					break;
				}
			}
		}
		if (n == 0) {
			return 0;
		}
		return Math.log(docs.size() / n);
	}

	public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
		double tf = tf(doc, term);
		double idf = idf(docs, term);
		return tf * idf;
	}

}
