package GUI;
/**
 * the class extends from Thread. it responsible to update the situation on the screen every "sec" seconds.
 * it is run when the user start to play on the manual mode
 * @author EILON
 *
 */
public class paint_thread extends Thread {
	private MainJPanel jpanel;

	public paint_thread (MainJPanel jpanel) {
		this.jpanel = jpanel;
	}
	/**
	 * paint_thread run method
	 */
	public void run() {
		
		while(jpanel.getPlay1().isRuning()) {
			this.jpanel.repaint();
			try {Thread.sleep((long) (100));
			} catch (InterruptedException e) {}
			
		}
	}

}
