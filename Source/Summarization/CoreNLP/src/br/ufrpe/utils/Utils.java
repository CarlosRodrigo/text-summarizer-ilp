package br.ufrpe.utils;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.nlptoolkit.models.Sentence;
import br.ufrpe.nlptoolkit.models.Token;

public class Utils {

	public static int getSentenceSize(Sentence sentence) {
		return sentence.getContent().split(" ").length;
	}

	public static ArrayList<Token> normalize(List<Token> tokens) {

		double max = tokens.get(0).getScore().intValue();
		double min = tokens.get(0).getScore().intValue();
		for (Token token : tokens) {
			if (token.getScore().intValue() > max) {
				max = token.getScore().intValue();
			}
			if (token.getScore().intValue() < min) {
				min = token.getScore().intValue();
			}
		}

		for (Token token : tokens) {
			double value = token.getScore().intValue();
			double normalizedValue = (value - min) / (max - min);

			token.setScore(normalizedValue);
		}

		return (ArrayList<Token>) tokens;

	}

	public static List<Sentence> normalizeSentences(List<Sentence> sentences) {

		double max = sentences.get(0).getCohesion().intValue();
		double min = sentences.get(0).getCohesion().intValue();
		for (Sentence sentence : sentences) {
			if (sentence.getCohesion().intValue() > max) {
				max = sentence.getCohesion().intValue();
			}
			if (sentence.getCohesion().intValue() < min) {
				min = sentence.getCohesion().intValue();
			}
		}

		for (Sentence sentence : sentences) {
			double value = sentence.getCohesion().intValue();
			double normalizedValue = (value - min) / (max - min);

			if (normalizedValue == 0) {
				normalizedValue = 0.05;
			}

			sentence.setCohesion(normalizedValue);
		}

		return sentences;
	}

}
