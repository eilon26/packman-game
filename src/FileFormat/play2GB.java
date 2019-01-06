package FileFormat;

import java.util.ArrayList;

import GIS.GameBoard;
import Robot.Play;
/**
 * this class responsible to convert Play parameter and convert it to GameBoard parameter
 * @author EILON
 *
 */
public class play2GB {
	GameBoard GB;
	/**
	 * the constructor get a Play parameter and create a GameBoard parameter 
	 * @param play1 Play
	 */
	public play2GB(Play play1) {
		ArrayList<String> board_data = play1.getBoard();
		ArrayList<String[]> Board_Data = new ArrayList<String[]>();
		for(int i=0;i<board_data.size();i++) {
			String[] element = board_data.get(i).split(",");
			Board_Data.add(element);
		}
		this.GB = new GameBoard(Board_Data);
	}

	public GameBoard getGB() {
		return GB;
	}
}
