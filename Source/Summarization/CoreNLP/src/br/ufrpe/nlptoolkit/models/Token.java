package br.ufrpe.nlptoolkit.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Token {

	public boolean valid = true;

	private Number score;

	@XmlAttribute
	private int id;

	@XmlAttribute
	private String lemma;

	@XmlAttribute
	private String string;

	@XmlAttribute
	private String stem;

	@XmlAttribute
	private String type;

	@XmlAttribute
	private String pos;

	@XmlAttribute
	private boolean isStopWord;

	@XmlAttribute(name = "is_referenced")
	private boolean isReferenced;

	@XmlAttribute(name = "is_refers")
	private boolean isRefers;

	public Number getScore() {
		return score;
	}

	public void setScore(Number score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLemma() {
		return lemma;
	}

	public void setLemma(String lemma) {
		this.lemma = lemma;
	}

	public String getString() {
		if (valid)
			return string;
		else
			return null;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getStem() {
		return stem;
	}

	public void setStem(String stem) {
		this.stem = stem;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public boolean isStopWord() {
		return isStopWord;
	}

	public void setStopWord(boolean isStopWord) {
		this.isStopWord = isStopWord;
	}

	public boolean isReferenced() {
		return isReferenced;
	}

	public void setReferenced(boolean isReferenced) {
		this.isReferenced = isReferenced;
	}

	public boolean isRefers() {
		return isRefers;
	}

	public void setRefers(boolean isRefers) {
		this.isRefers = isRefers;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Token) {
			return getString().equals(((Token) obj).getString());
		}

		return false;
	}

	@Override
	public String toString() {
		return "Token_" + getId() + ": " + getString();
	}

}
