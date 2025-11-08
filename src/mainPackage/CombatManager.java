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
* Version: 2025-11-07
*/
package mainPackage;

/**
 * Purpose: The reponsibility of CombatManager is to know the player, and the enemies the player is facing, and to be able to "run" combat.
 * Running combat is comprised of the following:
 *     Wait for the player to take an action by calling any of their methods
 *     Check to see if someone is dead
 *     Call on the enemy to take their turn (using the takeTurn() method)
 *     Check to see if someone is dead
 *     Rinse and repeat until combat is over.
 *
 * CombatManager is-a ...
 * CombatManager is ...
 */
public class CombatManager
{
	//fields
	private PlayerCharacter player;
	private Enemy combatEnemy; //NOTE: If we get to the point of squads of enemies, this will need to change to be an array list.
	private boolean enemyTurn;
	private boolean combatActive;
	private int winner; //0 for player winner, 1 for non player winner
	
	private GameScreenGUI gameWindow;
	
	//Constructor
	
	//TODO: remove the enemy parameter
	public CombatManager(PlayerCharacter PC, Enemy testGob)
	{
		player = PC;
		player.setCombatManager(this);
		
		//TODO: don't have this- this is just to test combat with a single goblin.
		//we'll want a "setup combat" method that populates a new level when combat ends
		combatEnemy = testGob;
	}
	
	//Methods
	
	public void beginCombat()
	{
		//TODO: move all of this into a "setup combat" method.
		//when combat starts, set the enemy to target the player, and give the enemy its combat manager
		combatEnemy.setTarget(player);
		combatEnemy.setCombatManager(this);
		
		//now, "turn on" combat
		System.out.println("Combat Begins");
//		combatActive = true;
//		enemyTurn = false;
	}
	
	public void enemyTurn()
	{
		System.out.println("Enemy turn");
		if (combatActive)
		{
			combatEnemy.takeTurn();			
		}
		gameWindow.refreshGUI();
	}
	
	public void endCombat(int victor)
	{
		combatActive = false;
		winner = victor;
		//Debug message to make sure we're getting out of combat
		System.out.println("Combat is over!");

		//TODO: run the upgrade picker, then setup a new combat after this one.
	}
	
	public void scan()
	{
		System.out.println(combatEnemy.scanForInfo());
	}
	
	public void playerActionTaken()
	{
		enemyTurn = true;
	}
	
	public void enemyActionTaken()
	{
		enemyTurn = false;
	}
	

	//getters and setters
	public Enemy getEnemy()
	{
		return combatEnemy;
	}
	
	public void setGUI(GameScreenGUI inGUI)
	{
		gameWindow = inGUI;
	}
}
