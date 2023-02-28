package com.tap.calculator.gateway.util.soap;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class getSOAPResponse {
	
	public String gettingResult(String response, String resOperation) throws Exception {
		List<String> output = getResultFromXml(response, resOperation);
		String[] strarray = new String[output.size()];
		output.toArray(strarray);
		String ans = output.toString();
		ans = "=" + ans.substring(1, ans.length()-1);
		return ans;
	}
	
	public static Document loadXMLString(String response) throws Exception
	{
	    DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(response));

	    return db.parse(is);
	}

	public static List<String> getResultFromXml(String response, String tagName) throws Exception {
	    Document xmlDoc = loadXMLString(response);
	    NodeList nodeList = xmlDoc.getElementsByTagName(tagName);
	    List<String> ids = new ArrayList<String>(nodeList.getLength());
	    for(int i=0;i<nodeList.getLength(); i++) {
	        Node x = nodeList.item(i);
	        ids.add(x.getFirstChild().getNodeValue());             
	    }
	    return ids;
	}
}
