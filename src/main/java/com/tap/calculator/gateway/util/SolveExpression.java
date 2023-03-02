package com.tap.calculator.gateway.util;

import com.tap.calculator.gateway.util.soap.OperationsXml;

public class SolveExpression {
	private String result;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public void solve(String expression) throws Exception {
		OperationsXml ops = new OperationsXml();
		String[] val= {};
		String method="";
		
		//Classifies the arithmetic operation used and store the given numbers to array
		try {
			if (!(expression.isEmpty())){
				if(expression.contains("+")) {
					val = expression.split("\\+");
					method = "Add";
				}
				else if (expression.contains("*")) {
					val = expression.split("\\*");
					method ="Multiply";
				}
				else if (expression.contains("/")) {
					val = expression.split("\\/");
					method ="Divide";
				}
				else if (expression.contains("-")) {
					if (expression.startsWith("-")) {
						expression = expression.substring(1);
						val = expression.split("\\-");
						val[0]="-"+val[0];
					}
					else {
						val = expression.split("\\-");
					}
					method ="Subtract";
				}
				else {
					setResult(expression);
				}
				
				//Calls the SOAP WebService to do the operation
				String soapEndpointUrl = "http://www.dneonline.com/calculator.asmx";
		        String soapAction = "http://tempuri.org/"+ method;
				result = ops.callSoapWebService(soapEndpointUrl, soapAction, method, val);
			}
			else {
				setResult(expression);
			}			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}	 

}
