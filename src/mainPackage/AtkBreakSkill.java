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
public class AtkBreakSkill extends ability
{

	/**
	 * Purpose: 
	 * @param inSeed
	 * @param hero
	 */
	public AtkBreakSkill(int inSeed, PlayerCharacter hero)
	{
		super(inSeed, hero);
		// TODO Auto-generated constructor stub
	}
	
	public void activate(Enemy target)
	{
		System.out.println("Attacking The Arm!");
		target.takeDamage(effectVal);
		target.debuffAttack();
	}

}
