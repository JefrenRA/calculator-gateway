package com.tap.calculator.gateway.util.jaxb.operations;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("restriction")
public class UnmarshallerAdd {
	public void unmarshalAdd(String xmlString) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(AddResponse.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(xmlString);
		System.out.println(xmlString);
		AddResponse add = (AddResponse) unmarshaller.unmarshal(reader);
		System.out.println(add.getAddResult());
	}
}
