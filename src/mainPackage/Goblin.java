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
		if(atkDown)
		{
			target.takeDamage(2);
		}
		else
		{
			target.takeDamage(4);			
		}
//		combat.enemyActionTaken();
	}

	@Override
	public void takeDamage(int damage)
	{
		health -= damage;
		//debug message
				combatMessage = ("Goblin takes " + damage + " dmg!");
				System.out.println(combatMessage);
				combat.combatLogMessage(combatMessage);
		
		if (health <= 0)
		{
			combatMessage = ("Goblin is slain!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
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
	public String[] scanForInfo()
	{
		String messages[] = new String[3];
		messages[0] = "Goblin: a small creature,";
		messages[1] = "it lives deep in dungeons.";
		messages[2] = "Mindlessly attacks."; 
		
		return messages;
	}


}
