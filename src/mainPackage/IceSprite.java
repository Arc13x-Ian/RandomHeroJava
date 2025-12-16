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
 *         Version: 2025-11-14
 */
package mainPackage;

/**
 * Purpose: The reponsibility of IceSprite is ...
 *
 * IceSprite is-a ...
 * IceSprite is ...
 */
public class IceSprite extends Enemy
{
	
	private boolean hardened = false;

	/**
	 * Purpose:
	 * 
	 * @param inSeed
	 */
	public IceSprite(int inSeed)
	{
		super(1);
	}

	@Override
	public void takeTurn()
	{
		if (health <= 10 && !hardened)
		{
			hardened = true;
		}
		else if (atkDown)
		{
			target.takeDamage(3);
		}
		else
		{
			target.takeDamage(5);
		}
	}

	@Override
	public void takeDamage(int damage)
	{
		// first step: check if my health is debuffed, and if it is, double
		// damage
		if (defDown)
		{
			damage = damage + damage;
			defDown = false;
		}

		if (!hardened)
		{
			health -= damage;
			combatMessage = ("Ice Sprite takes " + damage + " dmg!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);

			if (health <= 10)
			{
				combatMessage = ("Ice Sprite hardens its body, becoming sturdier!");
				System.out.println(combatMessage);
				combat.combatLogMessage(combatMessage);
			}
		}
		else
		{
			int reduceDamage = (int) ((int) damage * 0.75);

			health -= reduceDamage;

			combatMessage = ("Ice Sprite takes a reduced " + reduceDamage
					+ " dmg!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
		}
		
		if (health <= 0)
		{
			combatMessage = ("Ice Sprite is slain!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
			combat.endCombat(0);
		}
	}

	@Override
	public void takeElementalDamage(int damage, int elementType)
	{
		// Ice Sprite is weak to fire (type 1) and strong against ice (type 2)
		// System.out.println("Taking elemental damage");
		if (elementType == 1)
		{
			combatMessage = ("Ice Sprite is weak to fire!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
			int dmg = damage + (damage / 2);
			takeDamage(dmg);
		}
		else if (elementType == 2)
		{
			combatMessage = ("The Ice Sprite absorbs the ice!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
			takeDamage(damage / 2);
		}
		else
		{
			// if we hit here just send normal damage
			takeDamage(damage);
		}

	}

	@Override
	public String[] scanForInfo()
	{
		String messages[] = new String[3];
		messages[0] = "IceSprite: an ice spirit.";
		messages[1] = "Raises its DEF at low health.";
		messages[2] = "It may have a weakness!";

		return messages;
	}

}
