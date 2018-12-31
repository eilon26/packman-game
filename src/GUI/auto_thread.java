package GUI;

import java.util.Iterator;

import GIS.GIS_element;
import GIS.fruit;
import Geom.Point3D;
import Geom.geom;
import algorithms.closet_fruit;

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
		while (jpanel.getPlay1().isRuning()&&(counter <= sec*1000)&&(jpanel.getGB().contains(CF.getDst_fruit()))) {
			this.jpanel.repaint();
			try {Thread.sleep((long) (100));
			} catch (InterruptedException e) {}
			counter+=100;
		}
	}
}
