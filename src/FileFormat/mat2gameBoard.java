package FileFormat;

import GIS.GameBoard;
/**
 * the class take the csv2mat object and create GameBoard object
 * @author EILON
 *
 */
public class mat2gameBoard {
	private GameBoard GB;
	/**
	 * the consructor convert the csv2mat object and create GameBoard object
	 * @param c2m the GameBoard object
	 */
	public mat2gameBoard(csv2mat c2m) {
		this.GB = new GameBoard(c2m.getG());
	}
	/**
	 * 
	 * @return the GameBord object of the class
	 */
	public GameBoard getGB() {
		return GB;
	}
}
