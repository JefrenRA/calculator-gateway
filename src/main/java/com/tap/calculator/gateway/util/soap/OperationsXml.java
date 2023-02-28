package com.tap.calculator.gateway.util.soap;

import java.io.ByteArrayOutputStream;

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


public class OperationsXml {
	private static getSOAPResponse result = new getSOAPResponse();
	
	private static String nameSpace = "Calculator";
    private static String nameSpaceURI = "http://tempuri.org/";
    private static String res ="";
	
	@SuppressWarnings("restriction")
	public static String callSoapWebService(String soapEndpointUrl, String soapAction, String method, String[] val, String resOperation) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, method, val), soapEndpointUrl);

            
         // get SOAP message response as string
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            soapResponse.writeTo(out);
            String response= out.toString();
            res = result.gettingResult(response,resOperation);
            
            soapConnection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return res;
    }
	
	@SuppressWarnings("restriction")
	private static SOAPMessage createSOAPRequest(String soapAction, String method, String[] val) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        
        envelope.addNamespaceDeclaration(nameSpace, nameSpaceURI);
        createSoapOperateEnvelope(soapMessage, envelope, method, val);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        return soapMessage;
    }
	
	@SuppressWarnings("restriction")
	private static void createSoapOperateEnvelope(SOAPMessage soapMessage, SOAPEnvelope envelope, String method, String[] val) throws SOAPException {
       try {
    	   SOAPBody soapAddBody = envelope.getBody();
           SOAPElement soapAddBodyElem = soapAddBody.addChildElement(method, nameSpace);
           SOAPElement soapAddSubElem1 = soapAddBodyElem.addChildElement("intA", nameSpace);
           SOAPElement soapAddSubElem2 = soapAddBodyElem.addChildElement("intB", nameSpace);
           soapAddSubElem1.addTextNode(val[0]);
           soapAddSubElem2.addTextNode(val[1]);
       }
       catch(Exception e) {
    	   
       }
    }
}
