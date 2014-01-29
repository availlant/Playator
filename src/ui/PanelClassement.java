package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

import com.google.gson.Gson;

import Model.User;

import ws.AuthPortTypeProxy;

import java.awt.Component;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

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
		gridBagLayout.columnWidths = new int[] { 0, 117, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 20, 20, 20, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, Double.MIN_VALUE };
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

		final JLabel lblPremier = new JLabel("");
		lblPremier.setToolTipText("Premier");
		GridBagConstraints gbc_lblPremier = new GridBagConstraints();
		gbc_lblPremier.insets = new Insets(0, 0, 5, 5);
		gbc_lblPremier.gridx = 1;
		gbc_lblPremier.gridy = 3;
		add(lblPremier, gbc_lblPremier);

		final JLabel lblDeuxieme = new JLabel("");
		lblDeuxieme.setToolTipText("Premier");
		GridBagConstraints gbc_lblDeuxieme = new GridBagConstraints();
		gbc_lblDeuxieme.insets = new Insets(0, 0, 5, 5);
		gbc_lblDeuxieme.gridx = 1;
		gbc_lblDeuxieme.gridy = 4;
		add(lblDeuxieme, gbc_lblDeuxieme);

		final JLabel lblTroisieme = new JLabel("");
		GridBagConstraints gbc_lblTroisieme = new GridBagConstraints();
		gbc_lblTroisieme.insets = new Insets(0, 0, 5, 5);
		gbc_lblTroisieme.gridx = 1;
		gbc_lblTroisieme.gridy = 5;
		add(lblTroisieme, gbc_lblTroisieme);

		SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {

			String xmlJeu = "";
			String xmlUsers = "";

			String[] troisMeilleurs;

			@Override
			protected Void doInBackground() {
				AuthPortTypeProxy client = new AuthPortTypeProxy();

				Gson gson = new Gson();

				try {
					xmlJeu = client.getFileContent(gson.toJson(player),
							"jeux\\" + jeu);
					xmlUsers = client.getFileContent(gson.toJson(player),
							"users\\users.xml");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}

				troisMeilleurs = getTroisMeilleurs(jeu, xmlJeu, xmlUsers);

				return null;
			}

			@Override
			protected void done() {
				lblPremier.setText(troisMeilleurs[0]);
				lblDeuxieme.setText(troisMeilleurs[1]);
				lblTroisieme.setText(troisMeilleurs[2]);
			}
		};

		sw.execute();
	}

	private String[] getTroisMeilleurs(String nomJeu, String xmlJeu,
			String xmlUsers) {
		String[] result = new String[3];

		HashMap<String, List<String>> partiesParJoueur = getParties(nomJeu,
				xmlUsers);
		
		if (partiesParJoueur == null)
			return result;

		HashMap<Integer, Integer> pointParSituation = getPoints(xmlJeu);
		
		List<classement> classement = new ArrayList<>();
		
		for (Entry<String, List<String>> partie : partiesParJoueur.entrySet()) {
			String joueur = partie.getKey();
			List<String> deroules = partie.getValue();		
			
			for (String deroule : deroules) {
				classement position = new classement();				
				int points = 0;				
				String[] codes = deroule.split(";");
				
				for (int i = 0; i < codes.length; i++) {
					int codeInteger = 0;
					try {
						codeInteger = Integer.parseInt(codes[i]);
					} catch (Exception e) {
						continue;
					}
					
					points += pointParSituation.get(codeInteger) != null ? pointParSituation.get(codeInteger) : 0;
				}
				
				position.joueur = joueur;
				position.points = points;
				
				classement.add(position);
			}
		}
		
		Collections.sort(classement, new Comparator<classement>() {

			@Override
			public int compare(classement o1,
					classement o2) {
				return Integer.compare(o2.points,o1.points);
			}
		});	
		
		for (int i = 0; i < 3; i++) {
			classement score = classement.get(i);
			result[i] = score.joueur + " " + score.points + "pts";
		}

		return result;
	}
	
	public class classement{
		public String joueur;
		public int points;
	}

	private HashMap<Integer, Integer> getPoints(String xmlJeu) {
		HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();

		DOMImplementationRegistry dir = null;

		try {
			dir = DOMImplementationRegistry.newInstance();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | ClassCastException e) {
			e.printStackTrace();
		}

		DOMImplementationLS dils = (DOMImplementationLS) dir
				.getDOMImplementation("LS 3.0");
		LSParser lsp = dils.createLSParser(
				DOMImplementationLS.MODE_SYNCHRONOUS, null);
		LSInput input = dils.createLSInput();
		input.setStringData(xmlJeu);
		Document doc = lsp.parse(input);

		XPath xpath = XPathFactory.newInstance().newXPath();
		xpath.setNamespaceContext(new NamespaceContext() {
			
			@Override
			public Iterator getPrefixes(String namespaceURI) {
				return null;
			}
			
			@Override
			public String getPrefix(String namespaceURI) {
				return null;
			}
			
			@Override
			public String getNamespaceURI(String prefix) {
					return "jeudrole";
			}
		});
		NodeList situationNodes = null;
		try {
			situationNodes = (NodeList) xpath.evaluate("//j:situation",
					doc.getDocumentElement(), XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		if (null == situationNodes)
			return result;

		DocumentTraversal traversal = (DocumentTraversal) doc;
		
		for (int i = 0; i < situationNodes.getLength(); ++i) {
			Node node = situationNodes.item(i);
			
			int code = 0;
			int points = 0;

			TreeWalker walker = traversal.createTreeWalker(node,
					NodeFilter.SHOW_ALL, new NodeFilter() {

						@Override
						public short acceptNode(Node n) {
							if (n.getNodeName() == "j:code"
									|| n.getNodeName() == "j:points")
								return NodeFilter.FILTER_ACCEPT;
							return NodeFilter.FILTER_REJECT;
						}
					}, false);

			Node codeNode = walker.nextNode();
			Node pointsNode = walker.nextNode();
			
			try
			{
				code = Integer.parseInt(codeNode.getTextContent());
				points = Integer.parseInt(pointsNode.getTextContent());
				
				result.put(code, points);
			}
			finally {
				
			}
		}

		return result;
	}

	private HashMap<String, List<String>> getParties(String nomJeu,
			String xmlUsers) {
		DOMImplementationRegistry dir = null;

		try {
			dir = DOMImplementationRegistry.newInstance();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | ClassCastException e) {
			e.printStackTrace();
		}

		DOMImplementationLS dils = (DOMImplementationLS) dir
				.getDOMImplementation("LS 3.0");
		LSParser lsp = dils.createLSParser(
				DOMImplementationLS.MODE_SYNCHRONOUS, null);
		LSInput input = dils.createLSInput();
		input.setStringData(xmlUsers);
		Document doc = lsp.parse(input);

		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList deroulesNodes = null;
		try {
			deroulesNodes = (NodeList) xpath.evaluate("//deroule[./jeu ='"
					+ nomJeu + "']", doc.getDocumentElement(),
					XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		if (null == deroulesNodes)
			return null;

		HashMap<String, List<String>> deroulesParJoueur = new HashMap<>();

		for (int i = 0; i < deroulesNodes.getLength(); ++i) {
			Node node = deroulesNodes.item(i);

			String nomJoueur = node.getParentNode().getParentNode()
					.getFirstChild().getNextSibling().getTextContent();
			NodeList situationsNodes = null;

			try {
				situationsNodes = (NodeList) xpath.evaluate("situationCode",
						node, XPathConstants.NODESET);
			} catch (XPathExpressionException e) {
				e.printStackTrace();
			}

			if (null == situationsNodes)
				continue;

			List<String> deroules = new ArrayList<String>();
			String deroule = "";

			for (int j = 0; j < situationsNodes.getLength(); ++j) {
				Node situationNode = situationsNodes.item(j);

				int code = 1;
				try {
					code = Integer.parseInt(situationNode.getTextContent());
				} finally {

				}

				if (code == 1) {

					if (!deroule.isEmpty())
						deroules.add(deroule);
					deroule = "";
				}

				if (deroule.isEmpty())
					deroule += situationNode.getTextContent();
				else
					deroule += ";" + situationNode.getTextContent();
			}

			if (!deroule.isEmpty())
				deroules.add(deroule);

			deroulesParJoueur.put(nomJoueur, deroules);
		}

		return deroulesParJoueur;
	}
}
