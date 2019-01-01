package GUI;


import java.util.ArrayList;

import GIS.fruit;
import Robot.Play;
import algorithms.closet_fruit;
import algorithms.fruit_group_layer;

public class auto_main_control extends Thread {
	private Play play1;
	private JPanelWithBackground jpanel;

	public auto_main_control(Play play1,JPanelWithBackground jpanel) {
		this.play1 = play1;
		this.jpanel = jpanel;
	}

	public void run() {
		closet_fruit CF;
		ArrayList<fruit> fruitRelatedToPachmans;
		while(play1.isRuning()&&(jpanel.getGB().getFruits().size()!=0)) {
			fruit_group_layer FGL = new fruit_group_layer(jpanel.getGB());			
			fruitRelatedToPachmans = FGL.fruits_related_to_pachmans();
			//take the closet fruit without pachman close to. otherwise take the closet fruit
//			System.out.println("fruitRelatedToPachmans "+fruitRelatedToPachmans.size());
//			System.out.println("all fruit "+jpanel.getGB().getFruits().size());
			if (jpanel.isCloseFruit()=='F') jpanel.setCloseFruit('N');
			if ((fruitRelatedToPachmans.size()<jpanel.getGB().getFruits().size())&&(jpanel.isCloseFruit()=='N'))
				 CF = new closet_fruit(jpanel.getGB(), jpanel,fruitRelatedToPachmans);
			else  {
				if (jpanel.isCloseFruit()=='Y') jpanel.setCloseFruit('F');
				CF = new closet_fruit(jpanel.getGB(), jpanel,null);
			}
			Thread control = new auto_control_thread2(jpanel, CF);
			control.start();//go to the closet fruit
			try { control.join();
			} catch (InterruptedException e1) {}

		}
		jpanel.repaint();
	}


		
		
}
