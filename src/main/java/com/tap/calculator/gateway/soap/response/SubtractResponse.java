package com.tap.calculator.gateway.soap.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement(name = "SubtractResponse", namespace = "http://tempuri.org/")
public class SubtractResponse {
	private int subtractResult;

	public SubtractResponse() {
	}

	public SubtractResponse(int subtractResult) {
		super();

		this.subtractResult = subtractResult;
	}

	@XmlElement(name="SubtractResult")
	public int getSubResult() {
		return subtractResult;
	}

	public void setSubResult(int subtractResult) {
		this.subtractResult = subtractResult;
	}

}
