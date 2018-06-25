package br.ufrpe.summarization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.ufrpe.nlptoolkit.models.Document;
import br.ufrpe.nlptoolkit.models.Sentence;
import br.ufrpe.nlptoolkit.models.Token;

public class MapConceptsSentenceBuilder {

	public HashMap<Token, ArrayList<Sentence>> build(Document document,
			List<Token> tokens) {

		HashMap<Token, ArrayList<Sentence>> mapConceptsSentences = new HashMap<>();
		ArrayList<Sentence> tokenSentences;

		for (Token token : tokens) {

			tokenSentences = new ArrayList<>();

			for (Sentence sentence : document.getSentences()) {
				String content = sentence.getContent();

				if (content.contains(token.getString())) {
					tokenSentences.add(sentence);
				}
			}

			mapConceptsSentences.put(token, tokenSentences);

		}

		return mapConceptsSentences;
	}

}
