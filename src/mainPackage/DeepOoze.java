/**
 * Lead Author(s):
 * 
 * @author ian; student ID
 * @author Full name; student ID
 *         <<Add additional lead authors here>>
 *
 *         Other Contributors:
 *         Full name; student ID or contact information if not in class
 *         <<Add additional contributors (mentors, tutors, friends) here, with
 *         contact information>>
 *
 *         References:
 *         Morelli, R., & Walde, R. (2016).
 *         Java, Java, Java: Object-Oriented Problem Solving
 *         https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 *         <<Add more references here>>
 *
 *         Version: 2025-12-09
 */
package mainPackage;

/**
 * Purpose: The reponsibility of DeepOoze is ...
 *
 * DeepOoze is-a ...
 * DeepOoze is ...
 */
public class DeepOoze extends Enemy
{
	int turnCounter = 1;

	/**
	 * Purpose: General constructor for the enemy, passes along to the superconstructor
	 * 
	 * @param inSeed the seed for the enemy to properly populate its fields
	 */
	public DeepOoze(int inSeed)
	{
		super(inSeed);
	}

	@Override
	public void takeTurn()
	{
		//first, if its the 3rd/6th/etc turn, wind up
		if (turnCounter % 3 == 0)
		{
			combatMessage = ("Enemy preps a heavy strike!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
		}
		else if (turnCounter % 4 == 0) //the turn after winding up, hit BIG
		{
			combatMessage = ("Deep Ooze crits!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
			if (atkDown)
			{
				target.takeDamage(4);
			}
			else
			{
				target.takeDamage(8);				
			}
		}
		else //when its a normal turn
		{
			if(atkDown)
			{
				target.takeDamage(2);
				atkDown = false;
			}
			else
			{
				target.takeDamage(4);				
			}
		}
		
		//then, move up the turn counter!
		turnCounter++;
	}

	@Override
	public void takeDamage(int damage)
	{
		// first step: check if my defense is debuffed, and if it is, double
		// damage
		if (defDown)
		{
			damage = damage + damage;
			defDown = false;
		}
		
		// next, reduce damage by half (elemental damage will level out because its pre doubled
		damage = damage / 2;
		
		health = health - damage;
		
		combatMessage = ("Deep Ooze takes" + damage + " dmg!");
		System.out.println(combatMessage);
		combat.combatLogMessage(combatMessage);
		
		if (health <= 0)
		{
			combatMessage = ("Deep Ooze is slain!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
			combat.endCombat(0);
		}

	}

	@Override
	public void takeElementalDamage(int damage, int elementType)
	{
		if (elementType != 0) // if it IS elemental Damage
		{
			damage = damage + damage;
		}

		takeDamage(damage);
	}

	@Override
	public String[] scanForInfo()
	{
		String messages[] = new String[3];
		messages[0] = "DeepOoze: a large slime.";
		messages[1] = "It resists all physical harm.";
		messages[2] = "Hit it with elemental skills!";

		return messages;
	}

}
