package br.ufrpe.algorithms;

import java.util.HashMap;

import br.ufrpe.nlptoolkit.models.Dependency;
import br.ufrpe.nlptoolkit.models.Document;
import br.ufrpe.nlptoolkit.models.Sentence;
import br.ufrpe.nlptoolkit.models.Token;

public class DependencyAnalyzer {

	private static String TYPE_NSUBJ = "nsubj";
	private static String TYPE_NSUBJPASS = "nsubjpass";
	private static String TYPE_DOBJ = "dobj";
	private static String TYPE_IOBJ = "iobj";

	public HashMap<String, Integer> dictionary;

	public void Analyze(Document document) {
		for (Sentence sentence : document.getSentences()) {
			if (sentence != null && sentence.getDependencies() != null) {
				dictionary = new HashMap<String, Integer>();

				for (Dependency dependency : sentence.getDependencies()) {
					String dependent = dependency.getDependent().getContent();
					int value = 1;

					if (dependency.getType().equals(TYPE_NSUBJ)
							|| dependency.getType().equals(TYPE_NSUBJPASS)) {
						value = 3;

					} else if (dependency.getType().equals(TYPE_DOBJ)
							|| dependency.getType().equals(TYPE_IOBJ)) {
						value = 2;
					}

					dictionary.putIfAbsent(dependent, value);
				}

				for (Token token : sentence.getTokens()) {
					if (token.getScore() != null
							&& dictionary.containsKey(token.getString())) {
						int value = dictionary.get(token.getString());
						token.setScore(token.getScore().doubleValue() * value);
					}
				}
			}
		}

	}

}
