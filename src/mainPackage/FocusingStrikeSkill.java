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
* Version: 2025-12-13
*/
package mainPackage;

/**
 * Purpose: The reponsibility of FocusingStrikeSkill is ...
 *
 * FocusingStrikeSkill is-a ...
 * FocusingStrikeSkill is ...
 */
public class FocusingStrikeSkill extends Ability
{

	/**
	 * Purpose: General Constructor for the skill, passes to superconstructor
	 * @param inSeed the skill seed which helps the superconductor know what to do
	 * @param hero the player character
	 */
	public FocusingStrikeSkill(int inSeed, PlayerCharacter hero)
	{
		super(inSeed, hero);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void activate(Enemy target)
	{
		System.out.println("Strike And Focus");
		player.skillCombatLog("Attack and Focus!");
		target.takeDamage(effectVal);
		player.focus();
		player.setMana(player.getMana() + 5);
	}

}
