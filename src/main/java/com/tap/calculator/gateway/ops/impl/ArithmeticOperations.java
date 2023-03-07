package com.tap.calculator.gateway.ops.impl;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.tap.calculator.gateway.ops.Operations;
import com.tap.calculator.gateway.soap.response.AddResponse;
import com.tap.calculator.gateway.soap.response.DivideResponse;
import com.tap.calculator.gateway.soap.response.MultiplyResponse;
import com.tap.calculator.gateway.soap.response.SubtractResponse;
import com.tap.calculator.gateway.soap.util.SOAPOperationsXml;

@SuppressWarnings("restriction")
public class ArithmeticOperations implements Operations {
	private SOAPOperationsXml soapOperation = new SOAPOperationsXml();
	private String soapEndpointUrl = "http://www.dneonline.com/calculator.asmx";
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String add(String[] addVal) {
		try {
			// Calls the SOAP WebService to do the addition
			String soapAction = "http://tempuri.org/Add";
			String operation = "Add";
			String response = soapOperation.callSoapWebService(soapEndpointUrl, soapAction, addVal, operation);
			System.out.println(response);
			result = unmarshalAdd(response);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String subt(String[] subtVal) {
		try {
			// Calls the SOAP WebService to do the subtraction
			String soapAction = "http://tempuri.org/Subtract";
			String operation = "Subtract";
			String response = soapOperation.callSoapWebService(soapEndpointUrl, soapAction, subtVal, operation);
			System.out.println(response);
			result = unmarshalSub(response);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String mul(String[] mulVal) {
		try {
			// Calls the SOAP WebService to do the multiplication
			String soapAction = "http://tempuri.org/Multiply";
			String operation = "Multiply";
			String response = soapOperation.callSoapWebService(soapEndpointUrl, soapAction, mulVal, operation);
			System.out.println(response);
			result = unmarshalMul(response);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String div(String[] divVal) {
		try {
			// Calls the SOAP WebService to do the division
			String soapAction = "http://tempuri.org/Divide";
			String operation = "Divide";
			String response = soapOperation.callSoapWebService(soapEndpointUrl, soapAction, divVal, operation);
			System.out.println(response);
			result = unmarshalDiv(response);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void solveExpression(String expression) throws Exception {
		// Classifies the arithmetic operation used and store the given numbers to array
		try {
			String[] val = {};
			if (!(expression.isEmpty())) {
				if (expression.contains("+")) {
					val = expression.split("\\+");
					this.add(val);
				} else if (expression.contains("*")) {
					val = expression.split("\\*");
					this.mul(val);
				} else if (expression.contains("/")) {
					val = expression.split("\\/");
					this.div(val);
				} else if (expression.contains("-")) {
					if (expression.startsWith("-")) {
						expression = expression.substring(1);
						val = expression.split("\\-");
						val[0] = "-" + val[0];
					} else {
						val = expression.split("\\-");
					}
					this.subt(val);
				} else {
					setResult(expression);
				}
			} else {
				setResult(expression);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Unmarshal xml string to get the sum
	public String unmarshalAdd(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(AddResponse.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlString);
		AddResponse add = (AddResponse) unmarshaller.unmarshal(reader);
		return String.valueOf(add.getAddResult());
	}

	// Unmarshal xml string to get the difference
	public String unmarshalSub(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(SubtractResponse.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlString);
		SubtractResponse sub = (SubtractResponse) unmarshaller.unmarshal(reader);
		return String.valueOf(sub.getSubResult());
	}

	// Unmarshal xml string to get the product
	public String unmarshalMul(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(MultiplyResponse.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlString);
		MultiplyResponse mul = (MultiplyResponse) unmarshaller.unmarshal(reader);
		return String.valueOf(mul.getMulResult());
	}

	// Unmarshal xml string to get the quotient
	public String unmarshalDiv(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(DivideResponse.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlString);
		DivideResponse div = (DivideResponse) unmarshaller.unmarshal(reader);
		return String.valueOf(div.getDivResult());
	}
}
