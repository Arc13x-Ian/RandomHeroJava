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
 *         Version: 2025-11-07
 */
package mainPackage;

import java.io.FileNotFoundException;
import java.util.Random;

/**
 * Purpose: The reponsibility of CombatManager is to know the player, and the
 * enemies the player is facing, and to be able to "run" combat.
 * Running combat is comprised of the following:
 * Wait for the player to take an action by calling any of their methods
 * Check to see if someone is dead
 * Call on the enemy to take their turn (using the takeTurn() method)
 * Check to see if someone is dead
 * Rinse and repeat until combat is over.
 *
 * CombatManager is-a ...
 * CombatManager is ...
 */
public class CombatManager
{
	// fields
	private PlayerCharacter player;
	private Enemy combatEnemy; // NOTE: If we get to the point of squads of
								// enemies, this will need to change to be an
								// array list.
	private boolean enemyTurn;
	private boolean combatActive;
	private int winner; // 0 for player winner, 1 for non player winner
	private int levelCounter = 0; // this goes up whenever the player wins
									// combat, and is used by setupCombat.

	private boolean[] knownSkills; //TRUE skills should not be picked for learning.
	private Random random;
	private GameScreenGUI gameWindow;
	
	private int turnCount;

	// Constructor

	public CombatManager(PlayerCharacter PC)
	{
		player = PC;
		player.setCombatManager(this);
		turnCount = 0;
		
		//create the random generator
		random = new Random();
		//set all knownSkills to false
		//the array length = the amount of skills in the game
		knownSkills = new boolean[8];
		for (int i = 0; i < knownSkills.length; i++)
		{
			knownSkills[i] = false;
		}
	}

	// Methods

	public void setupCombat() throws FileNotFoundException
	{
		// first, we check the level counter
		// level counter == 6 means the game is over, so instead of doing anything else, we just win game.
		switch (levelCounter)
		{
			case 0:
				System.out.println("Goblin Encountered!");
				combatLogMessage("Goblin Encountered!");
				combatEnemy = new Goblin(0);
				break;

			case 1:
				System.out.println("Ice Sprite Encountered!");
				combatLogMessage("Ice Sprite Encountered!");
				combatEnemy = new IceSprite(1);
				break;
				
			case 2:
				System.out.println("Deep Ooze Encountered!");
				combatLogMessage("Deep Ooze Encountered!");
				combatEnemy = new DeepOoze(2);
				break;
				
			case 3:
				System.out.println("Cursed Armor Encountered!");
				combatLogMessage("Cursed Armor Encountered!");
				combatEnemy = new CursedArmor(3);
				break;
				
			case 4:
				System.out.println("Elemental Nexus Encountered!");
				combatLogMessage("Elemental Nexus Encountered!");
				combatEnemy = new ElementalNexus(4);
				break;
				
			case 5:
				System.out.println("FINAL BOSS: Demon Lord!");
				combatLogMessage("FINAL BOSS: Demon Lord!");
				combatEnemy = new DemonLord(5);
				break;
				
			case 6:
				gameWin();
				break;
		}

		// then, we assign combatEnemy to be a new Enemy based on the level
		// counter

		// then, we set the combatEnemy's target to be the player, and their
		// combat manager to be here.
		if (levelCounter < 6)
		{
			beginCombat();			
		}
	}

	public void beginCombat()
	{
		// TODO: move all of this into a "setup combat" method.
		// when combat starts, set the enemy to target the player, and give the
		// enemy its combat manager
		combatEnemy.setTarget(player);
		combatEnemy.setCombatManager(this);

		// now, "turn on" combat
		System.out.println("Combat Begins");
		combatActive = true;
		// enemyTurn = false;
	}

	public void enemyTurn()
	{
		//lets wrap the WHOOOOLE thing in an if so we don't get enemy + player turn
		if (combatEnemy.getHP() > 0) //if the enemy is ALIVE
		{
			System.out.println("Enemy turn");
			if (combatActive)
			{
				combatEnemy.takeTurn();
			}
			turnCount++;
			gameWindow.refreshGUI();			
		}
	}

	public void endCombat(int victor)
	{
		
		combatActive = false;
//		winner = victor;
		levelCounter++;
		// Debug message to make sure we're getting out of combat
		if (levelCounter < 6) //if its 6 that means game is over so we don't need to do this
		{
			System.out.println("Combat is over!");
			
			gameWindow.updateCombatLog(player.getName() + "Level Up!");
			player.levelUp();
			gameWindow.updateCombatLog("Choose an Upgrade.");
			upgradeTime();			
		}
		try
		{
			setupCombat();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void upgradeTime()
	{
		String[] upgradeOptions; //array of strings we pass to the GUI for the buttons
		String optionZero = null; //first string
		String optionOne = null; //second string
		String optionTwo = null; //third string
		
		int seedZero = -99; //used to create the proper skill and disable boolean value
		int seedOne = -99; //used to create the proper skill and disable boolean value
		int seedTwo = -99; //used to create the proper skill and disable boolean value
		int testSeed; //what random will be spitting numbers into
		
		Ability upgradeZero = null; //the actual ability to pass to the player if chosen
		Ability upgradeOne = null; //the actual ability to pass to the player if chosen
		Ability upgradeTwo = null; //the actual ability to pass to the player if chosen
		
		int selectedUpgrade; //the GUI button press will return a value here, use to create proper skill
		
		boolean generatingUpgrades = true;
		// first, we pick a random ability seed number
		// then, we check the player's ability skills arrayList and see if any
		// abilities with that code exist
		// if there are, we roll again
		while (generatingUpgrades)
		{
			testSeed = random.nextInt(knownSkills.length); //first, grab a random number.
			
			if (knownSkills[testSeed] == false) //if we do NOT know this particular skill
			{
				if (seedZero < 0) //there are no negative skill seeds, so this means the slot is not filled
				{
					seedZero = testSeed; //our first upgrade option is the randomly selected one
					upgradeZero = createSkill(seedZero); //we create the skill for our first upgrade option
					optionZero = upgradeZero.getName(); //and our first "option" for buttons is the name of the skill tied to this seed
				}
				else if (seedOne < 0 && testSeed != seedZero) //if we have picked our first skill, we move on to the next one and repeat
				{
					seedOne = testSeed;
					upgradeOne = createSkill(seedOne);
					optionOne = upgradeOne.getName();
				}
				else if (seedTwo < 0 && testSeed != seedZero && testSeed != seedOne)
				{
					seedTwo = testSeed;
					upgradeTwo = createSkill(seedTwo);
					optionTwo = upgradeTwo.getName();
					//IF we are filling the third seed, then we have filled all seeds, and are done generating upgrades.
					generatingUpgrades = false;
				}
			}
			//if we are down here, we've either deposited a skill into one slot, or we've pulled a number the player knows.
			//either way, loop again if we haven't filled all 3 seeds.
		}
		
		// next, populate the array of options strings containing the names of the chosen upgrades.
		upgradeOptions = new String[3];
		upgradeOptions[0] = optionZero; //since we grabbed the names for the skills we made, we just pass them in.
		upgradeOptions[1] = optionOne;
		upgradeOptions[2] = optionTwo;
		
		// and pass it to the GameScreenGUI's upgradeChecker() method
		selectedUpgrade = gameWindow.upgradeChecker(upgradeOptions);
		// that one will return an int pertaining to which option the player
		// picked.
		
		// whichever one the player chooses, call the playerCharacter's
		// learnSkill() to learn that skill
		switch(selectedUpgrade)
		{
			//on the bright side, everything lines up naturally.
			//our first option was 0, then our second option 1, then our third 2.
			//clicking the left button will return 0, the middle 1, and the right 2.
			//that means if selectedUpgrade is 0, the ability we want to ADD is 0.
			
			//on top of that, we will set the bool in the "known skills" array to be true for the linked skill
			//so that the skill will not get rolled again.
			case 0:
				player.learnSkill(upgradeZero);
				knownSkills[seedZero] = true;
				break;
				
			case 1:
				player.learnSkill(upgradeOne);
				knownSkills[seedOne] = true;
				break;
				
			case 2:
				player.learnSkill(upgradeTwo);
				knownSkills[seedTwo] = true;
				break;
		}
		

		// TODO: for now we're going to make a preset list of buttons, and then
		// add that skill to the player.
		
		///// EVERYTHING BELOW HERE IS THE DEBUG TEST VERSION, DO NOT USE /////
		///// EVERYTHING BELOW HERE IS THE DEBUG TEST VERSION, DO NOT USE /////
		///// EVERYTHING BELOW HERE IS THE DEBUG TEST VERSION, DO NOT USE /////
		///// EVERYTHING BELOW HERE IS THE DEBUG TEST VERSION, DO NOT USE /////
		///// EVERYTHING BELOW HERE IS THE DEBUG TEST VERSION, DO NOT USE /////


//		String[] demoUpgradeOptions = { "Fire I", "Cure", "Atk Break" };
//
//		selectedUpgrade = gameWindow.upgradeChecker(demoUpgradeOptions);
//		// selected upgrade is an int that now stores the button the player
//		// checked on the menu.
//		// we need to, initially, make an array of the 3 abilities we're
//		// passing in and then just have the player learn from that array using
//		// selectedUpgrade
//
//		//DEBUG STUFF THIS IS DEBUG STUFF
//		ability upgradeAbility = new FireSpell(0, player);
//		switch(selectedUpgrade)
//		{
//			case 0: //first button
//				upgradeAbility = new FireSpell(0, player); // DEBUG STUFF: THIS IS JUST
//				// A TEST FIRE SPELL
//				break;
//				
//			case 1: //second button
//				upgradeAbility = new CureSpell(1, player);
//				break;
//				
//			case 2: //third button
//				upgradeAbility = new AtkBreakSkill(3, player);
//				break;
//		}
//
//		// switch (selectedUpgrade)
//		// {
//		// case 0:
//		//
//		// }
//		player.learnSkill(upgradeAbility);
//
//		// then, call the next setupCombat() method.
	}

	public void scan()
	{
		String messages[] = combatEnemy.scanForInfo();

		for (int i = 0; i < messages.length; i++)
		{
			combatLogMessage(messages[i]);
		}
	}

	// public void playerActionTaken()
	// {
	// enemyTurn = true;
	// }
	//
	// public void enemyActionTaken()
	// {
	// enemyTurn = false;
	// }

	public void useSkill(Ability[] skills, int selection)
	{
		//we pull this from the GUI to see if that fixes things
		skills[selection].activate(combatEnemy);

		// finally, since activating the skill should be the player's turn, we
		// can call on the combat manager to trigger the enemy's turn from here.
		enemyTurn();
	}

	public void combatLogMessage(String combatLog)
	{
		// anything from the player or enemy that currently prints a debug
		// message should now boot that message into this method.
		gameWindow.updateCombatLog(combatLog);
	}
	
	public Ability createSkill(int input)
	{
		//this is just a big long Factory Pattern
		//a switch case that can create any skill.
		//when the upgradeTime method knows which skill the player wants, we'll go down here
		//create that skill, and return it back upwards for the upgrade time method to give.
		Ability createdAbility;
		
		//check the ability class to ensure your switch case index matches this one.
		switch(input)
		{
			case 0:
				createdAbility = new FireSpell(0, player);
				break;
				
			case 1: 
				createdAbility = new CureSpell(1, player);
				break;
				
			case 2: 
				createdAbility = new DualStrikeSkill(2, player);
				break;
				
			case 3:
				createdAbility = new AtkBreakSkill(3, player);
				break;
				
			case 4:
				createdAbility = new DefBreakSkill(4, player);
				break;
				
			case 5:
				createdAbility = new FocusingStrikeSkill(5, player);
				break;
				
			case 6:
				createdAbility = new HolyBladeSkill(6, player);
				break;
				
			case 7:
				createdAbility = new FrostLanceSkill(7, player);
				
			default: 
				createdAbility = new FireSpell(0, player);
				
		}
		
		return createdAbility;
	}
	
	public void gameLose() throws FileNotFoundException
	{
		//what to do:
		//0) reset clerical stuff (Set level counter to 0, and set all skills to unknown
		//1) disable game window
		//2) generate new loss window that can be used to reset the game
		int finalFloor = player.getLevel();
		
		resetGame();
		player.resetStats();
		gameWindow.dispose();
		try
		{
			new GameEndGUI(false, turnCount, finalFloor, player);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		
	}
	
	public void gameWin() throws FileNotFoundException
	{
		//what to do:
		//0) reset clerical stuff (Set level counter to 0, and set all skills to unknown
		//1) disable game window
		//2) generate new victory window for the game victory!
		int finalFloor = player.getLevel();
		
		resetGame();
		player.resetStats();
		gameWindow.dispose();
		new GameEndGUI(true, turnCount, finalFloor, player);
	}
	
	public void resetGame()
	{
		levelCounter = 0;
		
		for (int x = 0; x < knownSkills.length; x++)
		{
			knownSkills[x] = false; //we know nothing
		}
	}

	// getters and setters
	public Enemy getEnemy()
	{
		return combatEnemy;
	}

	public void setGUI(GameScreenGUI inGUI)
	{
		gameWindow = inGUI;
	}
}
