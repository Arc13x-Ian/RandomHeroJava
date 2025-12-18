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
 *         Version: 2025-10-17
 */
package mainPackage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Purpose: The reponsibility of PlayerCharacter is to hold the player's stats,
 * known skills, and obtained items
 *
 */
public class PlayerCharacter
{
	// fields
	private String name;
	private int healthPoints;
	private int maxHP;
	private int manaPoints;
	private int maxMP;
	private int level;
	private ArrayList<Ability> abilityList;
	private String combatMessage;

	private CombatManager combat;

	// constructors
	public PlayerCharacter(String inName)
	{
		name = inName;
		healthPoints = 50;
		manaPoints = 10;
		maxHP = 50;
		maxMP = 25;
		level = 0;
		abilityList = new ArrayList<>();
	}

	// methods

	public void attack(Enemy target)
	{
		target.takeDamage(4 + level);
		combat.enemyTurn();
	}

	public void focus()
	{
		int focusPts = (maxMP / 10) * 2;

		manaPoints += focusPts;

		if (manaPoints > maxMP)
		{
			manaPoints = maxMP;
		}

		healthPoints += 5;

		if (healthPoints > maxHP)
		{
			healthPoints = maxHP;
		}

		combatMessage = "Focusing...";
		System.out.println(combatMessage);
		combat.combatLogMessage(combatMessage);
		combat.enemyTurn();
	}

	public void scan()
	{
		combat.scan();
		combat.enemyTurn();
	}

	public void learnSkill(Ability newSkill)
	{
		abilityList.add(newSkill);
	}

	public Ability[] listSkills()
	{
		Ability[] skillList = new Ability[abilityList.size()];
		int listCounter = 0;

		for (int i = 0; i < abilityList.size(); i++)
		{
			// step through ability list, and if the ability isn't passive, put
			// it in the array.
			if (!abilityList.get(i).getPassive()) // if the get passive for
													// ability list i returns
													// FALSE
			{
				skillList[listCounter] = abilityList.get(i);
				listCounter++;
			}
		}

		// now skillList[] should be only the abilities that aren't passives.
		return skillList;
	}

	public void activateSkill(int selectedSkill)
	{
		abilityList.get(selectedSkill).activate(combat.getEnemy());
	}

	public void takeDamage(int dmg)
	{
		healthPoints -= dmg;
		// debug message
		combatMessage = (name + " takes " + dmg + " dmg!");
		System.out.println(combatMessage);
		combat.combatLogMessage(combatMessage);

		if (healthPoints <= 0)
		{
			// TODO: Lose the game if HP is 0 or less.

			// debug message
			combatMessage = (name + " has died!");
			System.out.println(combatMessage);
			combat.combatLogMessage(combatMessage);
			try
			{
				combat.gameLose();
			}
			catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void heal(int healAmt)
	{
		healthPoints += healAmt;

		if (healthPoints > maxHP)
		{
			healthPoints = maxHP;
		}

		combatMessage = (name + " heals for " + healAmt + "!");
		System.out.println(combatMessage);
		combat.combatLogMessage(combatMessage);

	}
	
	public void spendMana(int cost)
	{
		manaPoints -= cost;
		
		if (manaPoints < 0)
		{
			manaPoints = 0;
		}
	}

	public void skillCombatLog(String combatMessage)
	{
		combat.combatLogMessage(combatMessage);
	}
	
	public void levelUp()
	{
		level++;

		manaPoints = maxMP;

		healthPoints += 15;

		if (healthPoints > maxHP)
		{
			healthPoints = maxHP;
		}
	}

	public void resetStats()
	{
		abilityList.clear();
		healthPoints = maxHP;
		manaPoints = maxMP;
		level = 0;
	}

	// getters and setters
	public String getName()
	{
		return name;
	}

	public int getHealth()
	{
		return healthPoints;
	}

	public int getMana()
	{
		return manaPoints;
	}

	public void setCombatManager(CombatManager inCom)
	{
		combat = inCom;
	}

	public int getLevel()
	{
		return level;
	}
	
	public void setMana(int inMana)
	{
		manaPoints = inMana;
	}
}
