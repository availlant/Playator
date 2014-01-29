package Model;

public class User {
	@SuppressWarnings("unused")
	private String Login;
	@SuppressWarnings("unused")
	private String Mdp;
	
	public User(String username, String password){
		this.Login = username;
		this.Mdp = password;
	}
}
