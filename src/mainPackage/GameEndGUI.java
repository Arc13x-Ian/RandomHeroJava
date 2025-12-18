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
	
	
	//constructor
	public GameEndGUI(boolean playerWins, int turnCount, PlayerCharacter player)
	{
		//basically, if the input is true, we setup a player winning screen. If the input is false, we set up a loss screen.
		this.setLayout(new GridLayout(3,1));
		this.setBackground(Color.BLUE);
		this.setSize(600, 600);
		
		if(playerWins)
		{
			this.setTitle("You Win!!!");
			sprawlTextString = "With the Demon Lord Vanquished, " + player.getName() + " leaves for their next adventure...";
			statString = "Hero: " + player.getName() + ". Turn Count: " + turnCount;
		}
		
		if(!playerWins)
		{
			this.setTitle("You Lose...");
			sprawlTextString = "The Demon Lord remains in the dungeon... who can stop him?";
			statString = player.getName() + " was felled on floor " + player.getLevel() + "...";
		}
		
		sprawlText = new JLabel(sprawlTextString);
		stats = new JLabel(statString);
		buttonSetup();
		//in the top left we have: a big message!
		this.add(sprawlText);
		this.add(stats);
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

	@Override
	public void actionPerformed(ActionEvent e)
	{
		GameMain.replayGame();
		this.dispose();
	}
}
