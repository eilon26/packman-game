package GUI;

import java.util.ArrayList;

import GIS.fruit;
import algorithms.closet_fruit;
import algorithms.deal_with_fruit;
import algorithms.deal_with_ghost;
import algorithms.fruit_group_layer;

public class auto_thread extends Thread{
	private JPanelWithBackground jpanel;
	private double sec;
	private int counter;
	private closet_fruit CF;

	public auto_thread (JPanelWithBackground jpanel,double sec, closet_fruit CF) {
		this.jpanel = jpanel;
		this.sec = sec;
		this.counter = 0;
		this.CF = CF;
	}

	/**
	 * auto_paint_thread run method
	 */
	public void run() {
		while (jpanel.getPlay1().isRuning()&&(counter <= sec*1000)&&(jpanel.getGB().getFruits().contains(CF.getDst_fruit()))&&(continue_to_next_fruit())&&(!deal_with_ghost.isGoingToKill(jpanel))&&(deal_with_fruit.is_close_enough(jpanel, CF.getDst_fruit())=='N')) {
			this.jpanel.repaint();
			try {Thread.sleep((long) (50));
			} catch (InterruptedException e) {}
			counter+=100;
		}
		if (jpanel.isGointToKill()) deal_with_ghost.Action(jpanel); 
	}
	private boolean continue_to_next_fruit() {
		fruit_group_layer FGL = new fruit_group_layer(jpanel.getGB());
		ArrayList<fruit> fruitRelatedToPachmans = FGL.fruits_related_to_pachmans();
		boolean pachmans_Fruits_Contain_Dst_Fruit = fruitRelatedToPachmans.contains(CF.getDst_fruit());
		boolean pachmans_Fruits_Smaller_All_Fruits = fruitRelatedToPachmans.size()<jpanel.getGB().getFruits().size(); 
		boolean continueNextFruit = (!pachmans_Fruits_Contain_Dst_Fruit||!pachmans_Fruits_Smaller_All_Fruits);
		if (jpanel.isCloseFruit()=='F') continueNextFruit=true;
		jpanel.setContinue_to_next_fruit(continueNextFruit);
		return continueNextFruit;
	}
	
	
	
	
}
