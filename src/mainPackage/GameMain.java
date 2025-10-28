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
public class GameMain
{

	
	public static void main(String[] args)
	{
		PlayerCharacter player = new PlayerCharacter("TestHero");
		GameScreenGUI game = new GameScreenGUI(player);
	}

}
