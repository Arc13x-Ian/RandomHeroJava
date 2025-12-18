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

import javax.swing.JOptionPane;

/**
 * Purpose: The reponsibility of GameMain is Generate GUI, Keep watch for the game ending, and "manage" the game.
 *
 */
//public enum Elements {NONE, FIRE, ICE, ELEC, LGHT, DARK}

public class GameMain
{
	static PlayerCharacter player;
	static CombatManager combatManager;
	static String playerName;
	
	public static void main(String[] args)
	{
		playerName = JOptionPane.showInputDialog("Who are you, Hero?", JOptionPane.QUESTION_MESSAGE);
		JOptionPane.showMessageDialog(null, "Enter the dungeon- kill the Demon Lord.", " ", JOptionPane.INFORMATION_MESSAGE);

		player = new PlayerCharacter(playerName);
//		Enemy testGob = new Goblin(0); //TESTING LINE, DELETE IN FUTURE
		combatManager = new CombatManager(player);
		GameScreenGUI game = new GameScreenGUI(player, combatManager);
		combatManager.setupCombat();
	}

	public static void replayGame()
	{
		GameScreenGUI newGame = new GameScreenGUI(player, combatManager);
		combatManager.setupCombat();
	}
	
}
