package com.tap.calculator.gateway.util.jaxb.operations;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement(name = "DivideResponse", namespace = "http://tempuri.org/")
public class DivideResponse {
	private int divideResult;

	public DivideResponse() {
	}

	public DivideResponse(int divideResult) {
		super();

		this.divideResult = divideResult;
	}

	@XmlElement(name="DivideResult")
	public int getDivResult() {
		return divideResult;
	}

	public void setDivResult(int divideResult) {
		this.divideResult = divideResult;
	}

}
