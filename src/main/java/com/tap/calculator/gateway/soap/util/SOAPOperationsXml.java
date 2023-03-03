package com.tap.calculator.gateway.soap.util;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

@SuppressWarnings("restriction")
public class SOAPOperationsXml {
    private String response;
	
	public  String callSoapWebService(String soapEndpointUrl, String soapAction, String[] val, String operation) {
        try {
        	XmlBuilder xmlBuild = new XmlBuilder();
        	
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, val, operation), soapEndpointUrl);
            
            //Converts xml response to string
            xmlBuild.parseSoapMessageBody(soapResponse);
            response=xmlBuild.toXMLString();
            
            soapConnection.close();
            
        } catch (Exception e) {}
        
		return response;
    }
	
	//Creates SOAP Request and get the SOAP Message
	private SOAPMessage createSOAPRequest(String soapAction, String[] val, String operation) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        String nameSpace = "Calculator";
        String nameSpaceURI = "http://tempuri.org/";
        
        envelope.addNamespaceDeclaration(nameSpace, nameSpaceURI);
        createSoapOperateEnvelope(envelope, val, operation);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        return soapMessage;
    }
	
	//Throw the operation used and the integer values to the element in the SOAP WebService and creates an SOAP Envelope
	private void createSoapOperateEnvelope(SOAPEnvelope envelope, String[] val, String operation) throws SOAPException {
       try {
    	   String nameSpace = "Calculator";
    	   SOAPBody soapAddBody = envelope.getBody();
           SOAPElement soapAddBodyElem = soapAddBody.addChildElement(operation, nameSpace);
           SOAPElement soapAddSubElem1 = soapAddBodyElem.addChildElement("intA", nameSpace);
           SOAPElement soapAddSubElem2 = soapAddBodyElem.addChildElement("intB", nameSpace);
           soapAddSubElem1.addTextNode(val[0]);
           soapAddSubElem2.addTextNode(val[1]);
       }
       catch(Exception e) {}
    }
}
