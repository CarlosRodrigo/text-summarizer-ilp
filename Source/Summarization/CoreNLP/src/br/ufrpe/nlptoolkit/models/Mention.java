package br.ufrpe.nlptoolkit.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Mention {

	@XmlAttribute(name = "ids_tokens")
	private String tokenIds;

	@XmlAttribute
	private String string;

	@XmlAttribute(name = "id_sentence")
	private int sentenceId;

	public String getTokenIds() {
		return tokenIds;
	}

	public void setTokenIds(String ids_tokens) {
		this.tokenIds = ids_tokens;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public int getSentenceId() {
		return sentenceId;
	}

	public void setSentenceId(int sentenceId) {
		this.sentenceId = sentenceId;
	}

}
