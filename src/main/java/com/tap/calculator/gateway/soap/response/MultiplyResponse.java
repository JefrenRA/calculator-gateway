package com.tap.calculator.gateway.soap.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement(name = "MultiplyResponse", namespace = "http://tempuri.org/")
public class MultiplyResponse {
	private int multiplyResult;

	public MultiplyResponse() {
	}

	public MultiplyResponse(int multiplyResult) {
		super();

		this.multiplyResult = multiplyResult;
	}

	@XmlElement(name="MultiplyResult")
	public int getMulResult() {
		return multiplyResult;
	}

	public void setMulResult(int multiplyResult) {
		this.multiplyResult = multiplyResult;
	}

}

