
/*
 * Interface using JFrame
 * @Robert.Coleman
 * 9/30/2015--> Updated on 4/1/2017
 *     A Java weather parser RSS feed
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class climate_parser
{
	
	public static void main(String[] args) 
	{
		//---------The frame------------------------------------------------------//
		JFrame frame = new JFrame("Climate Interface");// Window frame title
		frame.setSize(300, 260);// Size of the window frame
		frame.setLocationRelativeTo(null);// Center the window frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X bottom to close window
		
        //------Panel---------------//
		JPanel panel = new JPanel();
		frame.add(panel);
		placeComponents(panel);
		frame.setVisible(true); // Brings the frame to life on the screen
	}
	
	private static void placeComponents(JPanel panel) 
	{
		panel.setLayout(null);	
		
		//Banner
		JLabel ban = new JLabel("-CLIMATE!-");
		Font banfont = new Font("Verdana", Font.BOLD, 40); // setting font
		ban.setFont(banfont); // calls font attributes
		ban.setForeground(Color.ORANGE);
		ban.setBounds(0,0,300,110);	
		panel.add(ban);
			
		//------------TEXT and BACKGROUND fill--------------------------------//
		panel.setBackground(Color.WHITE); // Set the color on the background
		Font font = new Font("Verdana", Font.ITALIC, 10	); // Font attributes
		
		//------------Instructions-----------//	
		JLabel Ins = new JLabel("Just click on the button below for location of weather");
		Ins.setBounds(1, 120, 500, 20); // Set the location of this in the frame
		Ins.setFont(font); // calls font attributes
		Ins.setForeground(Color.GRAY); // Set text color
		panel.add(Ins);
		
		//---------------Buttons------------------------//
				// parsing weather form PUERTO RICO
				JButton prButton = new JButton("PUERTO RICO");
				prButton.setBounds(1, 150, 150, 20); // Set the location of this in the frame
				panel.add(prButton);
				
				prButton.addActionListener(new ActionListener()// Submit button functionality 
				{
					private final static String newline = "\n"; //So can print a new line of the text output
		            public void actionPerformed(ActionEvent e)
		            {		            	
		                // PUERTO RICO CLIMA
		            	 JFrame A = new JFrame("Climate Interface");
		            	 String url = "http://w1.weather.gov/xml/current_obs/TJSJ.xml";	// Location of RSS to parse from
		                 try
		                 {   	 
		                     // string url
		                     DocumentBuilderFactory f =
		                             DocumentBuilderFactory.newInstance();
		                     DocumentBuilder b = f.newDocumentBuilder();
		                     Document doc = b.parse(url);
		                     doc.getDocumentElement().normalize();                   
		                     // loop through each item      
		                     NodeList items = doc.getElementsByTagName("current_observation");        
		                     for (int i = 0; i < items.getLength(); i++)
		                     {
		                         Node n = items.item(i);
		                         if (n.getNodeType() != Node.ELEMENT_NODE)
		                             continue;
		                         Element ex = (Element) n;
		                         NodeList titleList =
		                                         ex.getElementsByTagName("location");              
		                         Element titleElem = (Element) titleList.item(0);
		                         NodeList titleList2 =
		                                         ex.getElementsByTagName("observation_time");
		                         Element titleElem2 = (Element) titleList2.item(0);
		                         NodeList titleList3 =
		                                         ex.getElementsByTagName("temperature_string");
		                         Element titleElem3 = (Element) titleList3.item(0);
		                         NodeList titleList4 =
		                                 ex.getElementsByTagName("relative_humidity");
		                         Element titleElem4 = (Element) titleList4.item(0);
		                         NodeList titleList5 =
		                                 ex.getElementsByTagName("wind_string");
		                         Element titleElem5 = (Element) titleList5.item(0);
		                         NodeList titleList6 =
		                                 ex.getElementsByTagName("weather");
		                         Element titleElem6 = (Element) titleList6.item(0);
		                         A.setLayout(null);
		                         
		                       JTextArea txt = new JTextArea();
		                       JTextArea txt2 = new JTextArea();
		                       JTextArea txt3 = new JTextArea();
		                       txt.setBounds(1, 80, 500, 120);
		                       A.add(txt);
		                       txt2.setBounds(1, 1, 500, 80);
		                       A.add(txt2);
		                       txt3.setBounds(1, 200, 500, 100);
		                       A.add(txt3); 
		                       txt.setEditable(false);//disable editing output text
		                       Font font = new Font("Verdana", Font.BOLD, 13);
		                       txt.setFont(font);
		                       txt.setForeground(Color.BLACK);
		                       //txt.setBackground(Color.BLACK);
		                       txt2.setEditable(false);
		                       Font font2 = new Font("3D Thirteen Pixel Fonts", Font.PLAIN, 70); 
		                       txt2.setFont(font2);
		                       txt2.setForeground(Color.RED);
		                       //txt2.setBackground(Color.BLACK);  
		                       txt3.setEditable(false);
		                       Font font3 = new Font("Verdana", Font.PLAIN, 13);
		                       txt3.setFont(font3);
		                       txt3.setForeground(Color.WHITE);
		                       txt3.setBackground(Color.BLUE);
		                       txt2.setText("PUERTO RICO");  
		                       Node titleNode = titleElem.getChildNodes().item(0);
		                       Node titleNode2 = titleElem2.getChildNodes().item(0);
		                       Node titleNode3 = titleElem3.getChildNodes().item(0);
		                       Node titleNode4 = titleElem4.getChildNodes().item(0);
		                       Node titleNode5 = titleElem5.getChildNodes().item(0);
		                       Node titleNode6 = titleElem6.getChildNodes().item(0);
		                       txt.setText(titleNode.getNodeValue() 
		                    		   + newline + titleNode2.getNodeValue()
		                    		   + newline + "temperature = " + titleNode3.getNodeValue()
		                    		   + newline + "Humidity = " + titleNode4.getNodeValue()
		                    		   + newline + "Winds = " + titleNode5.getNodeValue()
		                    		   + newline + "Weather = " + titleNode6.getNodeValue());
		                    txt3.setText("__________________________ weather parser RSS feed_____");       
		                    
		                  A.setSize(500,260); // Sets the size of the window
		                  A.setLocationRelativeTo(null);// Center the window frame
		                  A.setVisible(true);// display frame
		                  A.setResizable(false);// Makes window not re-sizable
		                  A.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// DISPOSE_ON_CLOSE is so the main frame stays open only pop up one closes 
		                 }      
		                 }
		                 catch (Exception ex)
		                 {
		                     ex.printStackTrace();     
		                 }          
		            		             
		            }
		        });      
				
				// parsing weather form AUSTIN TX
				JButton txButton = new JButton("AUSTIN TX");
				txButton.setBounds(1, 190, 150, 20); // Set the location of this in the frame
				panel.add(txButton);
				
				txButton.addActionListener(new ActionListener()// Reset button functionality 
				{
					 private final static String newline = "\n"; // print a new line of the text output		 
				     public void actionPerformed(ActionEvent e)
				     {
				    	 // AUSTIN TX CLIMA
				    	 JFrame A = new JFrame("Climate Interface");
				    	 String url = "http://w1.weather.gov/xml/current_obs/KAUS.xml";	// Location of RSS to parse from
				         try
				         {   	 
				             // string url
				             DocumentBuilderFactory f =
				                     DocumentBuilderFactory.newInstance();
				             DocumentBuilder b = f.newDocumentBuilder();
				             Document doc = b.parse(url);
				             doc.getDocumentElement().normalize();                   
				             // loop through each item      
				             NodeList items = doc.getElementsByTagName("current_observation");        
				             for (int i = 0; i < items.getLength(); i++)
				             {
				                 Node n = items.item(i);
				                 if (n.getNodeType() != Node.ELEMENT_NODE)
				                     continue;
				                 Element ex = (Element) n;
				                 NodeList titleList =
				                                 ex.getElementsByTagName("location");              
				                 Element titleElem = (Element) titleList.item(0);
				                 NodeList titleList2 =
				                                 ex.getElementsByTagName("observation_time");
				                 Element titleElem2 = (Element) titleList2.item(0);
				                 NodeList titleList3 =
				                                 ex.getElementsByTagName("temperature_string");
				                 Element titleElem3 = (Element) titleList3.item(0);
				                 NodeList titleList4 =
				                         ex.getElementsByTagName("relative_humidity");
				                 Element titleElem4 = (Element) titleList4.item(0);
				                 NodeList titleList5 =
				                         ex.getElementsByTagName("wind_string");
				                 Element titleElem5 = (Element) titleList5.item(0);
				                 NodeList titleList6 =
				                         ex.getElementsByTagName("weather");
				                 Element titleElem6 = (Element) titleList6.item(0);
				                 A.setLayout(null);
				                 
				               JTextArea txt = new JTextArea();
				               JTextArea txt2 = new JTextArea();
				               JTextArea txt3 = new JTextArea();
				               txt.setBounds(1, 80, 500, 120);
				               A.add(txt);
				               txt2.setBounds(1, 1, 500, 80);
				               A.add(txt2);
				               txt3.setBounds(1, 200, 500, 100);
				               A.add(txt3); 
				               txt.setEditable(false);//disable editing output text
				               Font font = new Font("Verdana", Font.BOLD, 13);
				               txt.setFont(font);
				               txt.setForeground(Color.BLACK);
				               //txt.setBackground(Color.BLACK);
				               txt2.setEditable(false);
				               Font font2 = new Font("Sailor Stitch", Font.PLAIN, 40);
				               txt2.setFont(font2);
				               txt2.setForeground(Color.ORANGE);
				               //txt2.setBackground(Color.BLACK);  
				               txt3.setEditable(false);
				               Font font3 = new Font("Verdana", Font.PLAIN, 13);
				               txt3.setFont(font3);
				               txt3.setForeground(Color.WHITE);
				               txt3.setBackground(Color.ORANGE);
				               txt2.setText("AUSTIN TX");  
				               Node titleNode = titleElem.getChildNodes().item(0);
				               Node titleNode2 = titleElem2.getChildNodes().item(0);
				               Node titleNode3 = titleElem3.getChildNodes().item(0);
				               Node titleNode4 = titleElem4.getChildNodes().item(0);
				               Node titleNode5 = titleElem5.getChildNodes().item(0);
				               Node titleNode6 = titleElem6.getChildNodes().item(0);
				               txt.setText(titleNode.getNodeValue() // Body!
				            		   + newline + titleNode2.getNodeValue()
				            		   + newline + "temperature = " + titleNode3.getNodeValue()
				            		   + newline + "Humidity = " + titleNode4.getNodeValue()
				            		   + newline + "Winds = " + titleNode5.getNodeValue()
				            		   + newline + "Weather = " + titleNode6.getNodeValue());
				            txt3.setText("_____________________________ weather parser RSS feed_____");
				         
				          A.setSize(500,260); // sets the size of the window
				          A.setLocationRelativeTo(null);// Center the window frame
				          A.setVisible(true);// display frame
				          A.setResizable(false);//makes window not re-sizable
				          A.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				         }      
				         }
				         catch (Exception ex)
				         {
				             ex.printStackTrace();     
				         }            
				     }
				 }); 	
	}

}