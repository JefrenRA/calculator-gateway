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
import com.tap.calculator.gateway.util.jaxb.operations.UnmarshallerOperation;

@SuppressWarnings("restriction")
public class OperationsXml {
    private String res ="";
	
	public  String callSoapWebService(String soapEndpointUrl, String soapAction, String method, String[] val) {
        try {
        	UnmarshallerOperation unmarshallOps = new UnmarshallerOperation();
        	XmlBuilder xmlBuild = new XmlBuilder();
        	
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, method, val), soapEndpointUrl);
            
            //Converts xml response to string
            xmlBuild.parseSoapMessageBody(soapResponse);
            String response=xmlBuild.toXMLString();
            
            //Calls unmarshal method based on the particular arithmetic operation used
            if(method.contentEquals("Add")) {
            	res = unmarshallOps.unmarshalAdd(response);
            }
            else if(method.contentEquals("Subtract")) {
            	res = unmarshallOps.unmarshalSub(response);
            }
            else if(method.contentEquals("Multiply")) {
            	res = unmarshallOps.unmarshalMul(response);
            }
            else if(method.contentEquals("Divide")) {
            	res = unmarshallOps.unmarshalDiv(response);
            }
            
            
            soapConnection.close();
            
        } catch (Exception e) {}
        
		return res;
    }
	
	//Creates SOAP Request and get the SOAP Message
	private SOAPMessage createSOAPRequest(String soapAction, String method, String[] val) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        String nameSpace = "Calculator";
        String nameSpaceURI = "http://tempuri.org/";
        
        envelope.addNamespaceDeclaration(nameSpace, nameSpaceURI);
        createSoapOperateEnvelope(soapMessage, envelope, method, val);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        return soapMessage;
    }
	
	//Throw the operation used and the integer values to the element in the SOAP WebService and creates an SOAP Envelope
	private void createSoapOperateEnvelope(SOAPMessage soapMessage, SOAPEnvelope envelope, String method, String[] val) throws SOAPException {
       try {
    	   String nameSpace = "Calculator";
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
