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
* Version: 2025-11-20
*/
package mainPackage;

/**
 * Purpose: The reponsibility of FireSpell is ...
 *
 * FireSpell is-a ...
 * FireSpell is ...
 */
public class FireSpell extends Ability
{

	/**
	 * Purpose: 
	 * @param inSeed
	 */
	public FireSpell(int inSeed, PlayerCharacter player)
	{
		super(inSeed, player);
		// TODO Auto-generated constructor stub
	}
	
	//methods
	
	@Override
	public void activate(Enemy target)
	{
		System.out.println("Casting Fire 1");
		player.skillCombatLog("Casting Firebolt!");
		target.takeElementalDamage(effectVal, element);
		player.spendMana(mpCost);
	}

}
