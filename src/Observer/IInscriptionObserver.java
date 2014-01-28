package Observer;

public interface IInscriptionObserver {
	public void Refresh(String message, Boolean success);
	
	public String getUsername();
	public String getPassword();
}
