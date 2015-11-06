/**
 *
 */
package GUI.GridControl;

import java.util.Map;

import Cell.Simulation;
import GUI.ConfigurationException;
import Main.XMLReader;

/**
 *
 * @author Sally Al
 *
 */
public class SpecificIntialStates extends GridInitialization {

	@Override
	public Simulation[][] createSimulationArray(XMLReader xmlReader) {
		int rows = xmlReader.getMyRows();
		int columns = xmlReader.getMyColumns();
		String simulationName = xmlReader.getMySimulation();
		String[][] arrayFromXml = new String[rows][columns];
		arrayFromXml = xmlReader.getMyInitialConfiguration();
		Simulation[][] cellArray = new Simulation[rows][columns];

		for (int i = 0; i < rows; i++) {

			for (int j = 0; j < columns; j++) {

				PossibleSpecificStates possibleStates = new PossibleSpecificStates();
				Map<String, Simulation> map = possibleStates.createGOlStates();
				Simulation simulation = map.get(simulationName);
				simulation.setMyLocation(new int[] { i, j });
				try {
					simulation.setMyState(arrayFromXml[i][j]);
				} catch (ConfigurationException e) {
					e.printStackTrace();
				}
				cellArray[i][j] = simulation;

			}

		}

		return cellArray;
	}

}
