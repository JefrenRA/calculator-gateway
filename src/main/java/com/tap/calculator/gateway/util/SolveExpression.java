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
		String[] val= {};
		String method="";
		String resOperation="";
		try {
			if (!(expression.isEmpty())){
				if(expression.contains("+")) {
					val = expression.split("\\+");
					method = "Add";
					resOperation = "AddResult";
				}
				else if (expression.contains("*")) {
					val = expression.split("\\*");
					method ="Multiply";
					resOperation = "MultiplyResult";
				}
				else if (expression.contains("/")) {
					val = expression.split("\\/");
					method ="Divide";
					resOperation = "DivideResult";
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
					resOperation = "SubtractResult";
				}
				else {
					setResult(expression);
				}
				
				String soapEndpointUrl = "http://www.dneonline.com/calculator.asmx";
		        String soapAction = "http://tempuri.org/"+ method;
				result = OperationsXml.callSoapWebService(soapEndpointUrl, soapAction, method, val, resOperation);
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
