package GIS;
/**
 * the class represent a player in the game. it is extends from pachman 
 * @author EILON
 */
public class player extends pachman {
	public static String pic = "player.jpg";
	/**
	 * the constructor of player that get String[] with the player information 
	 * @param line String[]
	 */
	public player(String[] line) {
		super(line);
	}
}
