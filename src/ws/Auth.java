/**
 * Auth.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws;

public interface Auth extends javax.xml.rpc.Service {
    public java.lang.String getauthPortAddress();

    public ws.AuthPortType getauthPort() throws javax.xml.rpc.ServiceException;

    public ws.AuthPortType getauthPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
