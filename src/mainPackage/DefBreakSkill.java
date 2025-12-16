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
* Version: 2025-12-05
*/
package mainPackage;

/**
 * Purpose: The reponsibility of DefBreakSkill is ...
 *
 * DefBreakSkill is-a ...
 * DefBreakSkill is ...
 */
public class DefBreakSkill extends Ability
{

	/**
	 * Purpose: 
	 * @param inSeed
	 * @param hero
	 */
	public DefBreakSkill(int inSeed, PlayerCharacter hero)
	{
		super(inSeed, hero);
	}
	
	@Override
	public void activate(Enemy target)
	{
		System.out.println("Breaking the armor!");
		target.takeDamage(effectVal);
		target.debuffDefense();
	}

}
