package soap.client.notused;

import java.util.HashMap;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class SoapClient extends SoapClientBase {
	String url;
	String serverURI;
	
	public SoapClient(String url, String serverURI) {
		this.url = url;
		this.serverURI = serverURI;
	}
	
	public SOAPMessage call(String method, HashMap<String, String> parameters) throws SOAPException{
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		
		SoapRequestBuilder soapRequestBuilder = new SoapRequestBuilder();
		
		SOAPMessage request = soapRequestBuilder.createRequest(method, parameters, this.serverURI);
		SOAPMessage response = soapConnection.call(request, this.url);
		
		soapConnection.close();
		
		return response;
	}
}
