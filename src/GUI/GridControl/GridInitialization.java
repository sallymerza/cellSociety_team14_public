/**
 *
 */
package GUI.GridControl;

import Cell.Simulation;
import Main.XMLReader;

/**
 *
 * @author Sally Al
 *
 */
public abstract class GridInitialization {

	public abstract Simulation[][] createSimulationArray(XMLReader smlReader);

}
