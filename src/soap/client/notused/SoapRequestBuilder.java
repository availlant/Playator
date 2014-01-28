package soap.client.notused;

import java.util.HashMap;
import java.util.Map;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

public class SoapRequestBuilder {
	SOAPMessage request;
	final String namespace = "playator";
	
	public SOAPMessage createRequest(String method, HashMap<String, String> parameters, String serverURI) throws SOAPException{		
		MessageFactory messageFactory = MessageFactory.newInstance();
		this.request = messageFactory.createMessage();
		SOAPPart soapPart = this.request.getSOAPPart();
		
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration(this.namespace, serverURI);
		
		SOAPBody body = envelope.getBody();
		SOAPElement methodElement = body.addChildElement(method, this.namespace);
		
		for (Map.Entry<String, String> pair : parameters.entrySet()) {
			String key = pair.getKey();
			String value = pair.getValue();
			
			SOAPElement loginParameter = methodElement.addChildElement(key, this.namespace);
			loginParameter.addTextNode(value);
		}
		
		
		MimeHeaders headers = this.request.getMimeHeaders();
		headers.addHeader("SOAPAction", serverURI + method);
		
		this.request.saveChanges();
		
		return this.request;
	}	
}
