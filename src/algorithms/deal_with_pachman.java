package algorithms;

import java.util.Iterator;

import Coords.MyCoords;
import GIS.pachman;
import GUI.JPanelWithBackground;
import GUI.auto_main_control;
import Geom.Point3D;
import Geom.geom;

public class deal_with_pachman {
	static MyCoords MC = new MyCoords();
	final static int AmountOfRotate = 5;
	final static int disOfClose = 14;
	
	public static Point3D isclose(JPanelWithBackground jpanel, auto_main_control thread) {
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
	
	public static void Action(JPanelWithBackground jpanel, Point3D pach_loc) {
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
