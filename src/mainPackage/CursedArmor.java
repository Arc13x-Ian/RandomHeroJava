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
 *         Version: 2025-12-14
 */
package mainPackage;

import java.util.Random;

/**
 * Purpose: The reponsibility of CursedArmor is ...
 *
 * CursedArmor is-a ...
 * CursedArmor is ...
 */
public class CursedArmor extends Enemy
{
	private boolean parryUp;
	private boolean playerAtkDown;

	private Random rand;

	/**
	 * Purpose: General constructor for the cursed armor, passes along to the superconstructor
	 * 
	 * @param inSeed the seed for the enemy to properly populate its fields
	 */
	public CursedArmor(int inSeed)
	{
		super(inSeed);
		rand = new Random();
	}

	@Override
	public void takeTurn()
	{
		// first, get a random number, out of 8 so we can get percentages we
		// want.
		int checkNum = rand.nextInt(8);

		// now for the decision tree. 1: if we don't have parry up, lets see if
		// we parry: 25% chance
		if (!parryUp && checkNum < 2)
		{
			parryUp = true;

			combatMessage = ("The armor takes a parrying stance!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
		}
		else if (checkNum < 3) // next up, at a 33~% chance we weaken the player
								// instead of doing regular damage.
		{
			combatMessage = (target.getName() + "'s arm is struck! ATK Down!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
			target.takeDamage(3);
			playerAtkDown = true;
		}
		else // if we aren't doing special stuff, a normal attack
		{
			target.takeDamage(5);
		}

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
		
		//next step: if the player's attack is debuffed, half damage
		if(playerAtkDown)
		{
			damage = damage / 2;
			playerAtkDown = false;
		}
		
		//third step: if we're in parry mode, hit the player as they hit me
		if(parryUp)
		{
			target.takeDamage(damage);
			combatMessage = ("The armor counters!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
			parryUp = false;
		}
		
		//finally: actually take damage
		health -= damage;
		//debug message
				combatMessage = ("The armor takes " + damage + " dmg!");
				System.out.println(combatMessage);
				combat.combatLogMessage(combatMessage);
		
		if (health <= 0)
		{
			combatMessage = ("Cursed Armor is slain!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
			combat.endCombat(0);
		}
	}

	@Override
	public void takeElementalDamage(int damage, int elementType)
	{
		//I have no elemental weaknesses, so just pass the damage right on into the takeDamage method
		takeDamage(damage);
	}

	@Override
	public String[] scanForInfo()
	{
		String messages[] = new String[3];
		messages[0] = "CurseArmr: a hollowed set of armor.";
		messages[1] = "Watch for it to try to parry.";
		messages[2] = "It may try to weaken you!";

		return messages;
	}

}
