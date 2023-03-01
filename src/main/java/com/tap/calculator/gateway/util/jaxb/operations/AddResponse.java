package com.tap.calculator.gateway.util.jaxb.operations;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement  
public class AddResponse {  
    private int AddResult;
  
	public AddResponse() {}  
	
	public AddResponse(int intA,int intB, int AddResult) {  
	    super();  

	    this.AddResult = AddResult;
	} 
	
	
	@XmlElement
	public int getAddResult() {
		return AddResult;
	}
	
	public void setAddResult(int AddResult) {  
	    this.AddResult = AddResult;  
	}

}


