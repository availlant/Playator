package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.SwingWorker;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Arrays;

import javax.swing.JButton;

import ws.AuthPortTypeProxy;

import com.google.gson.Gson;

import Model.User;

public class PanelListeJeux extends JPanel {
	private User player;
	
	private String listeJeux;
	
	private JFrame frame;
	private Application application;
	
	JPanel panelContent;
	GridBagLayout gbl_panelContent;
	
	public void Refresh(final User player){
		this.player = player;
		
		SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
			
			Gson gson = new Gson();
			String alreadyPlayedGames;
			
			@Override
			protected Void doInBackground() {
				AuthPortTypeProxy client = new AuthPortTypeProxy();
				
				try {
					listeJeux = client.searchJeu(gson.toJson(player), ".xml");
					alreadyPlayedGames = client.getAlreadyPlayedGames(gson.toJson(player));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				
				return null;
			}
			
			@Override
			protected void done() {	
				int index = 0;
				
				panelContent.removeAll();
				
				for (final String jeu : listeJeux.split(";")) {
					
					JLabel lblJeu = new JLabel(jeu);
					GridBagConstraints gbc_lblJeu = new GridBagConstraints();
					gbc_lblJeu.insets = new Insets(0, 0, 0, 0);
					gbc_lblJeu.gridx = 1;
					gbc_lblJeu.gridy = index;
					panelContent.add(lblJeu, gbc_lblJeu);
					
					String nomBouton = "Recommencer jeu";
					int position = 4;
					
					if (alreadyPlayedGames.contains(jeu))
					{
						JButton btnJouer = new JButton("Continuer jeu");
						btnJouer.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								application.ContinuerJeu(jeu, player);
							}
						});
						GridBagConstraints gbc_btnJouer = new GridBagConstraints();
						gbc_btnJouer.insets = new Insets(0, 0, 0, 0);
						gbc_btnJouer.gridx = 3;
						gbc_btnJouer.gridy = index;
						panelContent.add(btnJouer, gbc_btnJouer);
					}
					else
					{
						nomBouton = "Commencer jeu";
						position = 3;
					}
					
					JButton btnRecommencer = new JButton(nomBouton);
					btnRecommencer.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							application.StartJeu(jeu, player);
						}
					});
					GridBagConstraints gbc_Recommencer = new GridBagConstraints();
					gbc_Recommencer.insets = new Insets(0, 0, 0, 0);
					gbc_Recommencer.gridx = position;
					gbc_Recommencer.gridy = index;
					panelContent.add(btnRecommencer, gbc_Recommencer);
					
					index++;
				}
				
				int[] rowHeights = new int[index + 1];
				double[] rowWeights = new double[index + 2];
				
				for (int i = 0; i < rowHeights.length; i++) {
					rowHeights[i] = 0;
					rowWeights[i] = 0;
				}
				
				rowWeights[index + 1] = Double.MIN_VALUE;
				rowWeights[index] = 1;
				
				gbl_panelContent.columnWidths = new int[]{0, 0, 30, 0, 0, 0};
				gbl_panelContent.rowHeights = rowHeights;
				gbl_panelContent.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
				gbl_panelContent.rowWeights = rowWeights;
				
				frame.getContentPane().validate();
				frame.getContentPane().repaint();
			}
		};
		
		sw.execute();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3036804797844696938L;

	/**
	 * Create the panel.
	 */
	public PanelListeJeux(JFrame frame, Application application) {
		this.frame = frame;
		this.application = application;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{224, 1, 0};
		gridBagLayout.rowHeights = new int[]{1, 62, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLayeredPane PanelListe = new JLayeredPane();
		GridBagConstraints gbc_PanelListe = new GridBagConstraints();
		gbc_PanelListe.insets = new Insets(0, 0, 5, 0);
		gbc_PanelListe.anchor = GridBagConstraints.NORTHWEST;
		gbc_PanelListe.gridx = 1;
		gbc_PanelListe.gridy = 0;
		add(PanelListe, gbc_PanelListe);
		
		JPanel panelTitle = new JPanel();
		GridBagConstraints gbc_panelTitle = new GridBagConstraints();
		gbc_panelTitle.insets = new Insets(0, 0, 5, 5);
		gbc_panelTitle.fill = GridBagConstraints.BOTH;
		gbc_panelTitle.gridx = 0;
		gbc_panelTitle.gridy = 1;
		add(panelTitle, gbc_panelTitle);
		GridBagLayout gbl_panelTitle = new GridBagLayout();
		gbl_panelTitle.columnWidths = new int[]{0, 124, 0, 0};
		gbl_panelTitle.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelTitle.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelTitle.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelTitle.setLayout(gbl_panelTitle);
		
		JLabel lblListeDesJeux = new JLabel("Liste des jeux");
		GridBagConstraints gbc_lblListeDesJeux = new GridBagConstraints();
		gbc_lblListeDesJeux.insets = new Insets(0, 0, 5, 5);
		gbc_lblListeDesJeux.gridx = 1;
		gbc_lblListeDesJeux.gridy = 1;
		panelTitle.add(lblListeDesJeux, gbc_lblListeDesJeux);
		
		panelContent = new JPanel();
		GridBagConstraints gbc_panelContent = new GridBagConstraints();
		gbc_panelContent.insets = new Insets(0, 0, 0, 5);
		gbc_panelContent.fill = GridBagConstraints.BOTH;
		gbc_panelContent.gridx = 0;
		gbc_panelContent.gridy = 2;
		add(panelContent, gbc_panelContent);
		gbl_panelContent = new GridBagLayout();
		gbl_panelContent.columnWidths = new int[]{0, 0};
		gbl_panelContent.rowHeights = new int[]{0, 0};
		gbl_panelContent.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelContent.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelContent.setLayout(gbl_panelContent);

	}
}
