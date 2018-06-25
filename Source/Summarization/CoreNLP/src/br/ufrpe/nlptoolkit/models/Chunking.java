package br.ufrpe.nlptoolkit.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class Chunking {

	@XmlAttribute
	private int id;

	@XmlAttribute
	private String string;

	@XmlAttribute
	private String type;

	@XmlElementWrapper
	@XmlElement(name = "token", type = Token.class)
	private List<Token> tokens;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
