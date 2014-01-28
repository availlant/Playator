package ui;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JButton;

import Observer.IInscriptionObserver;
import ui.action.InscriptionAction;

public class PanelInscription extends JPanel implements IInscriptionObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2448751812620279178L;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JLabel lblResult;

	/**
	 * Create the panel.
	 */
	public PanelInscription() {
		setName("panelInscription");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{130, 90, 0, 125, 130, 0};
		gridBagLayout.rowHeights = new int[]{60, 21, 0, 10, 25, 10, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Username :");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		txtUserName = new JTextField();
		GridBagConstraints gbc_txtUserName = new GridBagConstraints();
		gbc_txtUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUserName.insets = new Insets(0, 0, 5, 5);
		gbc_txtUserName.gridx = 3;
		gbc_txtUserName.gridy = 1;
		add(txtUserName, gbc_txtUserName);
		txtUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 2;
		add(lblPassword, gbc_lblPassword);
		
		txtPassword = new JTextField();
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtPassword.gridx = 3;
		gbc_txtPassword.gridy = 2;
		add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnSinscrire = new JButton("S'inscrire");
		btnSinscrire.addActionListener(new InscriptionAction("Inscription", this));
		
		lblResult = new JLabel("");
		GridBagConstraints gbc_lblResult = new GridBagConstraints();
		gbc_lblResult.gridwidth = 3;
		gbc_lblResult.insets = new Insets(0, 0, 5, 5);
		gbc_lblResult.gridx = 1;
		gbc_lblResult.gridy = 4;
		add(lblResult, gbc_lblResult);
		GridBagConstraints gbc_btnSinscrire = new GridBagConstraints();
		gbc_btnSinscrire.gridwidth = 3;
		gbc_btnSinscrire.insets = new Insets(0, 0, 0, 5);
		gbc_btnSinscrire.gridx = 1;
		gbc_btnSinscrire.gridy = 6;
		add(btnSinscrire, gbc_btnSinscrire);

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

	@Override
	public String getUsername() {
		return txtUserName.getText();
	}

	@Override
	public String getPassword() {
		return txtPassword.getText();
	}
}
