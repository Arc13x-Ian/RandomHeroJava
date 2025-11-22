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
* Version: 2025-11-06
*/
package mainPackage;

/**
 * Purpose: The reponsibility of Goblin is ...
 *
 * Goblin is-a ...
 * Goblin is ...
 */
public class Goblin extends Enemy
{

	/**
	 * Purpose: 
	 * @param inSeed
	 */
	public Goblin(int inSeed)
	{
		super(0);
	}

	@Override
	public void takeTurn()
	{
		target.takeDamage(4);
//		combat.enemyActionTaken();
	}

	@Override
	public void takeDamage(int damage)
	{
		health -= damage;
		//debug message
				System.out.println("Goblin takes " + damage + " dmg!");
		
		if (health <= 0)
		{
			System.out.println("Goblin is slain!");
			combat.endCombat(0);
		}
	}

	@Override
	public void takeElementalDamage(int damage, int elementType)
	{
		//goblins have no elemental weakness or resistance, so we just pass the damage through!
		takeDamage(damage);
	}
	
	@Override
	public String scanForInfo()
	{
		return "Goblin: a small creature that lives in dungeons. Doesn't do much other than mindlessly attack.";
	}


}
