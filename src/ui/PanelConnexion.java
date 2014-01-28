package ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;

import Observer.IConnexionObserver;

import ui.action.ConnexionAction;

public class PanelConnexion extends JPanel implements IConnexionObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4551624599156720072L;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JLabel lblResult;
	
	private Application application;

	/**
	 * Create the panel.
	 */
	public PanelConnexion(Application application) {
		this.application = application;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{130, 90, 0, 125, 130, 0};
		gridBagLayout.rowHeights = new int[]{60, 24, 23, 10, 25, 10, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblUsername = new JLabel("Username :");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 1;
		add(lblUsername, gbc_lblUsername);
		
		txtUsername = new JTextField();
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 3;
		gbc_txtUsername.gridy = 1;
		add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 2;
		add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JTextField();
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 3;
		gbc_txtPassword.gridy = 2;
		add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnConnexion = new JButton("Se connecter");
		btnConnexion.addActionListener(new ConnexionAction(this));
		
		lblResult = new JLabel("");
		GridBagConstraints gbc_lblResult = new GridBagConstraints();
		gbc_lblResult.gridwidth = 3;
		gbc_lblResult.insets = new Insets(0, 0, 5, 5);
		gbc_lblResult.gridx = 1;
		gbc_lblResult.gridy = 4;
		add(lblResult, gbc_lblResult);
		GridBagConstraints gbc_btnConnexion = new GridBagConstraints();
		gbc_btnConnexion.gridwidth = 3;
		gbc_btnConnexion.insets = new Insets(0, 0, 0, 5);
		gbc_btnConnexion.gridx = 1;
		gbc_btnConnexion.gridy = 6;
		add(btnConnexion, gbc_btnConnexion);

	}

	@Override
	public String getUsername() {
		return this.txtUsername.getText();
	}

	@Override
	public String getPassword() {
		return this.txtPassword.getText();
	}

	@Override
	public void result(Boolean successful) {
		if (!successful){
			this.lblResult.setText("Authentification impossible");
			this.lblResult.setForeground(Color.red);
		}
		else
		{
			this.application.Authentification(txtUsername.getText(), txtPassword.getText());
		}
	}

	@Override
	public void Refresh(String message, Boolean success) {
		if (success)
		{
			this.lblResult.setForeground(Color.green);
		}
		else
		{
			this.lblResult.setForeground(Color.red);
		}
			
		this.lblResult.setText(message);
	}

}
