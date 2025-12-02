/**
 * Lead Author(s):
 * 
 * @author ian; student ID
 * @author Full name; student ID
 *         <<Add additional lead authors here>>
 *
 *         Other Contributors:
 *         Full name; student ID or contact information if not in class
 *         <<Add additional contributors (mentors, tutors, friends) here, with
 *         contact information>>
 *
 *         References:
 *         Morelli, R., & Walde, R. (2016).
 *         Java, Java, Java: Object-Oriented Problem Solving
 *         https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 *         <<Add more references here>>
 *
 *         Version: 2025-10-17
 */
package mainPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Purpose: The reponsibility of GameScreenGUI is to be the main screen players
 * are viewing when they're playing the game.
 * Left panel contains player stats
 * Right panel contains combat log
 * South contains the player's "action" menu
 * North [???]
 * Center is modular, either containing a picture of the combat ongoing, or
 * showing the upgrade screen, or the "start" menu. (Use multiple panels)
 *
 */
public class GameScreenGUI extends JFrame
{
	// fields
	private JPanel playerStats;
	private JPanel actionMenu;
	private JPanel combatLog;
	private JPanel battleScreen;
	private JLabel battleView; // I may need to use this instead of a panel?
	private JPanel upgradeScreen;
	private JPanel startScreen;
	private JPanel skillsMenu;
	
	private JLabel[] playerStatsArray;
	
	private JLabel[] combatLogArray;
	private String[] combatLogStringsArray;

	ImageIcon combatBG = new ImageIcon("images/CombatBG.png");

	private boolean playing;

	private PlayerCharacter player;
	private CombatManager combat;

	// fields for mockup
	private JButton testButton;
	ArrayList<Enemy> testCombat; // this is until I properly have combat's
									// related classes set up
	ImageIcon tempHeroIcon = new ImageIcon("images/tempHeroToken.png");
	ImageIcon tempGobIcon = new ImageIcon("images/tempGoblinToken.png");
	private JLabel heroToken;
	private JLabel gobToken;

	// Constructors

	public GameScreenGUI(PlayerCharacter inPlayer, CombatManager inCombat)
	{
		player = inPlayer;
		combat = inCombat;
		combat.setGUI(this);
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLUE);
		this.setSize(1000, 1080);
		this.setTitle("RandoHero v_0.0.1");

		// SECTION FOR TESTING, DELETE WHEN MAKING FULL GUI
		testButton = new JButton("MOCKUP");
		testButton.setSize(900, 900);

		setupTestBattleView();
		// END SECTION FOR TESTING
		setupActionMenu();
		setupCombatLog();
		setupPlayerStats();

		add(playerStats, BorderLayout.WEST);
		add(battleView, BorderLayout.CENTER);
		add(combatLog, BorderLayout.EAST);
		add(actionMenu, BorderLayout.SOUTH);

		playing = true;
		setVisible(true);
	}

	// methods

	public void setupActionMenu()
	{
		actionMenu = new JPanel();

		actionMenu.setLayout(new GridLayout(2, 2));
		// TODO: make "actionButtons" class so you can seed them with an int ID
		// and then use a singular actionlistener to do their things
		actionMenu.add(new playerButton(0, player, combat, this));
		actionMenu.add(new playerButton(1, player, combat, this));
		actionMenu.add(new playerButton(2, player, combat, this));
		actionMenu.add(new playerButton(3, player, combat,this));
	}

	public void setupCombatLog()
	{
		// TODO: the combat log should probably be its own class so it can have
		// an update method. OR have one master graphics updater in here?
		combatLogArray = new JLabel[10];
		combatLogStringsArray = new String[10];
		JLabel title = new JLabel("COMBAT LOG:");

		combatLog = new JPanel();

		combatLog.setLayout(new GridLayout(11, 1));
		combatLog.add(title);

		for (int i = 0; i < combatLogArray.length; i++)
		{
			combatLogStringsArray[i] = ("Log " + (i + 1));
			combatLogArray[i] = new JLabel(combatLogStringsArray[i]);
			combatLog.add(combatLogArray[i]);
		}
	}

	public void setupPlayerStats()
	{
		playerStatsArray = new JLabel[5];
		playerStatsArray[0] = new JLabel(player.getName());
		playerStatsArray[1] = new JLabel("HP: " + player.getHealth() + " / 50");
		playerStatsArray[2] = new JLabel("MP: " + player.getMana() + " / 25");
		playerStatsArray[3] = new JLabel(" ");
		playerStatsArray[4] = new JLabel(" ");
		playerStats = new JPanel();

		playerStats.setLayout(new GridLayout(5, 1));

		for (int i = 0; i < playerStatsArray.length; i++)
		{
			playerStats.add(playerStatsArray[i]);
		}
	}

	public void setupNewBattleScreen(ArrayList<Enemy> combatEnemies)
	{
		battleScreen = new JPanel();

		battleScreen.setLayout(new GridLayout(4, 4));
		// TODO: in order to set an icon background for a JPanel I'm going to
		// need to make a new class
		// extend JPanel, and override
		battleScreen.setBackground(Color.BLUE);
		// TODO: POTENTIALLY ABANDONED, TESTING USING JLABEL
	}

	public void setupTestBattleView()
	{
		// TODO: require input of an array list of enemies
		// TODO: change name to setupNewBattleView when actually working
		// properly
		battleView = new JLabel();
		heroToken = new JLabel();
		gobToken = new JLabel();
		int counter = 0;

		battleView.setLayout(new GridLayout(4, 4)); // ...why can you use a
													// label like a panel?
		battleView.setIcon(combatBG);

		// now, the first 9 cells will be empty
		for (int i = 0; i < 9; i++)
		{
			battleView.add(new JLabel());
			counter++;
		}
		// next, we have the player character icon in cell 10
		heroToken.setIcon(tempHeroIcon);
		battleView.add(heroToken);
		counter++;

		// TODO: instead of simply setting down the goblin token, the method
		// SHOULD:
		// check to see how many enemies there are in combat
		// then set down the enemies into cells 11, 12, 15, and 16
		// no enemies in cells 13/14 as they are on the "Hero side" of the
		// combat view
		gobToken.setIcon(tempGobIcon);
		battleView.add(gobToken);
		counter++;

		// then, we fill in the rest of the cells blankly
		while (counter < 16)
		{
			battleView.add(new JLabel());
			counter++;
		}
	}

	// NON SETUP METHODS

	public void refreshGUI()
	{
		playerStatsArray[1].setText("HP: " + player.getHealth() + " / 50");
		playerStatsArray[2].setText("MP: " + player.getMana() + " / 25");
		this.add(actionMenu, BorderLayout.SOUTH);
	}
	
	public void refreshCombatScreen()
	{
		
	}
	
	public int upgradeChecker(Object[] options)
	{
		return JOptionPane.showOptionDialog(null, "Level Up! Choose an Upgrade:", "RandomHero", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
	}
	
	public void generateSkillMenu(ability[] skills)
	{
		//so, the goal is to create a bevy of buttons by:
		//reading the ability array, and for each button, attaching the ability in the array
		//then, those buttons are placed on a little menu and presented to the player
		//each button should have a listener that is able to call the attached ability's activate() method on the enemy in combat.
		
		String[] skillNames = new String[skills.length];
		
		for (int i = 0; i < skills.length; i++)
		{
			skillNames[i] = skills[i].getName();
		}
		
		//create an option dialog that has the player pick a skill from their known skills
		int selection = JOptionPane.showOptionDialog(null, "Skills:", "RandomHero", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, skillNames, null);
		//then, we activate that skill against the known enemy
		combat.useSkill(skills, selection);
	}
	
	public void updateCombatLog(String newMessage)
	{
		//first, we shuffle every log in the combat log upwards by 1.
		for (int i = 0; i < combatLogArray.length - 1; i++)
		{
			//combat log 0 has its text set to the text from combat log 1
			//then combat log 1 has its text set to the text from combat log 2
			//and so on
			combatLogArray[i].setText(combatLogArray[i+1].getText());
		}
		//then we set the combat log array's final line to be our new thing.
		combatLogArray[9].setText(newMessage);
		
	}

}
