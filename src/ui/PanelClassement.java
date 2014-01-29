package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

import com.google.gson.Gson;

import Model.User;

import ws.AuthPortTypeProxy;

import java.awt.Component;
import java.rmi.RemoteException;

public class PanelClassement extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8490999704916634527L;

	/**
	 * Create the panel.
	 */
	public PanelClassement(final String jeu, final User player) {		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 117, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 20, 20, 20, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTitre = new JLabel("Classement");
		lblTitre.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitre.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTitre = new GridBagConstraints();
		gbc_lblTitre.fill = GridBagConstraints.BOTH;
		gbc_lblTitre.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitre.gridx = 1;
		gbc_lblTitre.gridy = 1;
		add(lblTitre, gbc_lblTitre);
		
		JLabel lblPremier = new JLabel("");
		lblPremier.setToolTipText("Premier");
		GridBagConstraints gbc_lblPremier = new GridBagConstraints();
		gbc_lblPremier.insets = new Insets(0, 0, 5, 5);
		gbc_lblPremier.gridx = 1;
		gbc_lblPremier.gridy = 3;
		add(lblPremier, gbc_lblPremier);
		
		JLabel lblDeuxieme = new JLabel("");
		lblDeuxieme.setToolTipText("Premier");
		GridBagConstraints gbc_lblDeuxieme = new GridBagConstraints();
		gbc_lblDeuxieme.insets = new Insets(0, 0, 5, 5);
		gbc_lblDeuxieme.gridx = 1;
		gbc_lblDeuxieme.gridy = 4;
		add(lblDeuxieme, gbc_lblDeuxieme);
		
		JLabel lblTroisieme = new JLabel("");
		GridBagConstraints gbc_lblTroisieme = new GridBagConstraints();
		gbc_lblTroisieme.insets = new Insets(0, 0, 5, 5);
		gbc_lblTroisieme.gridx = 1;
		gbc_lblTroisieme.gridy = 5;
		add(lblTroisieme, gbc_lblTroisieme);
		
		SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
			
			String xmlJeu = "";
			String xmlUsers = "";
			
			@Override
			protected Void doInBackground() {
				AuthPortTypeProxy client = new AuthPortTypeProxy();
				
				Gson gson = new Gson();
				
				try {
					xmlJeu = client.getFileContent(gson.toJson(player), "jeux\\"+jeu);
					xmlUsers = client.getFileContent(gson.toJson(player), "users\\users.xml");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				
				System.out.println(xmlUsers);
				
				return null;
			}
			
			@Override
			protected void done() {	
			}
		};
		
		sw.execute();
	}

}
