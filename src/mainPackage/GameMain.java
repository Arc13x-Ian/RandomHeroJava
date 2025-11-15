/**
* Lead Author(s):
* @author Ian Moser
*
*
* References:
* Morelli, R., & Walde, R. (2016).
* Java, Java, Java: Object-Oriented Problem Solving
* https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
*
*
* Version: 2025-10-17
*/
package mainPackage;

/**
 * Purpose: The reponsibility of GameMain is Generate GUI, Keep watch for the game ending, and "manage" the game.
 *
 */
//public enum Elements {NONE, FIRE, ICE, ELEC, LGHT, DARK}

public class GameMain
{
	
	public static void main(String[] args)
	{
		PlayerCharacter player = new PlayerCharacter("TestHero");
//		Enemy testGob = new Goblin(0); //TESTING LINE, DELETE IN FUTURE
		CombatManager combatManager = new CombatManager(player);
		GameScreenGUI game = new GameScreenGUI(player, combatManager);
		combatManager.setupCombat();
	}

}
