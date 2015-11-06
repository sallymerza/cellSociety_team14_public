package Main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import GUI.ConfigurationException;

public class XMLReader {
	private String mySimulation;
	private String myTitle;
	private String myAuthor;
	private int myRows;
	private int myColumns;
	private String myNeighbors;
	private String[] myStates;
	private String myShape;
	private String[][] myInitialConfiguration = null;
	private Map<String, String> myParameters = new HashMap<String, String>();
	private String initialization;
	private String edgeType;
	private String direction;
	private Boolean myImageSwitch;
	private Document document;

	public XMLReader() {
	}

	public void parseXML(String fileName) throws ConfigurationException {
		DocumentBuilderFactory dodBuilderFactory = DocumentBuilderFactory.newInstance();

		try {

			File file = new File("xmlFiles/" + fileName);

			DocumentBuilder docBuilder = dodBuilderFactory.newDocumentBuilder();
			document = docBuilder.parse(file);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		Element root = document.getDocumentElement();
		if (root.getElementsByTagName("type").item(0).getTextContent().length() == 0)
			throw new ConfigurationException("No Simulation Type");
		else
			mySimulation = root.getElementsByTagName("type").item(0).getTextContent();

		edgeType = root.getElementsByTagName("edgeType").item(0).getTextContent();
		direction = root.getElementsByTagName("direction").item(0).getTextContent();
		myTitle = root.getElementsByTagName("title").item(0).getTextContent();
		myAuthor = root.getElementsByTagName("author").item(0).getTextContent();
		myRows = Integer.parseInt(root.getElementsByTagName("row").item(0).getTextContent());
		myColumns = Integer.parseInt(root.getElementsByTagName("column").item(0).getTextContent());

		myImageSwitch = (Boolean.valueOf(root.getElementsByTagName("imageSwitch").item(0).getTextContent()));
		myNeighbors = root.getElementsByTagName("neighbors").item(0).getTextContent();

		myStates = root.getElementsByTagName("states").item(0).getTextContent().split(" ");
		setMyShape(root.getElementsByTagName("shapeOfCell").item(0).getTextContent());
		populateParameterList(root);
		initialization = root.getElementsByTagName("initialization").item(0).getAttributes().getNamedItem("type")
				.getNodeValue();
		if (initialization.equals("random"))
			createRandomInitialGrid();
		else if (initialization.equals("probability"))
			createProbInitialGrid(root);
		else
			populateInitialConfigArray(root);

	}

	private void populateParameterList(Element root) {
		NodeList parameterList = root.getElementsByTagName("parameters").item(0).getChildNodes();

		for (int node = 0; node < parameterList.getLength(); node++) {
			Node n = parameterList.item(node);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				myParameters.put(n.getNodeName(), n.getTextContent());
			}

		}
	}

	private void populateInitialConfigArray(Element root) {
		String[] initialConfigArray = root.getElementsByTagName("initial").item(0).getTextContent().split(" ");

		int configCounter = 0;
		myInitialConfiguration = new String[myRows][myColumns];
		for (int r = 0; r < myRows; r++) {
			for (int c = 0; c < myColumns; c++) {
				myInitialConfiguration[r][c] = initialConfigArray[configCounter];
				configCounter++;

			}
		}

	}

	private void createRandomInitialGrid() {
		myInitialConfiguration = new String[myRows][myColumns];
		for (int r = 0; r < myRows; r++) {
			for (int c = 0; c < myColumns; c++) {
				int i = new Random().nextInt(myStates.length);
				myInitialConfiguration[r][c] = myStates[i];

			}
		}
	}

	private void createProbInitialGrid(Element root) {
		NodeList parameterList = root.getElementsByTagName("initialization").item(0).getChildNodes();
		double[] distb = new double[myStates.length];
		int index = 0;
		double past = 0;
		for (int node = 0; node < parameterList.getLength(); node++) {
			Node n = parameterList.item(node);
			if (n.getNodeType() == Node.ELEMENT_NODE) {

				distb[index] = past + Double.parseDouble(n.getTextContent());
				past = past + Double.parseDouble(n.getTextContent());
				index++;
				if (index == distb.length)
					break;
			}
		}

		myInitialConfiguration = new String[myRows][myColumns];
		for (int r = 0; r < myRows; r++) {
			for (int c = 0; c < myColumns; c++) {
				double d = Math.random();
				int i = 0;
				while (d > distb[i]) {
					i++;
				}
				myInitialConfiguration[r][c] = myStates[i];

			}
		}

	}

	public String getMySimulation() {
		return mySimulation;
	}

	public String getEdgeType() {
		return edgeType;
	}

	public String getDirection() {
		return direction;
	}

	public String getMyInitialization() {
		return initialization;
	}

	public String getMyTitle() {
		return myTitle;
	}

	public String getMyAuthor() {
		return myAuthor;
	}

	public int getMyRows() {
		return myRows;
	}

	public int getMyColumns() {
		return myColumns;
	}

	public String getMyNeighbors() {
		return myNeighbors;
	}

	public String[] getMyStates() {
		return myStates;
	}

	public String[][] getMyInitialConfiguration() {
		return myInitialConfiguration;
	}

	public Map<String, String> getMyParameters() {
		return myParameters;
	}

	public String getMyShape() {
		return myShape;
	}

	public void setMyShape(String myShape) {
		this.myShape = myShape;
	}

	public Boolean getMyImageSwitch() {
		return myImageSwitch;
	}

}
