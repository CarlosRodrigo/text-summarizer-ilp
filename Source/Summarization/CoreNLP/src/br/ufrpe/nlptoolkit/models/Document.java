package br.ufrpe.nlptoolkit.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "document")
public class Document {

	@XmlAttribute
	private int id;

	@XmlAttribute
	private String name;

	@XmlElementWrapper
	@XmlElement(name = "coreference", type = Coreference.class)
	private List<Coreference> coreferences;

	@XmlElementWrapper
	@XmlElement(name = "sentence", type = Sentence.class)
	private List<Sentence> sentences;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Coreference> getCoreferences() {
		return coreferences;
	}

	public void setCoreferences(List<Coreference> coreferences) {
		this.coreferences = coreferences;
	}

	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}

	public List<Token> getAllTokens() {

		List<Token> tokens = new ArrayList<Token>();

		for (Sentence sentence : getSentences()) {
			for (Token token : sentence.getTokens()) {
				tokens.add(token);
			}
		}

		return tokens;
	}

	public String getTokensString() {
		StringBuilder sb = new StringBuilder();

		for (Sentence sentence : getSentences()) {
			for (Token tokens : sentence.getTokens()) {
				sb.append(tokens.getString() + " ");
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public void RemoveInvalidTokens() {
		for (Sentence sentence : sentences) {

			Iterator<Token> i = sentence.getTokens().iterator();
			while (i.hasNext()) {
				Token s = i.next(); // must be called before i.remove()

				if (!s.valid)
					i.remove();
			}
		}
	}

}
