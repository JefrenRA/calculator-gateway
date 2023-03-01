package com.tap.calculator.gateway.util.soap;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javax.xml.soap.SOAPMessage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlBuilder {

	private Document document = null;

	public XmlBuilder() {
		init();
	}

	private void init() {
		try {

			this.document = null;
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			this.document = builder.newDocument();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("restriction")
	public void parseSoapMessageBody(SOAPMessage soapMsg) throws Exception {

		if (this.document != null) {
			init();
		}

		Document documentTemp = null;
		Element root = null;
		StringWriter sw = null;
		StreamResult xmlResult = null;
		Source source = null;
		
		try {

			source = soapMsg.getSOAPPart().getContent();

			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transFormer = transFactory.newTransformer();
			transFormer.setOutputProperty(OutputKeys.ENCODING, "utf-8");

			sw = new StringWriter();
			xmlResult = new StreamResult(sw);
			transFormer.transform(source, xmlResult);

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			documentTemp = builder.parse(new ByteArrayInputStream(sw.toString().getBytes()));

			NodeList list = documentTemp.getDocumentElement().getElementsByTagName("soap:Body");

			if (list != null && list.getLength() > 0) {
				Node note = soapMsg.getSOAPBody().getFirstChild();
				Node node2 = this.document.importNode(note, true);
				this.document.appendChild(node2);
			} else {
				root = this.document.createElement("soap:Body");
				this.document.appendChild(root);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String toXMLString() {

		try {
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transFormer = transFactory.newTransformer();
			transFormer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			
			DOMSource domSource = new DOMSource(document);
			StringWriter sw = new StringWriter();
			StreamResult xmlResult = new StreamResult(sw);
			transFormer.transform(domSource, xmlResult);
			
			return sw.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
