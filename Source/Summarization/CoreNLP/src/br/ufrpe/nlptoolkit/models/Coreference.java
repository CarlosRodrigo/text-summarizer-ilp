package br.ufrpe.nlptoolkit.models;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sentence")
public class Coreference {

	@XmlAttribute
	private int id;

	@XmlAttribute
	private String type;

	@XmlElement
	private Referenced referenced;

	@XmlElementWrapper
	@XmlElement(name = "mention", type = Mention.class)
	private List<Mention> mentions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Referenced getReferenced() {
		return referenced;
	}

	public void setReferenced(Referenced referenced) {
		this.referenced = referenced;
	}

	public List<Mention> getMentions() {
		return mentions;
	}

	public void setMentions(List<Mention> mentions) {
		this.mentions = mentions;
	}

}
