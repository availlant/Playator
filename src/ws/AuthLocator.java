/**
 * AuthLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AuthLocator extends org.apache.axis.client.Service implements ws.Auth {

	static String url = "";
	
	static{
		String propertiePath = "src/ws/ws.properties";

		Properties properties = new Properties();

		try {
			properties.load(new FileInputStream(propertiePath));
			url = properties.getProperty("url");
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}	
	
    public AuthLocator() {
    }


    public AuthLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AuthLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for authPort
    private java.lang.String authPort_address = "http://localhost/xml2/ws/WS.php";

    public java.lang.String getauthPortAddress() {
        return authPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String authPortWSDDServiceName = url;

    public java.lang.String getauthPortWSDDServiceName() {
        return authPortWSDDServiceName;
    }

    public void setauthPortWSDDServiceName(java.lang.String name) {
        authPortWSDDServiceName = name;
    }

    public ws.AuthPortType getauthPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(authPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getauthPort(endpoint);
    }

    public ws.AuthPortType getauthPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ws.AuthBindingStub _stub = new ws.AuthBindingStub(portAddress, this);
            _stub.setPortName(getauthPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setauthPortEndpointAddress(java.lang.String address) {
        authPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ws.AuthPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ws.AuthBindingStub _stub = new ws.AuthBindingStub(new java.net.URL(authPort_address), this);
                _stub.setPortName(getauthPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("authPort".equals(inputPortName)) {
            return getauthPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:ws", "auth");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:ws", "authPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("authPort".equals(portName)) {
            setauthPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
