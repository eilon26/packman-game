package GUI;

import Coords.MyCoords;

import Geom.Point3D;
import Geom.geom;
import algorithms.closet_fruit;

public class auto_control_thread2 extends Thread {
	private JPanelWithBackground jpanel;
	private closet_fruit CF;

	public auto_control_thread2(JPanelWithBackground jpanel, closet_fruit CF) {
		this.jpanel = jpanel;
		this.CF = CF;
	}
	public void run() {

		if (CF.getMinRoute()!=null) {
			Point3D player_loc = ((geom)jpanel.getGB().getPlayer().getGeom()).getP();//global location
			Point3D curr_dst;
			MyCoords MC = new MyCoords();
			double[] AED;

			while (jpanel.getPlay1().isRuning()&&(!CF.getMinRoute().isEmpty())&&(jpanel.getGB().getFruits().contains(CF.getDst_fruit()))&&jpanel.getContinue_to_next_fruit()&&!jpanel.isGointToKill()&&((jpanel.isCloseFruit()=='N')||(jpanel.isCloseFruit()=='F'))) {
				curr_dst = CF.getMinRoute().pop();//global point
				player_loc = ((geom)jpanel.getGB().getPlayer().getGeom()).getP();//global location
				AED = MC.azimuth_elevation_dist(player_loc, curr_dst);
				jpanel.setAzimuth(AED[0]);
				double sec_to_curr_dst = AED[2]/20;
				Thread auto = new auto_thread(jpanel,sec_to_curr_dst,CF);
				auto.start();
				try {auto.join();
				} catch (InterruptedException e) {}
			}
			if (jpanel.getContinue_to_next_fruit()==false) jpanel.setContinue_to_next_fruit(true);
			if (jpanel.isGointToKill() == true) jpanel.setGointToKill(false);
		}
	}
	
//	private boolean continue_to_next_fruit() {
//		fruit_group_layer FGL = new fruit_group_layer(jpanel.getGB());
//		ArrayList<fruit> fruitRelatedToPachmans = FGL.fruits_related_to_pachmans();
//		boolean pachmans_Fruits_Contain_Dst_Fruit = fruitRelatedToPachmans.contains(CF.getDst_fruit());
//		boolean pachmans_Fruits_Smaller_All_Fruits = fruitRelatedToPachmans.size()<jpanel.getGB().getFruits().size(); 
//		return !pachmans_Fruits_Contain_Dst_Fruit||!pachmans_Fruits_Smaller_All_Fruits;
//		
//	}

}
