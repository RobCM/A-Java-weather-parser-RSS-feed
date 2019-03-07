package ClimateParser;

/*
 * Interface using JFrame
 * @Robert.Coleman
 * 9/30/2015--> Updated on 5/12/2018
 *     A Java weather parser RSS feed
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
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
	// Global variables
	private static String url;
	private static String imageResLink;
	// ---Main------//
	public static void main(String[] args) 
	{
		//---------The frame------------------------------------------------------//
		JFrame frame = new JFrame("Climate Interface");// Window frame title
		frame.setSize(340, 250);// Size of the window frame
		frame.setResizable(false);// Makes window not re-sizable
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
		imageResLink = "/res/titleimage.png";
		// Calling function that displays image
		ImageBanner(panel);
			
		//------------TEXT and BACKGROUND fill--------------------------------//
		panel.setBackground(Color.WHITE); // Set the color on the background
		Font font = new Font("Verdana", Font.ITALIC, 10	); // Font attributes
		
		//------------Instructions-----------//	
		JLabel Ins = new JLabel("Just click on the button below for the weather base on location");
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
		            public void actionPerformed(ActionEvent e)
		            {	
		            	// Overwrite variables
		            	url = "https://w1.weather.gov/xml/current_obs/TJSJ.xml"; // Location of RSS to parse from;
		            	imageResLink = "/res/primage.png";
		            	// Calling function!
		            	Dataparser(panel);
		            		             
		            }
		        });      
				
				// parsing weather form AUSTIN TX
				JButton txButton = new JButton("AUSTIN TX");
				txButton.setBounds(1, 190, 150, 20); // Set the location of this in the frame
				panel.add(txButton);
				
				txButton.addActionListener(new ActionListener()// Submit button functionality 
				{	 
				     public void actionPerformed(ActionEvent e)
				     {
				    	// Overwrite variables
				    	 url = "https://w1.weather.gov/xml/current_obs/KAUS.xml"; // Location of RSS to parse from;
				    	 imageResLink = "/res/ausimage.png";
			            // Calling function!
			            Dataparser(panel);			    	 
				     }
				    	 
				 }); 	
    }
	// Created function to clean the code up and avoid repeated code for parsing!
	private static void Dataparser(JPanel panel) 
	{
		String newline = "\n"; // print a new line for the text output	
		JFrame A = new JFrame("Climate Interface");
        try
        {  
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
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
                
                //setting up image on frame(can't use image function do to try catch?)
                URL Icon = climate_parser.class.getResource(imageResLink);
                JLabel background= new JLabel(new ImageIcon(Icon));
                background.setBounds(1, 1, 500, 80);
                A.add(background);      
                
              JTextArea txt = new JTextArea();
              JTextArea txt2 = new JTextArea();
              txt.setBounds(1, 80, 500, 120);
              A.add(txt);
              txt2.setBounds(1, 200, 500, 100);
              A.add(txt2); 
              txt.setEditable(false);//disable editing output text
              Font font = new Font("Verdana", Font.BOLD, 13);
              txt.setFont(font);
              txt.setForeground(Color.BLACK);
              txt2.setEditable(false);
              Font font3 = new Font("Verdana", Font.PLAIN, 13);
              txt2.setFont(font3);
              txt2.setForeground(Color.WHITE);
              txt2.setBackground(Color.GRAY);
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
           txt2.setText("__________________________ weather parser RSS feed_____");       
           
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
    // Image function
	private static void ImageBanner(JPanel panel)
	{
		//setting up image on frame as banner
        URL Icon = climate_parser.class.getResource(imageResLink);
        JLabel background= new JLabel(new ImageIcon(Icon));
        background.setBounds(0,0,370,85);
        panel.add(background);
	}
	
}
