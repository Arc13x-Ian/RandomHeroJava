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
* Version: 2025-12-14
*/
package mainPackage;

/**
 * Purpose: The reponsibility of ElementalNexus is ...
 *
 * ElementalNexus is-a ...
 * ElementalNexus is ...
 */
public class ElementalNexus extends Enemy
{

	/**
	 * Purpose: 
	 * @param inSeed
	 */
	public ElementalNexus(int inSeed)
	{
		super(inSeed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void takeTurn()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void takeDamage(int damage)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void takeElementalDamage(int damage, int elementType)
	{
		// Behavior note: Elemental nexus will change its weakness when the weakness is hit (deal damage first, THEN change weakness)
		// Weaknesses should shift between fire, ice, and holy.

	}

	@Override
	public String[] scanForInfo()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
