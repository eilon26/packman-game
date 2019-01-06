package GUI;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 * the class that responsible on the GUI
 * @author EILON
 *
 */
public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private MainJPanel jpanel;
	/**
	 * the empty constructor for MyFrame 
	 */
	public MyFrame() 
	{
		
		initGUI();		
		this.jpanel = new MainJPanel();
		this.getContentPane().add(this.jpanel );//add the jpanel to "MyFrame"
		
		}
		/**
		 * initialization the buttons of the GUI
		 */
		private void initGUI() 
		{
			MenuBar menuBar = new MenuBar();
			Menu file = new Menu("file");
			Menu start = new Menu("start");
			Menu dataBase = new Menu("dataBase");
			Menu clearScreen = new Menu("clear screen");
			
			MenuItem load = new MenuItem("load csv file");
			load.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							jpanel.load(e);
						}
					}
					);
			MenuItem startManual = new MenuItem("start manualy");
			startManual.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							jpanel.startManual(e);
						}
					}
					);
			MenuItem startAuto = new MenuItem("start auto");
			startAuto.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							jpanel.startAuto(e);
						}
					}
					);
			MenuItem viewDataBase = new MenuItem("view dataBase");
			viewDataBase.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							jpanel.viewDataBase(e);
						}
					}
					);
			MenuItem clear = new MenuItem("clear");
			clear.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							jpanel.clear(e);
						}
					}
					);
			menuBar.add(file);
			menuBar.add(start);
			menuBar.add(dataBase);
			menuBar.add(clearScreen);
			
			file.add(load);
			start.add(startManual);
			start.add(startAuto);
			dataBase.add(viewDataBase);
			clearScreen.add(clear);
			this.setMenuBar(menuBar);
	}


	//***********************getters and setters************************
	
	public MainJPanel getJpanel() {
		return jpanel;
	}

	//**************************main function*******************************
	/**
	 * the main function that start all the game
	 * @param args
	 */
	public static void main(String[] args)
	{
		MyFrame window = new MyFrame();
		window.setVisible(true);
		//window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		window.setSize(1200,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
