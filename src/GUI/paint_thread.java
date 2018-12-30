package GUI;
/**'
 * the class responsible to update the situation on the screen every "sec" seconds
 * @author EILON
 *
 */
public class paint_thread extends Thread {
	private JPanelWithBackground jpanel;

	public paint_thread (JPanelWithBackground jpanel) {
		this.jpanel = jpanel;
	}
	/**
	 * paint_thread run method
	 */
	public void run() {
		
		while(jpanel.get_type()!='F') {
			try {Thread.sleep((long) (100));
			} catch (InterruptedException e) {}
			this.jpanel.repaint();
		}
	}

}
