package com.tap.calculator.gateway.soap.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement(name = "AddResponse", namespace = "http://tempuri.org/")
public class AddResponse {
	private int addResult;

	public AddResponse() {
	}

	public AddResponse(int addResult) {
		super();

		this.addResult = addResult;
	}

	@XmlElement(name="AddResult")
	public int getAddResult() {
		return addResult;
	}

	public void setAddResult(int addResult) {
		this.addResult = addResult;
	}

}
