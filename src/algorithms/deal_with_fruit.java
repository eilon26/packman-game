package algorithms;

import java.util.Iterator;

import Coords.MyCoords;
import GIS.fruit;
import GUI.MainJPanel;
import GUI.auto_main_control;
import Geom.Point3D;
import Geom.geom;
/**
 * this class is responsible to take care of the case that during the movement of the player,
 *  he pass close to a fruit. so the algorithm in this class
 *  cause him to go to the close fruit and then continue with his regular path.
 * @author EILON
 *
 */
public class deal_with_fruit {
	final static int disOfClose = 100;
	static MyCoords MC = new MyCoords();

	public static char is_close_enough(MainJPanel jpanel,fruit dstFruit, auto_main_control thread) {
		if (thread.isCloseFruit()!='F') {
			Iterator<fruit> IterFruit = jpanel.getGB().getFruits().iterator();
			while (IterFruit.hasNext()) {
				fruit curr = IterFruit.next();
				Point3D fruit_loc = ((geom)curr.getGeom()).getP();
				Point3D player_loc = ((geom)jpanel.getGB().getPlayer().getGeom()).getP();
				try{if ((dstFruit != curr)&&(new advence_dijkstra(jpanel, player_loc, fruit_loc).getRouteDist()<disOfClose)) {
					thread.setCloseFruit('Y');
					return 'Y';
				}
				}catch(NullPointerException e) {
					return 'N';
				}
			}
		}
		return 'N';
	}
}
