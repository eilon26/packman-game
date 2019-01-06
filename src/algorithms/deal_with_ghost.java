package algorithms;

import java.util.Iterator;

import Coords.MyCoords;
import GIS.Ghost;
import GUI.MainJPanel;
import GUI.auto_main_control;
import Geom.Point3D;
import Geom.geom;
/**
 * the class responsible on the case when during the movement of the player he pass close to a ghost and
 * the are going to crash. so the class make it not happen 
 * @author EILON
 *
 */
public class deal_with_ghost {
	static MyCoords MC = new MyCoords();
	final static int MaxDiffAzimuth = 30;
	final static int AddtoPlayerAzimuth = 70;
	final static int AmountOfRotate = 5;
	final static int disOfClose = 10;
	/**
	 * a static function that check if the player and the ghost are going to crash
	 * @param jpanel MainJPanel parameter
	 * @param thread auto_main_control parameter
	 * @return true if the player and the ghost are going to crash,otherwise false.
	 */
	public static boolean isGoingToKill(MainJPanel jpanel, auto_main_control thread) {
		Iterator<Ghost> IterGhost = jpanel.getGB().getGhosts().iterator();
		while (IterGhost.hasNext()) {
			Ghost curr = IterGhost.next();
			Point3D Ghost_loc = ((geom)curr.getGeom()).getP();
			Point3D player_loc = ((geom)jpanel.getGB().getPlayer().getGeom()).getP();
			if ((MC.distance3d(player_loc, Ghost_loc)<disOfClose)&&(isOppositeAzimuth(player_loc, Ghost_loc, jpanel))) {
				thread.setGointToKill(true);
				return true;
			}
		}
		return false;
	}
	/**
	 * check if the player azimuth and the ghost azimuth are opposite. 
	 * it is let a range of "MaxDiffAzimuth" degrees
	 * @param player_loc Point3D parameter
	 * @param Ghost_loc Point3D parameter
	 * @param jpanel MainJPanel parameter
	 * @return true if the player azimuth and the ghost azimuth are opposite.
	 * it is let a range of "MaxDiffAzimuth" degrees
	 */
	private static boolean isOppositeAzimuth(Point3D player_loc, Point3D Ghost_loc, MainJPanel jpanel) {
		double player_azimuth = jpanel.getAzimuth();
		double ghost_azimuth = MC.azimuth_elevation_dist(Ghost_loc, player_loc)[0];
		double diff = Math.min(player_azimuth, ghost_azimuth)+180-Math.max(player_azimuth, ghost_azimuth);
		
		if (Math.abs(diff)<=MaxDiffAzimuth) return true;
		else return false;
	}
/**
 * in a case that the player and the ghost are going to crash this function
 * take care of the detour of the player such that they will not crash
 * @param jpanel MainJPanel parameter
 */
	public static void Action(MainJPanel jpanel) {
		double newAzimuth = jpanel.getAzimuth()+AddtoPlayerAzimuth;
		if (newAzimuth >=360) newAzimuth= jpanel.getAzimuth()-AddtoPlayerAzimuth;
		jpanel.setAzimuth(newAzimuth);
		for (int i=0;i<AmountOfRotate;i++) {
			jpanel.repaint();
			try {Thread.sleep((long) (100));
			} catch (InterruptedException e) {}
		}
	}

}
