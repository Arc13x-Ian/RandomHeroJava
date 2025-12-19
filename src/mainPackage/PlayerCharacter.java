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
	/**
	 * 
	 * Purpose: General constructor for the player's character
	 * 
	 * @param inName String for the name for the hero
	 */
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

	/**
	 * 
	 * Purpose: Takes turn, deals damage to the current enemy, increasing as the
	 * player levels up
	 * 
	 * @param target the enemy to affect the health of
	 */
	public void attack(Enemy target)
	{
		target.takeDamage(4 + level);
		combat.enemyTurn();
	}

	/**
	 * 
	 * Purpose: Takes turn, restoring a small amount of health and mana
	 */
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

	/**
	 * 
	 * Purpose: Takes turn, scanning the enemy for info by calling the combat
	 * manager's method
	 */
	public void scan()
	{
		combat.scan();
		combat.enemyTurn();
	}

	/**
	 * 
	 * Purpose: Adds a new skill to the player's arrayList of known skills
	 * 
	 * @param newSkill the Ability object to add to the arrayList
	 */
	public void learnSkill(Ability newSkill)
	{
		abilityList.add(newSkill);
	}

	/**
	 * 
	 * Purpose: Creates a list of only active skills known by the player
	 * 
	 * @return a static array of currently known active skills
	 */
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

	/**
	 * 
	 * Purpose: activates a given skill off the ability list, targeting the
	 * enemy. DEPRECATED (the buttons that activate skills now have the skills
	 * attached and activate on that end.
	 * 
	 * @param selectedSkill the index of the skill within the arrayList.
	 */
	public void activateSkill(int selectedSkill)
	{
		abilityList.get(selectedSkill).activate(combat.getEnemy());
	}

	/**
	 * 
	 * Purpose: reduces the player's health by incoming damage, and causes the
	 * player to lose if the hp value drops to 0 or below
	 * 
	 * @param dmg the value to reduce health by
	 */
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

	/**
	 * 
	 * Purpose: heals the player, raising their health up to the maximum value
	 * 
	 * @param healAmt the amount to heal by
	 */
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

	/**
	 * 
	 * Purpose: reduces the player's mana to a minimum of 0
	 * 
	 * @param cost the amount to reduce the mana value by
	 */
	public void spendMana(int cost)
	{
		manaPoints -= cost;

		if (manaPoints < 0)
		{
			manaPoints = 0;
		}
	}

	/**
	 * 
	 * Purpose: calls the combat manager's combat log updater (skills know the
	 * player, so pass their combat log messsages here, then to the manager,
	 * then to the GUI)
	 * 
	 * @param combatMessage String message to send along
	 */
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

	/**
	 * 
	 * Purpose: resets the player's stats when they begin a new game by clearing the ability list, topping off HP and MP, and resetting level
	 */
	public void resetStats()
	{
		abilityList.clear();
		healthPoints = maxHP;
		manaPoints = maxMP;
		level = 0;
	}

	// getters and setters
	
	/**
	 * 
	 * Purpose: Getter class for the name field
	 * @return current name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 
	 * Purpose: Getter method for the healthPoints field
	 * @return current healthPoints value
	 */
	public int getHealth()
	{
		return healthPoints;
	}

	/**
	 * 
	 * Purpose: Getter method for the manaPoints field
	 * @return current manaPoints value
	 */
	public int getMana()
	{
		return manaPoints;
	}

	/**
	 * 
	 * Purpose: Setter method for the combatManager field
	 * @param inCom combatManager object
	 */
	public void setCombatManager(CombatManager inCom)
	{
		combat = inCom;
	}

	/**
	 * 
	 * Purpose: Getter method for the level field
	 * @return current level value
	 */
	public int getLevel()
	{
		return level;
	}

	/**
	 * 
	 * Purpose: Setter method for the manaPoints value
	 * @param inMana the value to set the manaPoints to
	 */
	public void setMana(int inMana)
	{
		manaPoints = inMana;
	}

	/**
	 * 
	 * Purpose: Setter method for the name field
	 * @param newName the new name to set
	 */
	public void setName(String newName)
	{
		name = newName;
	}
}
