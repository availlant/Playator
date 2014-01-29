/**
 * AuthPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws;

public interface AuthPortType extends java.rmi.Remote {

    /**
     * Sauvegarde le fichier passÃ© en paramÃ¨tre
     */
    public boolean saveFile(java.lang.String credentials, java.lang.String fileName, java.lang.String json) throws java.rmi.RemoteException;

    /**
     * RÃ©cupÃ¨re un fichier passÃ© en paramÃ¨tre
     */
    public java.lang.String getFile(java.lang.String credentials, java.lang.String fileName) throws java.rmi.RemoteException;

    /**
     * RÃ©cupÃ¨re le contenu du fichier passÃ© en paramÃ¨tre
     */
    public java.lang.String getFileContent(java.lang.String credentials, java.lang.String fileName) throws java.rmi.RemoteException;

    /**
     * InsÃ¨re un nouvel utilisateur en base
     */
    public boolean inscription(java.lang.String login, java.lang.String password) throws java.rmi.RemoteException;
    public boolean loginAlreadyExists(java.lang.String login) throws java.rmi.RemoteException;

    /**
     * Authentification du couple login/mot de passe ?
     */
    public boolean authentication(java.lang.String login, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * Trouve des jeux en fonction du paramÃ¨tre
     */
    public java.lang.String searchJeu(java.lang.String credentials, java.lang.String something) throws java.rmi.RemoteException;

    /**
     * Renvoie une situation de jeu
     */
    public java.lang.String getSituationJeu(java.lang.String credentials, java.lang.String fileName, java.math.BigInteger situationCode) throws java.rmi.RemoteException;
    public java.lang.String getLastSituationJeu(java.lang.String credentials, java.lang.String fileName) throws java.rmi.RemoteException;
    public java.lang.String getAlreadyPlayedGames(java.lang.String credentials) throws java.rmi.RemoteException;
}
