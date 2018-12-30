package FileFormat;

import java.util.ArrayList;

import GIS.GameBoard;

public class play2GB {
	GameBoard GB;
	
	public play2GB(ArrayList<String> board_data) {
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
