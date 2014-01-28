package ws;

public class AuthPortTypeProxy implements ws.AuthPortType {
  private String _endpoint = null;
  private ws.AuthPortType authPortType = null;
  
  public AuthPortTypeProxy() {
    _initAuthPortTypeProxy();
  }
  
  public AuthPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initAuthPortTypeProxy();
  }
  
  private void _initAuthPortTypeProxy() {
    try {
      authPortType = (new ws.AuthLocator()).getauthPort();
      if (authPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)authPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)authPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (authPortType != null)
      ((javax.xml.rpc.Stub)authPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ws.AuthPortType getAuthPortType() {
    if (authPortType == null)
      _initAuthPortTypeProxy();
    return authPortType;
  }
  
  public boolean saveFile(java.lang.String credentials, java.lang.String fileName, java.lang.String json) throws java.rmi.RemoteException{
    if (authPortType == null)
      _initAuthPortTypeProxy();
    return authPortType.saveFile(credentials, fileName, json);
  }
  
  public java.lang.String getFile(java.lang.String credentials, java.lang.String fileName) throws java.rmi.RemoteException{
    if (authPortType == null)
      _initAuthPortTypeProxy();
    return authPortType.getFile(credentials, fileName);
  }
  
  public boolean inscription(java.lang.String login, java.lang.String password) throws java.rmi.RemoteException{
    if (authPortType == null)
      _initAuthPortTypeProxy();
    return authPortType.inscription(login, password);
  }
  
  public boolean loginAlreadyExists(java.lang.String login) throws java.rmi.RemoteException{
    if (authPortType == null)
      _initAuthPortTypeProxy();
    return authPortType.loginAlreadyExists(login);
  }
  
  public boolean authentication(java.lang.String login, java.lang.String password) throws java.rmi.RemoteException{
    if (authPortType == null)
      _initAuthPortTypeProxy();
    return authPortType.authentication(login, password);
  }
  
  public java.lang.String searchJeu(java.lang.String credentials, java.lang.String something) throws java.rmi.RemoteException{
    if (authPortType == null)
      _initAuthPortTypeProxy();
    return authPortType.searchJeu(credentials, something);
  }
  
  public java.lang.String getSituationJeu(java.lang.String credentials, java.lang.String fileName, java.math.BigInteger situationCode) throws java.rmi.RemoteException{
    if (authPortType == null)
      _initAuthPortTypeProxy();
    return authPortType.getSituationJeu(credentials, fileName, situationCode);
  }
  
  public java.lang.String getLastSituationJeu(java.lang.String credentials, java.lang.String fileName) throws java.rmi.RemoteException{
    if (authPortType == null)
      _initAuthPortTypeProxy();
    return authPortType.getLastSituationJeu(credentials, fileName);
  }
  
  public java.lang.String getAlreadyPlayedGames(java.lang.String credentials) throws java.rmi.RemoteException{
    if (authPortType == null)
      _initAuthPortTypeProxy();
    return authPortType.getAlreadyPlayedGames(credentials);
  }
  
  
}