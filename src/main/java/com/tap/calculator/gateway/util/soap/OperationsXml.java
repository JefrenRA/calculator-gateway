package com.tap.calculator.gateway.util.soap;

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
import com.tap.calculator.gateway.util.jaxb.operations.UnmarshallerAdd;


@SuppressWarnings("restriction")
public class OperationsXml {
	//private static SOAPResponse result = new SOAPResponse();
	private static UnmarshallerAdd uAdd = new UnmarshallerAdd();
	private static XmlBuilder xmlBuild = new XmlBuilder();
	private static String nameSpace = "Calculator";
    private static String nameSpaceURI = "http://tempuri.org/";
    private static String res ="";
	
	public static String callSoapWebService(String soapEndpointUrl, String soapAction, String method, String[] val, String resOperation) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, method, val), soapEndpointUrl);
            
            xmlBuild.parseSoapMessageBody(soapResponse);
            String response=xmlBuild.toXMLString();
            uAdd.unmarshalAdd(response);
            //res = result.gettingResult(response,resOperation);
            
            soapConnection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
		return res;
    }
	
	private static SOAPMessage createSOAPRequest(String soapAction, String method, String[] val) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        
        envelope.addNamespaceDeclaration(nameSpace, nameSpaceURI);
        createSoapOperateEnvelope(soapMessage, envelope, method, val);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        return soapMessage;
    }
	
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
