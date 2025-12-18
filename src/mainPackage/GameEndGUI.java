/**
* Lead Author(s):
* @author ian; student ID
* @author Full name; student ID
* <<Add additional lead authors here>>
*
* Other Contributors:
* Full name; student ID or contact information if not in class
* <<Add additional contributors (mentors, tutors, friends) here, with contact information>>
*
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
*
* <<Add more references here>>
*
* Version: 2025-12-17
*/
package mainPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Purpose: The reponsibility of GameEndGUI is ...
 *
 * GameEndGUI is-a ...
 * GameEndGUI is ...
 */
public class GameEndGUI extends JFrame implements ActionListener
{
	//fields
	private JLabel sprawlText;
	private JLabel stats;
	private JLabel record;
	private JPanel buttonPanel;
	private JButton restartButton;
//	private JButton quitButton;
	
	private String sprawlTextString;
	private String statString;
	private String recordString;
	
	private PrintWriter printer;
	private FileReader reader;
	
	private int turnCount;
	
	
	//constructor
	public GameEndGUI(boolean playerWins, int inTurnCount, int finalFloor, PlayerCharacter player) throws FileNotFoundException
	{
		turnCount = inTurnCount;
		//basically, if the input is true, we setup a player winning screen. If the input is false, we set up a loss screen.
		this.setLayout(new GridLayout(4,1));
		this.setBackground(Color.BLUE);
		this.setSize(600, 600);
		
		if(playerWins)
		{
			this.setTitle("You Win!!!");
			sprawlTextString = "With the Demon Lord Vanquished, " + player.getName() + " leaves for their next adventure...";
			statString = "Hero: " + player.getName() + ". Turn Count: " + inTurnCount;
			//now, we need to check the record to see if we have a new record.
			boolean newRecord = checkRecord(statString); //this will ALSO write the record if it exists.
			if (newRecord)
			{
				statString = "NEW RECORD! " + statString;
			}
		}
		
		if(!playerWins)
		{
			this.setTitle("You Lose...");
			sprawlTextString = "The Demon Lord remains in the dungeon... who can stop him?";
			statString = player.getName() + " was felled on floor " + finalFloor + "...";
		}
		
		sprawlText = new JLabel(sprawlTextString);
		stats = new JLabel(statString);
		record = null;
		try
		{
			record = new JLabel("Record: " + getRecord());			
		}
		catch (FileNotFoundException e)
		{
			
		}
		
		buttonSetup();
		//in the top left we have: a big message!
		this.add(sprawlText);
		this.add(stats);
		this.add(record);
		this.add(restartButton);
				
		this.pack();
		this.setVisible(true);
	}
	
	public void buttonSetup()
	{
		restartButton = new JButton();
		restartButton.setText("Play Again?");
		restartButton.addActionListener(this);
//		restartButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {GameMain.replayGame();} });
//		quitButton = new JButton();
//		quitButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {GameEndGUI.dispose()} });
	}
	
	public String getRecord() throws FileNotFoundException
	{
		String record = "";
		File recordFile = new File("textData/recordData.txt");
		Scanner fileReader = null;
		
		try
		{
			fileReader = new Scanner(recordFile);
			while(fileReader.hasNext())
			{
				record += fileReader.nextLine();
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.print(e);
			record = "No Record Set!";
		}
		finally
		{
			if (fileReader != null)
			{
				fileReader.close();
			}
		}
		System.out.println("Record Retrieved: " + record);
		return record;
	}
	
	public boolean checkRecord(String recordData) throws FileNotFoundException, NumberFormatException, IndexOutOfBoundsException
	{
		System.out.println("Checking for new record!");
		//first, we need to open up and read the old record.
		try
		{
			String record = getRecord();
			
			//now if we DID get something, we need to verify if the current number is lower than the old one.
			int turnCountIndex = record.indexOf(":", 6); //we use : 2 times, first with Hero: and then with Turn Count:
			String subString = record.substring(turnCountIndex); //this substring SHOULD be just the turncount.
			System.out.println("Substring: " + subString);
			String turnCountString = subString.replaceAll("[^0-9]", ""); //this should clean out any non numbers
			System.out.println("Only Numbers: " + turnCountString);
			
			//now that we have a string of just numbers, convert to int.
			
			int recordTurnCount = Integer.parseInt(turnCountString);
			
			//now that we have the old turn count, if the new turn count is faster, we write the new record.
			if (turnCount < recordTurnCount)
			{
				System.out.println("New Turn Count is Lower, trying to write a new record:");
				writeRecord(recordData);
				return true;
			}
			else
			{
				return false;
			}
			
	
		}
		catch(FileNotFoundException e)
		{
			//if we can't find a file, that *probably* means we don't have a record yet, just write.
			writeRecord(recordData);
		}
		catch(NumberFormatException e)
		{
			//if the number checking code fails for whatever reason, just write
			//(there may be something wrong with the string, so overriding it may be for the best.)
			writeRecord(recordData);
		}
		catch(IndexOutOfBoundsException e)
		{
			//if we've hit this, that means there is NOT two :'s in our process. Which means...
			//that we probably don't have a record set.
			//so, we just set the record.
			writeRecord(recordData);
		}
		catch(Exception e)
		{
			System.out.println(e);
			writeRecord("Record Process Corrupted.");
		}
		finally
		{
			//and if something failed along the way... just return false
			return false;
		}
		
	}
	
	public void writeRecord(String recordData) throws FileNotFoundException
	{
		System.out.println("Writing Record!");
		try
		{
			printer = new PrintWriter("textData/recordData.txt");
			System.out.println("Attempting to Write: " + recordData);
			printer.println(recordData);
		}
		catch(FileNotFoundException e)
		{
			
		}
		finally
		{
			printer.close();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			GameMain.replayGame();
		}
		catch (FileNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.dispose();
	}
}
