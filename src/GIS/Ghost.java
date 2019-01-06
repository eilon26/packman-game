package GIS;
/**
 * the class represent a ghost in the game. it is extends from pachman 
 * @author EILON
 */
public class Ghost extends pachman {
	public static String pic = "ghost.jpg";
	/**
	 * the constructor of Ghost that get String[] with the box information 
	 * @param line String[]
	 */
	public Ghost(String[] line) {
		super(line);
	}

}
