package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import java.awt.GridBagLayout;
import javax.swing.JLabel;

import Model.Reponse;
import Model.SituationJeu;
import Model.User;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.math.BigInteger;
import java.rmi.RemoteException;

import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

import com.google.gson.Gson;

import ws.AuthPortTypeProxy;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelJeu extends JPanel {
	
	private JFrame frame;
	
	private String jeu;
	private User player;
	
	private JLabel lblTitle;
	private JLabel lblDescription;
	private JComboBox<ComboboxReponse> comboBoxChoix;
	private JLabel lblQuestion;

	/**
	 * 
	 */
	private static final long serialVersionUID = -543760664110625359L;	
	private JButton btnValider;

	/**
	 * Create the panel.
	 */
	public PanelJeu(JFrame frame) {
		this.frame = frame;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 150, 150, 150, 20, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 30, 69, 30, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblTitle = new JLabel("Jeu");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 3;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 1;
		add(lblTitle, gbc_lblTitle);
		
		lblDescription = new JLabel("");
		lblDescription.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.fill = GridBagConstraints.BOTH;
		gbc_lblDescription.gridwidth = 3;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 1;
		gbc_lblDescription.gridy = 3;
		add(lblDescription, gbc_lblDescription);
		
		lblQuestion = new JLabel("");
		GridBagConstraints gbc_lblQuestion = new GridBagConstraints();
		gbc_lblQuestion.gridwidth = 3;
		gbc_lblQuestion.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuestion.gridx = 1;
		gbc_lblQuestion.gridy = 5;
		add(lblQuestion, gbc_lblQuestion);
		
		comboBoxChoix = new JComboBox<ComboboxReponse>();
		GridBagConstraints gbc_comboBoxChoix = new GridBagConstraints();
		gbc_comboBoxChoix.gridwidth = 3;
		gbc_comboBoxChoix.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxChoix.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxChoix.gridx = 1;
		gbc_comboBoxChoix.gridy = 6;
		add(comboBoxChoix, gbc_comboBoxChoix);
		
		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxChoix.getSelectedIndex() != -1)
				{
					ComboboxReponse item = (ComboboxReponse)comboBoxChoix.getSelectedItem();
					setNextSituation(String.valueOf(item.getValue()));
				}
			}
		});
		GridBagConstraints gbc_btnValider = new GridBagConstraints();
		gbc_btnValider.insets = new Insets(0, 0, 0, 5);
		gbc_btnValider.gridx = 3;
		gbc_btnValider.gridy = 7;
		add(btnValider, gbc_btnValider);

	}
	
	public void StartJeu(final String jeu, final User player)
	{
		this.jeu = jeu;
		this.player = player;
		
		lblTitle.setText("Jeu "+jeu);
		
		setNextSituation("1");
	}
	
	public void ContinuerJeu(final String jeu, final User player)
	{
		this.jeu = jeu;
		this.player = player;
		
		lblTitle.setText("Jeu "+jeu);
		
		setNextSituation();
	}
	
	private void setNextSituation(final String code)
	{
		SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
			
			SituationJeu situation;
			
			@Override
			protected Void doInBackground() {
				AuthPortTypeProxy client = new AuthPortTypeProxy();
				
				Gson gson = new Gson();
				
				String situationJeu = "";
				
				try {
					situationJeu = client.getSituationJeu(gson.toJson(player), jeu, new BigInteger(code));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				
				situation = gson.fromJson(situationJeu, SituationJeu.class);
				
				return null;
			}
			
			@Override
			protected void done() {	
				UpdateSituationJeu(situation);
			}
		};
		
		sw.execute();
	}
	
	private void setNextSituation()
	{
		SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
			
			SituationJeu situation;
			
			@Override
			protected Void doInBackground() {
				AuthPortTypeProxy client = new AuthPortTypeProxy();
				
				Gson gson = new Gson();
				
				String situationJeu = "";
				
				try {
					situationJeu = client.getLastSituationJeu(gson.toJson(player), jeu);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				
				situation = gson.fromJson(situationJeu, SituationJeu.class);
				
				return null;
			}
			
			@Override
			protected void done() {	
				UpdateSituationJeu(situation);
			}
		};
		
		sw.execute();
	}
	
	private void UpdateSituationJeu(SituationJeu situation)
	{		
		if (situation != null)
		{
			lblDescription.setText("<html>"+situation.getDescription()+"</html>");
			lblQuestion.setText(situation.getQuestion());
			
			comboBoxChoix.removeAllItems();
			
			for (Reponse reponse : situation.getReponses()) {
				ComboboxReponse item = new ComboboxReponse();
				item.setText(reponse.getLibelle());
				item.setValue(reponse.getCode());
				comboBoxChoix.addItem(item);
			}
		}
	}

}
