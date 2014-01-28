package ui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JMenu;

import Model.User;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application {

	private JFrame frame;
	
	private PanelListeJeux panelListeJeux;
	private PanelJeu panelJeu;
	
	private JMenuItem mntmConnexion;
	private JMenuItem mntmInscription;
	private JMenu mnUtilisateur;
	
	private User player;
	private JMenuItem mntmListeDesJeux;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application main = new Application();
					main.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnUtilisateur = new JMenu("Utilisateur");
		menuBar.add(mnUtilisateur);
		
		mntmConnexion = new JMenuItem("Connexion");
		mntmConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				redirectTo("panelConnexion");
			}
		});
		mnUtilisateur.add(mntmConnexion);		
		
		mntmInscription = new JMenuItem("Inscription");
		mntmInscription.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				redirectTo("panelInscription");
			}
		});
		mnUtilisateur.add(mntmInscription);
		
		mntmListeDesJeux = new JMenuItem("Liste des jeux");
		mntmListeDesJeux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				redirectTo("panelListeJeux");
				
				panelListeJeux.Refresh(player);
			}
		});
		mntmListeDesJeux.setVisible(false);
		menuBar.add(mntmListeDesJeux);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panelAccueil = new PanelAccueil();
		frame.getContentPane().add(panelAccueil, "panelAccueil");
		
		JPanel panelConnexion = new PanelConnexion(this);
		frame.getContentPane().add(panelConnexion, "panelConnexion");
		
		JPanel panelInscription = new PanelInscription();
		frame.getContentPane().add(panelInscription, "panelInscription");
		
		panelListeJeux = new PanelListeJeux(frame, this);
		frame.getContentPane().add(panelListeJeux, "panelListeJeux");
		
		panelJeu = new PanelJeu(frame);
		frame.getContentPane().add(panelJeu, "panelJeu");
	}

	public void Authentification(String username, String password) {
		this.player = new User(username, password);
		
		mnUtilisateur.setVisible(false);
		mntmListeDesJeux.setVisible(true);
		
		redirectTo("panelAccueil");
	}
	
	public void StartJeu(String jeu, User player)
	{
		frame.getContentPane().remove(panelJeu);
		panelJeu = new PanelJeu(frame);
		frame.getContentPane().add(panelJeu, "panelJeu");
		
		panelJeu.StartJeu(jeu, player);
		
		redirectTo("panelJeu");
	}
	
	public void ContinuerJeu(String jeu, User player)
	{
		frame.getContentPane().remove(panelJeu);
		panelJeu = new PanelJeu(frame);
		frame.getContentPane().add(panelJeu, "panelJeu");
		
		panelJeu.ContinuerJeu(jeu, player);
		
		redirectTo("panelJeu");
	}
	
	public void redirectTo(String panelName)
	{
		CardLayout cl = (CardLayout)frame.getContentPane().getLayout();
		cl.show(frame.getContentPane(), panelName);
	}
}
