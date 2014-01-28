package ui.action;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import javax.swing.AbstractAction;
import javax.swing.SwingWorker;
import Observer.IInscriptionObserver;

import ws.AuthPortTypeProxy;

public class InscriptionAction extends AbstractAction {
	
	private IInscriptionObserver observer;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6001587207927910697L;

	public InscriptionAction(String texte, IInscriptionObserver observer){
		super(texte);
		this.observer = observer;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.observer.getUsername().isEmpty() || this.observer.getPassword().isEmpty())
		{
			observer.Refresh("Le nom d'utilisateur ou le mot de passe est vide.", false);
			return;
		}
		
		Inscription(this.observer.getUsername(), this.observer.getPassword());
	}
	
	private void Inscription(final String login, final String password)
	{
		SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
			
			Boolean usersExists = false;
			Boolean error = false;
			
			@Override
			protected Void doInBackground() {
				AuthPortTypeProxy client = new AuthPortTypeProxy();
				
				try {
					usersExists = client.loginAlreadyExists(login);
				} catch (RemoteException e1) {
					e1.printStackTrace();
					error = true;
				}
				
				if (usersExists)
					return null;
				
				try {
					client.inscription(login, password);
				} catch (RemoteException e1) {
					e1.printStackTrace();
					error = true;
				}
				
				return null;
			}
			
			@Override
			protected void done() {	
				
				if (error)
				{					
					observer.Refresh("Echec de l'inscription", false);
					return;
				}
				
				if (usersExists)
				{
					observer.Refresh("Ce login est déjà utilisé, veuillez en choisir un autre.", false);
					return;
				}
				
				observer.Refresh("L'utilisateur est inscrit.", true);
			}
		};
		
		sw.execute();
	}
}
