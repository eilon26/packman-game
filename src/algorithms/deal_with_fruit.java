package algorithms;

import java.util.Iterator;

import Coords.MyCoords;
import GIS.Ghost;
import GIS.fruit;
import GUI.JPanelWithBackground;
import Geom.Point3D;
import Geom.geom;

public class deal_with_fruit {
	final static int disOfClose = 100;
	static MyCoords MC = new MyCoords();

	public static char is_close_enough(JPanelWithBackground jpanel,fruit dstFruit) {
		if (jpanel.isCloseFruit()!='F') {
			Iterator<fruit> IterFruit = jpanel.getGB().getFruits().iterator();
			while (IterFruit.hasNext()) {
				fruit curr = IterFruit.next();
				Point3D fruit_loc = ((geom)curr.getGeom()).getP();
				Point3D player_loc = ((geom)jpanel.getGB().getPlayer().getGeom()).getP();
				if ((dstFruit != curr)&&(new advence_dijkstra(jpanel, player_loc, fruit_loc).getRouteDist()<disOfClose)) {
					jpanel.setCloseFruit('Y');
					return 'Y';

				}
			}
		}
		return 'N';
	}
}
