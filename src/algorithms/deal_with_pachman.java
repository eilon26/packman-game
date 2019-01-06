package algorithms;

import java.util.Iterator;

import Coords.MyCoords;
import GIS.pachman;
import GUI.MainJPanel;
import GUI.auto_main_control;
import Geom.Point3D;
import Geom.geom;
/**
 * this class is responsible to take care of the case that during the movement of the player,
 *  he pass close to a pachman. so the algorithm in this class
 *  cause him to go to the close pachman and then continue with his regular path.
 * @author EILON
 *
 */
public class deal_with_pachman {
	static MyCoords MC = new MyCoords();
	final static int AmountOfRotate = 5;
	final static int disOfClose = 14;
	
	public static Point3D isclose(MainJPanel jpanel, auto_main_control thread) {
		Iterator<pachman> IterPach = jpanel.getGB().getPachmans().iterator();
		while (IterPach.hasNext()) {
			pachman curr = IterPach.next();
			Point3D pach_loc = ((geom)curr.getGeom()).getP();
			Point3D player_loc = ((geom)jpanel.getGB().getPlayer().getGeom()).getP();
			if (MC.distance3d(player_loc, pach_loc)<disOfClose) {
				thread.setPachman_on_way(pach_loc);
				return pach_loc;
			}
		}
		return null;
	}
	/**
	 * in a case that the player are close inough to a pachman this function
	 * take care of the movement of the player such that he will eat the pachan
	 * and then continue with his regular path.
	 * @param jpanel MainJPanel parameter
	 * @param pach_loc Point3D parameter
	 */
	public static void Action(MainJPanel jpanel, Point3D pach_loc) {
		Point3D player_loc = ((geom)jpanel.getGB().getPlayer().getGeom()).getP();
		double newAzimuth = MC.azimuth_elevation_dist(player_loc, pach_loc)[0];
		jpanel.setAzimuth(newAzimuth);
		for (int i=0;i<AmountOfRotate;i++) {
			jpanel.repaint();
			try {Thread.sleep((long) (100));
			} catch (InterruptedException e) {}
		}
	}
}
