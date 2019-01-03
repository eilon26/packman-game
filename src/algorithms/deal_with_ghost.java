package algorithms;

import java.util.Iterator;

import Coords.MyCoords;
import GIS.Ghost;
import GUI.JPanelWithBackground;
import GUI.auto_main_control;
import Geom.Point3D;
import Geom.geom;

public class deal_with_ghost {
	static MyCoords MC = new MyCoords();
	final static int MaxDiffAzimuth = 30;
	final static int AddtoPlayerAzimuth = 60;
	final static int AmountOfRotate = 5;
	final static int disOfClose = 10;
	
	public static boolean isGoingToKill(JPanelWithBackground jpanel, auto_main_control thread) {
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
	
	private static boolean isOppositeAzimuth(Point3D player_loc, Point3D Ghost_loc, JPanelWithBackground jpanel) {
		double player_azimuth = jpanel.getAzimuth();
		double ghost_azimuth = MC.azimuth_elevation_dist(Ghost_loc, player_loc)[0];
		double diff = Math.min(player_azimuth, ghost_azimuth)+180-Math.max(player_azimuth, ghost_azimuth);
		
		if (Math.abs(diff)<=MaxDiffAzimuth) return true;
		else return false;
	}

	public static void Action(JPanelWithBackground jpanel) {
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
