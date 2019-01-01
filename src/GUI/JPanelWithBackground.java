package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Coords.MyCoords;
import FileFormat.play2GB;
import GIS.GameBoard;
import GIS.Ghost;
import GIS.ariel_map;
import GIS.box;
import GIS.fruit;
import GIS.pachman;
import Geom.Point3D;
import Geom.geom;
import Robot.Play;
import algorithms.starting_point;


public class JPanelWithBackground extends JPanel implements MouseListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GameBoard GB;
	//private BufferedImage myImage;
	JFrame j = new JFrame();
	private ariel_map m = null;
	private BufferedImage imgP = null;
	private BufferedImage imgF = null;
	private BufferedImage imgG = null;
	private BufferedImage imgPlayer = null;
	private double azimuth;
	private Play play1;
	private int x;
	private int y;
	private char type; 	
	private Image backgroundImage;
	// parameter for the painting
	private boolean continue_to_next_fruit=true;
	private boolean isGointToKill = false;
	private char isCloseFruit = 'N';

	public JPanelWithBackground() {
		this.GB = new GameBoard();
		this.play1 = null;
		this.azimuth = 0;
		this.type= 'N';
		this.m = new ariel_map(this);
		try {	this.backgroundImage = ImageIO.read(new File(m.getPath()));
		} catch (IOException e) {}
		this.addMouseListener(this); 
		try {
			imgP = ImageIO.read(new File("pachman.png"));
		} catch (IOException e) {}
		try{ imgF = ImageIO.read(new File("fruit.png"));
		} catch (IOException ex) {}
		try{ imgG = ImageIO.read(new File("Ghost.jpg"));
		} catch (IOException ex) {}
		try{ imgPlayer = ImageIO.read(new File("player.jpg"));
		} catch (IOException ex) {}
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw the background image.
		g.drawImage(backgroundImage, 0, 0,this.getWidth(), this.getHeight(), this);

		Graphics2D g2d = (Graphics2D) g;
		int rP = 30;
		int rF = 23;
		int rG = 30;
		int rPl = 30;
		//g2d.drawImage(myImage, 0, 0, this.getWidth(), this.getHeight(), this);
		//			if(x!=-1 && y!=-1 && type!='N')
		//			{
		//			i++;
		if ((type=='S')||(type=='A')) {
			play1.rotate(azimuth);
			ArrayList<String> board_data = play1.getBoard();
			//					this.GB = new play2GB(board_data).getGB();//update the GB
			this.GB.update_GameBoard(board_data);
			String info = play1.getStatistics();// get the current score of the game
			System.out.println(info);
			//					if (i%100==0)printMat();
			//	if (!isContainFruits()||(isTimeEnd())) {
			if (!play1.isRuning()) {
				type='F';
				JOptionPane.showMessageDialog(null, "end of game your statistics is: "+info);
			}
		}
		//				if (type=='M') {
		//
		//				}
		//				x=y=-1;
		//			}

		try {//draw the characters

			Point3D player_pixel_point = m.global2pixel(((geom)this.GB.getPlayer().getGeom()).getP());
			g.drawImage(imgPlayer,(int)(player_pixel_point.x()- (rPl / 2)),(int) (player_pixel_point.y()- (rPl / 2)), rPl, rPl, null);

			//draw the fruit
			Iterator<fruit> IterFruit = GB.getFruits().iterator();
			while (IterFruit.hasNext()) {
				fruit curr = null;
				try {curr = IterFruit.next();}
				catch (java.util.ConcurrentModificationException e) {}

				Point3D curr_pixel_point = m.global2pixel(((geom)((fruit)curr).getGeom()).getP());
				g.drawImage(imgF,(int)(curr_pixel_point.x()- (rF / 2)),(int) (curr_pixel_point.y()- (rF / 2)), rF, rF,null);
			}
			//draw the Ghost
			Iterator<Ghost> IterGhost = GB.getGhosts().iterator();
			while (IterGhost.hasNext()) {
				Ghost curr = null;
				try {curr = IterGhost.next();}
				catch (java.util.ConcurrentModificationException e) {}
				Point3D curr_pixel_point = m.global2pixel(((geom)((Ghost)curr).getGeom()).getP());
				g.drawImage(imgG,(int)(curr_pixel_point.x()- (rG / 2)),(int) (curr_pixel_point.y()- (rG / 2)), rG, rG, null);
			}
			//draw the pachman
			Iterator<pachman> IterPach = GB.getPachmans().iterator();
			while (IterPach.hasNext()) {
				pachman curr = null;
				try {curr = IterPach.next();}
				catch (java.util.ConcurrentModificationException e) {}
				Point3D curr_pixel_point = m.global2pixel(((geom)((pachman)curr).getGeom()).getP());
				g.drawImage(imgP,(int)(curr_pixel_point.x()- (rP / 2)),(int) (curr_pixel_point.y()- (rP / 2)), rP, rP, null);
			}

			//draw the boxes
			int width;
			int height;
			Iterator<box> IterBox = GB.getBox_set().iterator();
			while (IterBox.hasNext()) {
				box curr = (box)IterBox.next();
				Point3D p1 = m.global2pixel(curr.getP1());//in pixel
				Point3D p2 = m.global2pixel(curr.getP2());//in pixel

				if (p2.x()<p1.x()) {
					swapX(p2,p1);
				}
				if(p2.y()<p1.y()) {
					swapY(p2,p1);
				}
				width = (int) (p2.x()-p1.x());
				height = (int) (p2.y()-p1.y());
				g2d.setColor(Color.BLACK);
				g2d.drawRect((int)p1.x(),(int)p1.y() , width, height);
				g2d.fillRect((int)p1.x(),(int)p1.y() , width, height);
			}
		}
		catch(Exception e){}
	}
	//********************private functions**************************
	private void swapX(Point3D a,Point3D b) {
		double temp = a.x();
		a.set_x(b.x());
		b.set_x(temp);
	}
	private void swapY(Point3D a,Point3D b) {
		double temp = a.y();
		a.set_y(b.y());
		b.set_y(temp);
	}
	/**
	 * get a global point and return true if the point is inside some box 
	 * @param player_loc
	 * @param box_set
	 */
	public static boolean isInsideBox(Point3D player_loc,ArrayList<box> box_set){
		boolean insideBox = false;
		Iterator<box> IterBox = box_set.iterator();
		while (IterBox.hasNext()&&!insideBox) {
			box curr_box = IterBox.next();
			if ((player_loc.x()>=curr_box.getP1().x())&&(player_loc.x()<=curr_box.getP2().x())) {
				if ((player_loc.y()>=curr_box.getP1().y())&&(player_loc.y()<=curr_box.getP2().y())) {
					insideBox = true;
				}
			}
		}
		return insideBox;

	}
	//			private void printMat() {
	//				GB2mat a = new GB2mat(this);
	//				for (int r =0;r<this.getHeight();r++) {
	//				for (int c=0;c<this.getWidth();c++) {
	//					System.out.print(a.getMat()[r][c]);
	//				}
	//				System.out.println();
	//				}
	//			}

	//**************************mouse listener function****************
	@Override
	public void mouseClicked(MouseEvent arg0) {
		x = arg0.getX();
		y = arg0.getY();
		if (type =='S') {
			Point3D dst_point = m.pixel2global(new Point3D(x,y));
			MyCoords MC = new MyCoords();
			double[] AED = MC.azimuth_elevation_dist(((geom)this.GB.getPlayer().getGeom()).getP(), dst_point);
			this.azimuth = AED[0];
		}
		if (type =='M') {
			if (isInsideBox(this.m.pixel2global(new Point3D(x,y)), GB.getBox_set())==true) {
				JOptionPane.showMessageDialog(null, "not valid player point. enter again");
			}
			else {
				Point3D player_point = m.pixel2global(new Point3D(x,y));//global point
				play1.setInitLocation(player_point.y(),player_point.x());//set player location
				GB.getPlayer().set_Geom(new geom(player_point));
				type='N';
				repaint();

				play1.start(); 
				type = 'S';
				new paint_thread(this).start();
			}
		}

		//crossing check();
		//					Point3D dst = m.pixel2global(new Point3D(x,y));
		//					System.out.println(cross.isCross(((geom)GB.getPlayer().getGeom()).getP(), dst, GB.getBox_set()));
		//					try {
		//						Thread.sleep(2000);
		//					} catch (InterruptedException e) {
		//						// TODO Auto-generated catch block
		//						e.printStackTrace();
		//					}

		//				if (type =='A') {
		//					Point3D dst_point = m.pixel2global(new Point3D(x,y));//global point
		//					new auto_paint_thread(this,dst_point).start();
		//				}
		//***********ver1**************
		//				if (type =='A') {
		//					
		//					Point3D dst_point = m.pixel2global(new Point3D(x,y));//global point
		//					new auto_control_thread(this, dst_point).start();
		//				}
		//***********ver1**************
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	//*******************Button event function*****************
	public void load(ActionEvent e) {
		if (type=='F') {
			this.GB = new GameBoard();
			this.play1 = null;
			this.azimuth = 0;
			this.type= 'N';
			this.m = new ariel_map(this);
			try {	this.backgroundImage = ImageIO.read(new File(m.getPath()));
			} catch (IOException ex) {}
		}

		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String file_name = selectedFile.getAbsolutePath();
			//String file_name = "data/Ex4_OOP_example8.csv";
			play1 = new Play(file_name);
			ArrayList<String> board_data = play1.getBoard();
			play2GB p2BG = new play2GB(board_data);
			this.GB = p2BG.getGB();//init the gameBord with the starting data
			repaint();
			// 2) Set your ID's - of all the group members
			//					String input1 = JOptionPane.showInputDialog(null, "enter first id");
			//					String input2 = JOptionPane.showInputDialog(null, "enter second id");
			//					play1.setIDs(Long.parseLong(input1),Long.parseLong(input2));
			play1.setIDs(304950645,318875085);

		}


	}
	public void startManual(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "choose your starting point");
		type = 'M';	

	}

	public void startAuto(ActionEvent e) {
		Point3D player_point = starting_point.find(GB);//global point
		play1.setInitLocation(player_point.y(),player_point.x());//set player location
		GB.getPlayer().set_Geom(new geom(player_point));
		type='N';
		repaint();
		
		play1.start();
		type = 'A';
		new auto_main_control(play1,this).start();
	}
	public void viewDataBase(ActionEvent e) {
		data_base.main(null);
	}
	//***********************getters and setters************************

	public GameBoard getGB() {
		return GB;
	}


	public ariel_map getM() {
		return m;
	}

	public char get_type() {
		return type;
	}

	public double getAzimuth() {
		return azimuth;
	}

	public Play getPlay1() {
		return play1;
	}

	public BufferedImage getImgP() {
		return imgP;
	}

	public BufferedImage getImgF() {
		return imgF;
	}

	public BufferedImage getImgG() {
		return imgG;
	}

	public void setGB(GameBoard gB) {
		GB = gB;
	}

	public void setType(char type) {
		this.type = type;
	}

	public void setAzimuth(double azimuth) {
		this.azimuth = azimuth;
	}


	public BufferedImage getImgPlayer() {
		return imgPlayer;
	}


	public boolean getContinue_to_next_fruit() {
		return continue_to_next_fruit;
	}


	public void setContinue_to_next_fruit(boolean continue_to_next_fruit) {
		this.continue_to_next_fruit = continue_to_next_fruit;
	}


	public boolean isGointToKill() {
		return isGointToKill;
	}


	public void setGointToKill(boolean isGointToKill) {
		this.isGointToKill = isGointToKill;
	}


	public char isCloseFruit() {
		return isCloseFruit;
	}


	public void setCloseFruit(char isCloseFruit) {
		this.isCloseFruit = isCloseFruit;
	}

}