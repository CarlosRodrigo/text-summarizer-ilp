package br.ufrpe.algorithms;

import br.ufrpe.nlptoolkit.models.Coreference;
import br.ufrpe.nlptoolkit.models.Document;
import br.ufrpe.nlptoolkit.models.Mention;
import br.ufrpe.nlptoolkit.models.Referenced;
import br.ufrpe.nlptoolkit.models.Sentence;
import br.ufrpe.nlptoolkit.models.Token;

public class CoreferenceResolution {

	public void resolve(Document document) {

		for (Coreference coreference : document.getCoreferences()) {
			Referenced referenced = coreference.getReferenced();

			for (Mention mention : coreference.getMentions()) {
				Sentence mentionedSentence = document.getSentences().get(
						mention.getSentenceId() - 1);
				String[] mentionIds = mention.getTokenIds().split("-");

				Token token = mentionedSentence.getTokens().get(
						Integer.parseInt(mentionIds[0]) - 1);
				token.setString(referenced.getString());

				int begin = Integer.parseInt(mentionIds[0]);
				int end = Integer.parseInt(mentionIds[mentionIds.length - 1]);

				for (int i = begin; i < end; i++) {
					mentionedSentence.RemoveToken(i);
				}
			}
		}

		document.RemoveInvalidTokens();

	}

}
