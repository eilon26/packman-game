package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class data_base {
	public static void main(String[] args)
	{
		String jdbcUrl="jdbc:mysql://ariel-oop.xyz:3306/oop"; //?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
		String jdbcUser="student";
		String jdbcPassword="student";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = 
					DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
			
			
			Statement statement = connection.createStatement();
			
			//select data
			String fileName = "data/Ex4_OOP_example8.csv";
			double fileNameNum = fileName.hashCode();
			String allCustomersQuery = "SELECT * FROM logs WHERE SomeDouble="+fileNameNum;
			ResultSet resultSet = statement.executeQuery(allCustomersQuery);
//			System.out.println("FirstID\t\tSecondID\tThirdID\t\tLogTime\t\t\t\tPoint\t\tSomeDouble");
			StringBuilder content = new StringBuilder("FirstID\tSecondID\tThirdID\tLogTime\t\tPoint\t\tSomeDouble\r\n");
			while(resultSet.next())
			{
				content.append(resultSet.getInt("FirstID")+"\t" +
						resultSet.getInt("SecondID")+"\t" +
						resultSet.getInt("ThirdID")+"\t" +
						resultSet.getTimestamp("LogTime") +"\t" +
						resultSet.getDouble("Point") +"\t\t" +
						resultSet.getDouble("SomeDouble")+"\r\n");
			}
			
			resultSet.close();		
			statement.close();		
			connection.close();		
		
		
		JFrame frame = new JFrame();
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setBounds(200,20,750,650);
		
		JTextArea JTA = new JTextArea();
		JTA.setBounds(0,0,frame.getWidth()-25,frame.getHeight());
		JTA.setText(content.toString());
		JTA.setEditable(false);
		
		JScrollPane JSP = new JScrollPane(JTA);
		JSP.setBounds(0,0,frame.getWidth()-40,frame.getHeight());
		frame.add(JSP);
		
		
		}
		catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("Vendor Error: " + sqle.getErrorCode());
		}
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
