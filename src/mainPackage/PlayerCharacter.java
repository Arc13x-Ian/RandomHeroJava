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
* Version: 2025-10-17
*/
package mainPackage;

import java.util.ArrayList;

/**
 * Purpose: The reponsibility of PlayerCharacter is to hold the player's stats, known skills, and obtained items
 *
 */
public class PlayerCharacter
{
	//fields
	private String name;
	private int healthPoints;
	private int maxHP;
	private int manaPoints;
	private int maxMP;
	private ArrayList<ability> abilityList;
	
	private CombatManager combat;
	
	//constructors
	public PlayerCharacter(String inName)
	{
		name = inName;
		healthPoints = 50;
		manaPoints = 10;
		maxHP = 50;
		maxMP = 25;
		abilityList = new ArrayList<>();
	}
	
	//methods
	
	public void attack(Enemy target)
	{
		target.takeDamage(5);
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
		
		System.out.println("Focusing...");
		combat.enemyTurn();
	}
	
	public void scan()
	{
		combat.scan();
		combat.enemyTurn();
	}
	
	public void learnSkill(ability newSkill)
	{
		abilityList.add(newSkill);
	}
	
	public ability[] listSkills()
	{
		ability[] skillList = new ability[abilityList.size()];
		int listCounter = 0;
		
		for (int i = 0; i < abilityList.size(); i++)
		{
			//step through ability list, and if the ability isn't passive, put it in the array.
			if(!abilityList.get(i).getPassive()) //if the get passive for ability list i returns FALSE
			{
				skillList[listCounter] = abilityList.get(i);
				listCounter++;
			}
		}
		
		//now skillList[] should be only the abilities that aren't passives.
		return skillList;
	}
	
	public void activateSkill(int selectedSkill)
	{
		abilityList.get(selectedSkill).activate(combat.getEnemy());
	}
	
	public void takeDamage(int dmg)
	{
		healthPoints -= dmg;
		//debug message
		System.out.println(name + " takes " + dmg + " dmg!");
		
		if (healthPoints <= 0)
		{
			//TODO: Lose the game if HP is 0 or less.
			
			//debug message
			System.out.println(name + "has died!");
			combat.endCombat(1);
		}
	}
	
	//getters and setters
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
}
