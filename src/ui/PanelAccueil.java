package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;

public class PanelAccueil extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2108113375866606210L;

	/**
	 * Create the panel.
	 */
	public PanelAccueil() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 450, 0};
		gridBagLayout.rowHeights = new int[]{300, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 2.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("<html>\r\n\tBienvenue sur Playator !\r\n<br>\r\n<br>\r\n\tPour jouer, il faut vous connecter. Vous pouvez acc\u00E9der \u00E0 l'\u00E9cran de connexion par le menu \"Utilisateur\".\r\n<br>\r\n<br>\r\n\tSi vous ne poss\u00E9dez pas de compte, vous pouvez en cr\u00E9er un.\r\n</html>");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

	}

}
