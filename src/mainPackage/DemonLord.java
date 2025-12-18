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
 * Purpose: The reponsibility of DemonLord is ...
 *
 * DemonLord is-a ...
 * DemonLord is ...
 */
public class DemonLord extends Enemy
{
	private Random rand;
	private int finalCharge;
	private int combo;
	private boolean rageStance;
	private boolean negatingStance;
	private boolean finalLimit;

	/**
	 * Purpose:
	 * 
	 * @param inSeed
	 */
	public DemonLord(int inSeed)
	{
		super(inSeed);
		rand = new Random();
		combo = 0;
		rageStance = false;
		negatingStance = false;
		finalLimit = false;
	}

	@Override
	public void takeTurn()
	{
		// Demon Lord decision tree:
		// Priority 1: If in "Final Limit" mode, wind up twice, then on the
		// third turn, deal 9999 damage
		// Priority 2: if combo is on, perform a combo attack
		// Priority 3: if combo is NOT on, attack at normal damage ranges

		// Afterwards: check to see if we adopt a special stance via random
		// number

		if (finalLimit)
		{
			switch (finalCharge)
			{
				case 0:
					combatMessage = ("The Demon Lord prepares ULTIMA");
					System.out.println(combatMessage);
					combat.combatLogMessage(combatMessage);
					finalCharge++;
					break;

				case 1:
					combatMessage = ("ULTIMA is about to be cast!");
					System.out.println(combatMessage);
					combat.combatLogMessage(combatMessage);
					finalCharge++;
					break;

				case 2:
					combatMessage = ("The world fades away.");
					System.out.println(combatMessage);
					combat.combatLogMessage(combatMessage);

					target.takeDamage(9999);
					break;
			}
		}
		else // AKA, if the Demon Lord is NOT in final limit mode
		{
			if (combo == 3)
			{
				dealDamage(15);
				combo = 0;
			}
			else // if combo counter isn't at 3 yet
			{
				dealDamage(5);
				combo++;
			}
		}

		// after doing our damage stuff, we clear our old stances, and see if
		// the demon lord is taking on a new stance
		// ONLY if not in final limit mode
		rageStance = false;
		negatingStance = false;
		if (!finalLimit)
		{
			int stanceCheck = rand.nextInt(3);

			if (stanceCheck == 0)
			{
				rageStance = true;
				combatMessage = ("The Demon Lord is Enraged.");
				System.out.println(combatMessage);
				combat.combatLogMessage(combatMessage);
				combatMessage = ("He will do and take more damage!");
				System.out.println(combatMessage);
				combat.combatLogMessage(combatMessage);
			}
			else if (stanceCheck == 1)
			{
				negatingStance = true;
				combatMessage = ("The Demon Lord prepares to negate damage!");
				System.out.println(combatMessage);
				combat.combatLogMessage(combatMessage);
			}
		}
	}

	@Override
	public void takeDamage(int damage)
	{
		// step one: if he's in negating stance, reduce the damage to 0
		if (negatingStance)
		{
			damage = 0;
			negatingStance = false;
		}

		// step two: if the damage isn't negated, then we check for defense
		// debuff
		if (defDown && damage != 0)
		{
			damage = damage + damage;
			defDown = false;
		}

		// step 3: take damage!

		health = health - damage;

		combatMessage = ("Demon Lord takes" + damage + " dmg!");
		System.out.println(combatMessage);
		combat.combatLogMessage(combatMessage);

		//now, we do our health value checking. 
		if (health <= 20 && finalLimit == false)
		{
			finalLimit = true;
			health = 20;
			combatMessage = ("The Demon Lord is Desperate...");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
		}
		
		if (health <= 0)
		{
			combatMessage = ("VICTORY!!!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
			combat.gameWin();
		}
	}

	@Override
	public void takeElementalDamage(int damage, int elementType)
	{
		// my only weakness is holy damage, but I'm VERY weak to it!
		if (elementType == 3)
		{
			damage = (damage * 2) + (damage / 2);
		}

		takeDamage(damage);
	}

	@Override
	public String[] scanForInfo()
	{
		String messages[] = new String[3];
		messages[0] = "The Demon Lord.";
		messages[1] = "It is immensely strong, but predictable.";
		messages[2] = "Watch the combat log to predict its intent!";

		return messages;
	}

	public void dealDamage(int dmg)
	{
		if (atkDown)
		{
			dmg = dmg / 2;
			atkDown = false;
		}

		if (rageStance)
		{
			dmg = dmg * 2;
		}

		target.takeDamage(dmg);
	}

}
