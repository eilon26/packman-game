package FileFormat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * this class transports the data we have on our file to an arraylist.
 * @author Daniel Ventura
 * length - the number of different variables we have in each line.
 * width - the number of lines there are in the file.
 * g - the arraylist we will be using to enter the information into.
 */
public class csv2mat {
	private File file;
	private double length;
	private double width;
	private ArrayList<String[]> g;
	
	/**
	 * @return the length.
	 */
	public double getLength() {
		return length;
	}

	/**
	 * @return the width.
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @return the arraylist.
	 */
	public ArrayList<String[]> getG() {
		return g;
	}

	/**
	 * this function reads each line in the file and transport its information to g, an arraylist.
	 * @param csvFile - the location of the csv file on the computer.
	 */
	public csv2mat(String csvFile) {
		this.file = new File(csvFile);
		String line = "";
		String cvsSplitBy = ",";
		this.g = new ArrayList<String[]>();

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			//as long as there are more lines to the file keep on reading.
			while ((line = br.readLine()) != null) 
			{
				String[] userInfo = line.split(cvsSplitBy);
				//add the info to g.
				this.g.add(userInfo);
			}
			this.length = this.g.size();
			this.width=this.g.get(1).length;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File getFile() {
		return file;
	}
    
}
