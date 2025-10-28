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
* Version: 2025-10-17
*/
package mainPackage;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Purpose: The reponsibility of GameScreenGUI is to be the main screen players are viewing when they're playing the game.
 * Left panel contains player stats
 * Right panel contains combat log
 * South contains the player's "action" menu
 * North [???]
 * Center is modular, either containing a picture of the combat ongoing, or showing the upgrade screen, or the "start" menu. (Use multiple panels)
 *
 */
public class GameScreenGUI extends JFrame
{
	//fields
	private JPanel playerStats;
	private JPanel actionMenu;
	private JPanel combatLog;
	private JPanel battleScreen;
	private JPanel upgradeScreen;
	private JPanel startScreen;
	
	private boolean playing;
	
	private PlayerCharacter player;
	
	//fields for mockup
	private JButton testButton;
	
	
	//Constructors
	
	public GameScreenGUI(PlayerCharacter inPlayer)
	{
		player = inPlayer;
		this.setLayout(new BorderLayout());
		this.setSize(1920, 1080);
		this.setTitle("RandoHero v_0.0.1");
		
		testButton = new JButton("MOCKUP");
		testButton.setSize(900,900);
		setupActionMenu();
		setupCombatLog();
		setupPlayerStats();
		
		add(playerStats, BorderLayout.WEST);
		add(testButton, BorderLayout.CENTER);
		add(combatLog, BorderLayout.EAST);
		add(actionMenu, BorderLayout.SOUTH);
		
		
		playing = true;
		setVisible(true);
	}
	
	//methods
	
	public void setupActionMenu()
	{
		actionMenu = new JPanel();
		
		actionMenu.setLayout(new GridLayout(2,2));
		//TODO: make "actionButtons" class so you can seed them with an int ID and then use a singular actionlistener to do their things
		actionMenu.add(new JButton("attack"));
		actionMenu.add(new JButton("skills"));
		actionMenu.add(new JButton("focus"));
		actionMenu.add(new JButton("info"));
	}
	
	public void setupCombatLog()
	{
		//TODO: the combat log should probably be its own class so it can have an update method. OR have one master graphics updater in here?
		JLabel[] combatLogArray = new JLabel[10];
		String[] combatLogStringsArray = new String[10];
		JLabel title = new JLabel("COMBAT LOG:");
		
		combatLog = new JPanel();

		combatLog.setLayout(new GridLayout(11,1));
		combatLog.add(title);
		
		for (int i = 0; i < combatLogArray.length; i++)
		{
			combatLogStringsArray[i] = ("Log " + (i+1));
			combatLogArray[i] = new JLabel(combatLogStringsArray[i]);
			combatLog.add(combatLogArray[i]);
		}
	}
	
	public void setupPlayerStats()
	{
		JLabel[] playerStatsArray = new JLabel[5];
		playerStatsArray[0] = new JLabel(player.getName());
		playerStatsArray[1] = new JLabel("HP: " + player.getHealth() + " / 50");
		playerStatsArray[2] = new JLabel("MP: " + player.getMana() + " / 25");
		playerStatsArray[3] = new JLabel(" ");
		playerStatsArray[4] = new JLabel(" ");
		playerStats = new JPanel();
		
		playerStats.setLayout(new GridLayout(5,1));
		
		for (int i = 0; i < playerStatsArray.length; i++)
		{
			playerStats.add(playerStatsArray[i]);
		}
	}

}
