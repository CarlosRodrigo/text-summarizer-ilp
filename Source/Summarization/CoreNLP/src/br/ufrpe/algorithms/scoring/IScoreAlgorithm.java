package br.ufrpe.algorithms.scoring;

import java.util.List;

import br.ufrpe.nlptoolkit.models.Document;
import br.ufrpe.nlptoolkit.models.Token;

public interface IScoreAlgorithm {

	public List<Token> calculate(Document document, List<Token> tokens);

	public List<Token> calculate(Document document, List<Token> tokens,
			int theta);

}
