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
* Version: 2025-12-04
*/
package mainPackage;

/**
 * Purpose: The reponsibility of AtkBreakSkill is ...
 *
 * AtkBreakSkill is-a ...
 * AtkBreakSkill is ...
 */
public class AtkBreakSkill extends Ability
{

	/**
	 * Purpose: General Constructor for the skill, passes to superconstructor
	 * @param inSeed the skill seed which helps the superconductor know what to do
	 * @param hero the player character
	 */
	public AtkBreakSkill(int inSeed, PlayerCharacter hero)
	{
		super(inSeed, hero);
	}
	
	@Override
	public void activate(Enemy target)
	{
		System.out.println("Attacking The Arm!");
		player.skillCombatLog("Attack the arm!");
		target.takeDamage(effectVal);
		target.debuffAttack();
		player.spendMana(mpCost);
	}

}
