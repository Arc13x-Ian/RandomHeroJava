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
 * Purpose: The reponsibility of FrostLanceSkill is ...
 *
 * FrostLanceSkill is-a ...
 * FrostLanceSkill is ...
 */
public class FrostLanceSkill extends Ability
{

	/**
	 * Purpose: 
	 * @param inSeed
	 * @param hero
	 */
	public FrostLanceSkill(int inSeed, PlayerCharacter hero)
	{
		super(inSeed, hero);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void activate(Enemy target)
	{
		System.out.println("Casting Frost Lance");
		player.skillCombatLog("Casting Frost Lance!");
		target.takeElementalDamage(effectVal, element);
		player.spendMana(mpCost);
	}
}
