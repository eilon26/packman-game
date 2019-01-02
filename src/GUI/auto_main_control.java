package GUI;


import java.util.ArrayList;

import Coords.MyCoords;
import GIS.fruit;
import GIS.pachman;
import Geom.Point3D;
import Geom.geom;
import Robot.Play;
import algorithms.closet_fruit;
import algorithms.deal_with_fruit;
import algorithms.deal_with_ghost;
import algorithms.deal_with_pachman;
import algorithms.fruit_group_layer;

public class auto_main_control extends Thread {
	private Play play1;
	private JPanelWithBackground jpanel;
	// parameter for the terms
	private boolean continue_to_next_fruit=true;
	private boolean isGointToKill = false;
	private char isCloseFruit = 'N';
	private boolean isTooMuchTime = false;
	private Point3D Pachman_on_way = null;
	
	public auto_main_control(Play play1,JPanelWithBackground jpanel) {
		this.play1 = play1;
		this.jpanel = jpanel;
	}

	public void run() {
		closet_fruit CF;
		ArrayList<fruit> fruitRelatedToPachmans;
		//*********************the loop search for the next fruit by the algorithm***************************************
		while(play1.isRuning()&&(jpanel.getGB().getFruits().size()!=0)) {
			fruit_group_layer FGL = new fruit_group_layer(jpanel.getGB());			
			fruitRelatedToPachmans = FGL.fruits_related_to_pachmans();
			//take the closet fruit without pachman close to. otherwise take the closet fruit
			if ((fruitRelatedToPachmans.size()<jpanel.getGB().getFruits().size())&&(isCloseFruit =='N'))
				 CF = new closet_fruit(jpanel.getGB(), jpanel,fruitRelatedToPachmans);
			else  {
				if (isCloseFruit =='Y') isCloseFruit = 'F';
				CF = new closet_fruit(jpanel.getGB(), jpanel,null);
			}

			//****************go through the the Points by dijkstra to the destination fruit********************************
			if (CF.getMinRoute()!=null) {
				Point3D player_loc = ((geom)jpanel.getGB().getPlayer().getGeom()).getP();//global location
				Point3D curr_dst;
				MyCoords MC = new MyCoords();
				double[] AED;

				while (jpanel.getPlay1().isRuning()&&(!CF.getMinRoute().isEmpty())&&(jpanel.getGB().getFruits().contains(CF.getDst_fruit()))&&continue_to_next_fruit&&!isGointToKill&&((isCloseFruit == 'N')||(isCloseFruit == 'F'))&&!isTooMuchTime&&(Pachman_on_way==null)) {
					curr_dst = CF.getMinRoute().pop();//global point
					player_loc = ((geom)jpanel.getGB().getPlayer().getGeom()).getP();//global location
					AED = MC.azimuth_elevation_dist(player_loc, curr_dst);
					jpanel.setAzimuth(AED[0]);
					double sec_to_curr_dst = AED[2]/20;

//					} catch (InterruptedException e) {}
					//****************go straight to point in dijkstra way ********************************
					int counter = 0;
					while (jpanel.getPlay1().isRuning()&&(counter <= sec_to_curr_dst*1000)&&(jpanel.getGB().getFruits().contains(CF.getDst_fruit()))&&(continue_to_next_fruit(CF))&&(!deal_with_ghost.isGoingToKill(jpanel,this))&&(deal_with_fruit.is_close_enough(jpanel, CF.getDst_fruit(),this)=='N')&&!isTooMuchTime&&(deal_with_pachman.isclose(jpanel, this)==null)) {
						this.jpanel.repaint();
						try {Thread.sleep((long) (50));
						} catch (InterruptedException e) {}
						counter+=100;
						if (counter>3000) isTooMuchTime = true;
					}
					if (isGointToKill) deal_with_ghost.Action(jpanel);
					if (Pachman_on_way!=null) deal_with_pachman.Action(jpanel, Pachman_on_way);
					//****************go straight to point in dijkstra way ********************************
				}
				if (continue_to_next_fruit==false) continue_to_next_fruit = true;
				if (isGointToKill == true) isGointToKill = false;
				if (Pachman_on_way!=null) Pachman_on_way=null;
				if (isCloseFruit =='F') isCloseFruit = 'N';
				if (isTooMuchTime) isTooMuchTime = false;
			}
			//****************go through the the Points by dijkstra to the destination fruit*******************************
		}
		//*********************the loop search for the next fruit by the algorithm***************************************
		jpanel.repaint();
	}

	private boolean continue_to_next_fruit(closet_fruit CF) {
		fruit_group_layer FGL = new fruit_group_layer(jpanel.getGB());
		ArrayList<fruit> fruitRelatedToPachmans = FGL.fruits_related_to_pachmans();
		boolean pachmans_Fruits_Contain_Dst_Fruit = fruitRelatedToPachmans.contains(CF.getDst_fruit());
		boolean pachmans_Fruits_Smaller_All_Fruits = fruitRelatedToPachmans.size()<jpanel.getGB().getFruits().size(); 
		boolean continueNextFruit = (!pachmans_Fruits_Contain_Dst_Fruit||!pachmans_Fruits_Smaller_All_Fruits);
		if (isCloseFruit =='F') continueNextFruit=true;
		continue_to_next_fruit = continueNextFruit;
		return continueNextFruit;
	}
	public boolean isGointToKill() {
		return isGointToKill;
	}

	//***********************getters and setters************************
	public void setGointToKill(boolean isGointToKill) {
		this.isGointToKill = isGointToKill;
	}
	
	public void setCloseFruit(char isCloseFruit) {
		this.isCloseFruit = isCloseFruit;
	}
	
	public char isCloseFruit() {
		return this.isCloseFruit;
	}

	public void setPachman_on_way(Point3D pachman_on_way) {
		Pachman_on_way = pachman_on_way;
	}
	
		
}
