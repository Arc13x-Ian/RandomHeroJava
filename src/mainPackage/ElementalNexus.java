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
 * Purpose: The reponsibility of ElementalNexus is ...
 *
 * ElementalNexus is-a ...
 * ElementalNexus is ...
 */
public class ElementalNexus extends Enemy
{
	private Random rand;
	private int rampUp;

	/**
	 * Purpose: General constructor for the enemy, passes along to the superconstructor
	 * 
	 * @param inSeed the seed for the enemy to properly populate its fields
	 */
	public ElementalNexus(int inSeed)
	{
		super(inSeed);
		rand = new Random();
		weakness = rand.nextInt(1, 3);
	}

	@Override
	public void takeTurn()
	{
		// a very simple turn: Hit the enemy, and RAMP UP
		target.takeDamage(1 + rampUp);
		rampUp++;

		combatMessage = ("E.Nexus grows in intensity!");
		System.out.println(combatMessage);
		combat.combatLogMessage(combatMessage);
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

		health -= damage;
		// debug message
		combatMessage = ("E.Nexus takes " + damage + " dmg!");
		System.out.println(combatMessage);
		combat.combatLogMessage(combatMessage);

		if (health <= 0)
		{
			combatMessage = ("E.Nexus is slain!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
			combat.endCombat(0);
		}
	}

	@Override
	public void takeElementalDamage(int damage, int elementType)
	{
		// Behavior note: Elemental nexus will change its weakness when the
		// weakness is hit (deal damage first, THEN change weakness)
		// Weaknesses should shift between fire, ice, and holy.
		int pastWeakness = weakness;
		boolean changingWeakness = false;

		if (elementType == weakness)
		{
			damage = damage + damage;

			combatMessage = ("Weakness struck! E.nexus changes weakness!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);

			changingWeakness = true;
			while (changingWeakness)
			{
				weakness = rand.nextInt(1, 3);
				if (weakness != pastWeakness)
				{
					changingWeakness = false;
				}
			}
		}
		takeDamage(damage);
	}

	@Override
	public String[] scanForInfo()
	{
		String messages[] = new String[3];
		messages[0] = "Elemental Nexus: Chaotic energy.";
		messages[1] = "Its weakness will shift if you hit it.";
		messages[2] = "It gets stronger as time goes on!";

		return messages;
	}

}
