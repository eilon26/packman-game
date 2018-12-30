package GUI;

public class auto_thread extends Thread{
	private JPanelWithBackground jpanel;
	private double sec;
	private int counter;

	public auto_thread (JPanelWithBackground jpanel,double sec) {
		this.jpanel = jpanel;
		this.sec = sec;
		this.counter = 0;
	}

	/**
	 * auto_paint_thread run method
	 */
	public void run() {
		while (counter <= sec*1000) {
			this.jpanel.repaint();
			try {Thread.sleep((long) (100));
			} catch (InterruptedException e) {}
			counter+=100;
		}
	}
	
}
