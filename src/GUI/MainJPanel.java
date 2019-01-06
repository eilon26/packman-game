package GUI;

import java.awt.Color;
import java.awt.Font;
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
import GIS.*;
import Geom.Point3D;
import Geom.geom;
import Robot.Play;
import algorithms.starting_point;
/**
 * the core class of the project. it responsible to respond to the pressing on the 
 * buttons in the GUI. and fulfill their duty 
 * @author EILON
 *
 */
public class MainJPanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	private GameBoard GB;
	// private BufferedImage myImage;
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
/**
 * the empty constructor of MainJPanel
 */
	public MainJPanel() {
		this.GB = new GameBoard();
		this.play1 = null;
		this.azimuth = 0;
		this.type = 'N';
		this.m = new ariel_map(this);
		try {
			this.backgroundImage = ImageIO.read(new File(m.getPath()));
		} catch (IOException e) {
		}
		this.addMouseListener(this);
		try {
			imgP = ImageIO.read(new File(pachman.pic));
		} catch (IOException e) {
		}
		try {
			imgF = ImageIO.read(new File(fruit.pic));
		} catch (IOException ex) {
		}
		try {
			imgG = ImageIO.read(new File(Ghost.pic));
		} catch (IOException ex) {
		}
		try {
			imgPlayer = ImageIO.read(new File(player.pic));
		} catch (IOException ex) {
		}
	}
/**
 * responsible to actually move the player with the Paly.rotate function.
 *  more over it is responsible to show on the screen the characters location in real time.
 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw the background image.
		g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);

		Graphics2D g2d = (Graphics2D) g;
		int rP = 30;
		int rF = 23;
		int rG = 30;
		int rPl = 30;
		// take care player moving
		if ((type == 'S') || (type == 'A')) {
			play1.rotate(azimuth);
			ArrayList<String> board_data = play1.getBoard();
			this.GB.update_GameBoard(board_data);
			String info = play1.getStatistics();// get the current score of the game
			g.setColor(Color.WHITE);
			Font f = new Font("game", Font.BOLD, 16);
			g.setFont(f);
			g.drawString(info, 20, 20);
			//
			// if (!play1.isRuning()) {
			// type='F';
			// }
		}

		try {// draw the characters

			Point3D player_pixel_point = m.global2pixel(((geom) this.GB.getPlayer().getGeom()).getP());
			g.drawImage(imgPlayer, (int) (player_pixel_point.x() - (rPl / 2)),
					(int) (player_pixel_point.y() - (rPl / 2)), rPl, rPl, null);

			// draw the fruit
			Iterator<fruit> IterFruit = GB.getFruits().iterator();
			while (IterFruit.hasNext()) {
				fruit curr = null;
				try {
					curr = IterFruit.next();
				} catch (java.util.ConcurrentModificationException e) {
				}

				Point3D curr_pixel_point = m.global2pixel(((geom) ((fruit) curr).getGeom()).getP());
				g.drawImage(imgF, (int) (curr_pixel_point.x() - (rF / 2)), (int) (curr_pixel_point.y() - (rF / 2)), rF,
						rF, null);
			}
			// draw the Ghost
			Iterator<Ghost> IterGhost = GB.getGhosts().iterator();
			while (IterGhost.hasNext()) {
				Ghost curr = null;
				try {
					curr = IterGhost.next();
				} catch (java.util.ConcurrentModificationException e) {
				}
				Point3D curr_pixel_point = m.global2pixel(((geom) ((Ghost) curr).getGeom()).getP());
				g.drawImage(imgG, (int) (curr_pixel_point.x() - (rG / 2)), (int) (curr_pixel_point.y() - (rG / 2)), rG,
						rG, null);
			}
			// draw the pachman
			Iterator<pachman> IterPach = GB.getPachmans().iterator();
			while (IterPach.hasNext()) {
				pachman curr = null;
				try {
					curr = IterPach.next();
				} catch (java.util.ConcurrentModificationException e) {
				}
				Point3D curr_pixel_point = m.global2pixel(((geom) ((pachman) curr).getGeom()).getP());
				g.drawImage(imgP, (int) (curr_pixel_point.x() - (rP / 2)), (int) (curr_pixel_point.y() - (rP / 2)), rP,
						rP, null);
			}

			// draw the boxes
			int width;
			int height;
			Iterator<box> IterBox = GB.getBox_set().iterator();
			while (IterBox.hasNext()) {
				box curr = (box) IterBox.next();
				Point3D p1 = m.global2pixel(curr.getP1());// in pixel
				Point3D p2 = m.global2pixel(curr.getP2());// in pixel

				if (p2.x() < p1.x()) {
					swapX(p2, p1);
				}
				if (p2.y() < p1.y()) {
					swapY(p2, p1);
				}
				width = (int) (p2.x() - p1.x());
				height = (int) (p2.y() - p1.y());
				g2d.setColor(Color.BLACK);
				g2d.drawRect((int) p1.x(), (int) p1.y(), width, height);
				g2d.fillRect((int) p1.x(), (int) p1.y(), width, height);
			}
		} catch (Exception e) {
		}
	}

	// ********************private functions**************************
	private void swapX(Point3D a, Point3D b) {
		double temp = a.x();
		a.set_x(b.x());
		b.set_x(temp);
	}

	private void swapY(Point3D a, Point3D b) {
		double temp = a.y();
		a.set_y(b.y());
		b.set_y(temp);
	}

	// **************************mouse listener function****************
	/**
	 * the function start after mouse was cliched.
	 * it responsible to find the start location that the user choose in the manual mode
	 * moreover it is responsible to change the direction of the player in the manual mode
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		x = arg0.getX();
		y = arg0.getY();
		// in the manual running change the azimuth by the player selections
		if (type == 'S') {
			Point3D dst_point = m.pixel2global(new Point3D(x, y));
			MyCoords MC = new MyCoords();
			double[] AED = MC.azimuth_elevation_dist(((geom) this.GB.getPlayer().getGeom()).getP(), dst_point);
			this.azimuth = AED[0];
		}
		// in the manual running set the select player locatin
		if (type == 'M') {
			if (box.isInsideBox(this.m.pixel2global(new Point3D(x, y)), GB.getBox_set()) == true) {
				JOptionPane.showMessageDialog(null, "not valid player point. enter again");
			} else {
				Point3D player_point = m.pixel2global(new Point3D(x, y));// global point
				play1.setInitLocation(player_point.y(), player_point.x());// set player location
				GB.getPlayer().set_Geom(new geom(player_point));

				play1.start();
				type = 'S';
				new paint_thread(this).start();
			}
		}
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

	// *******************Button event function*****************
	/**
	 * responsible to let the player choose the csv file to load from it the game
	 * @param e ActionEvent
	 */
	public void load(ActionEvent e) {
		// reset the game if loading after starting the game
		if ((type == 'S') || (type == 'A')) {
			this.GB = new GameBoard();
			this.play1 = null;
			this.azimuth = 0;
			this.type = 'N';
		}
		// open a window to let user choose the file to play with
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			String file_name = selectedFile.getAbsolutePath();
			// String file_name = "data/Ex4_OOP_example8.csv";
			play1 = new Play(file_name);
			play2GB p2BG = new play2GB(play1);
			this.GB = p2BG.getGB();// init the gameBord with the starting data
			repaint();

			String input1 = JOptionPane.showInputDialog(null, "enter first id");
			String input2 = JOptionPane.showInputDialog(null, "enter second id");
			play1.setIDs(Long.parseLong(input1),Long.parseLong(input2));
		}
	}

	/**
	 * this function responsible to start the manual game and to to tell the player to choose his starting point.
	 * 
	 * the best score that we succeed to get in example 8 is 41.
	 * @param e ActionEvent parameter          
	 */
	public void startManual(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "choose your starting point");
		type = 'M';
	}
/**
 * this function is responsible to start the auto game by choose the starting point of player 
 * and by start auto_main_control.
 * @param e ActionEvent
 */
	public void startAuto(ActionEvent e) {
		Point3D player_point = starting_point.find(GB, this);// global point
		play1.setInitLocation(player_point.y(), player_point.x());// set player location
		GB.getPlayer().set_Geom(new geom(player_point));

		play1.start();
		type = 'A';
		new auto_main_control(play1, this).start();
	}
/**
 * this function is responsible to open a new window with all the information about the results that
 * other people succeed to achieve in the game. 
 * @param e ActionEvent
 */
	public void viewDataBase(ActionEvent e) {
		data_base.main(null);
	}
/**
 * this function is responsible to clear the screen and delete all the data that was collected.
 * @param e
 */
	public void clear(ActionEvent e) {
		this.GB = new GameBoard();
		this.play1 = null;
		this.azimuth = 0;
		this.type = 'N';
		repaint();
	}
	// ***********************getters and setters************************

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

}