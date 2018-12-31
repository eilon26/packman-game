package GUI;

import java.util.Iterator;

import GIS.GIS_element;
import GIS.fruit;
import Geom.Point3D;
import Geom.geom;
import Robot.Play;
import algorithms.closet_fruit;

public class auto_main_control extends Thread {
	private Play play1;
	private JPanelWithBackground jpanel;

	public auto_main_control(Play play1,JPanelWithBackground jpanel) {
		this.play1 = play1;
		this.jpanel = jpanel;
	}

	public void run() {
		while(play1.isRuning()) {
			closet_fruit CF = new closet_fruit(jpanel.getGB(), jpanel);
			Thread control = new auto_control_thread2(jpanel, CF);
			control.start();//go to the closet fruit
			try { control.join();
			} catch (InterruptedException e1) {}

		}
	}


		
		
}
