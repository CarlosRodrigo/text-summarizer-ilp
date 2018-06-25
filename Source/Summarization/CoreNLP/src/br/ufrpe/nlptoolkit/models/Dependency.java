package br.ufrpe.nlptoolkit.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Dependency {

	@XmlAttribute
	private String type;

	@XmlElement
	private Dependent dependent;

	@XmlElement
	private Governor governor;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Dependent getDependent() {
		return dependent;
	}

	public void setDependent(Dependent dependent) {
		this.dependent = dependent;
	}

	public Governor getGovernor() {
		return governor;
	}

	public void setGovernor(Governor governor) {
		this.governor = governor;
	}

}
