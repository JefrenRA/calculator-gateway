package com.tap.calculator.gateway.util.jaxb.operations;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("restriction")
public class UnmarshallerOperation {
	//Unmarshal xml string to get the sum
	public String unmarshalAdd(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(AddResponse.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlString);
		AddResponse add = (AddResponse) unmarshaller.unmarshal(reader);
		return String.valueOf(add.getAddResult());
	}
	
	//Unmarshal xml string to get the difference
	public String unmarshalSub(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(SubtractResponse.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlString);
		SubtractResponse sub = (SubtractResponse) unmarshaller.unmarshal(reader);
		return String.valueOf(sub.getSubResult());
	}
	
	//Unmarshal xml string to get the product
	public String unmarshalMul(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(MultiplyResponse.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlString);
		MultiplyResponse mul = (MultiplyResponse) unmarshaller.unmarshal(reader);
		return String.valueOf(mul.getMulResult());
	}
	
	//Unmarshal xml string to get the quotient
	public String unmarshalDiv(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(DivideResponse.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlString);
		DivideResponse div = (DivideResponse) unmarshaller.unmarshal(reader);
		return String.valueOf(div.getDivResult());
	}
}
