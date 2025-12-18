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
* Version: 2025-10-18
*/
package mainPackage;

/**
 * Purpose: The reponsibility of Enemy is ...
 *
 * Enemy is-a ...
 * Enemy is ...
 */
public abstract class Enemy
{
	//fields
	protected String name;
	protected int weakness; //elemental weaknesses. 0 = none, 1 = fire, 2 = ice,
	protected int health;
	protected int difficultyVal; //maybe? If we're not doing sequential floors and we're doing random enemies, we'll want a diffValue to randomize
	protected int enemySeed; //juuust in case, we can define enemies by their seed and use a lot of switch cases
	protected String combatMessage;
	
	protected PlayerCharacter target; //even though a combat managing class will exist, enemies need to be able to call PC commands.
	protected CombatManager combat;
	
	//booleans for tracking one time things
	protected boolean atkDown;
	protected boolean defDown;
	
	//enemy key: 0 = goblin, 1 = ice sprite, 99 = demon
	
	//Superconstructor
	public Enemy(int inSeed)
	{		
		switch(inSeed)
		{
			//we'll have 9 floors, so monster cases 0-8
			//actually, start with 6 (tutorial + 5), so monster cases 0-5.
			case 0:
				name = "Goblin";
				health = 10;
				weakness = 0;
				difficultyVal = 1;
				enemySeed = 0;
				break;
				
			case 1:
				name = "Ice Sprite";
				health = 20;
				weakness = 1;
				difficultyVal = 2;
				enemySeed = 1;
				break;
				
			case 2:
				name = "Deep Ooze";
				health = 25;
				weakness = 99;
				difficultyVal = 3;
				enemySeed = 2;
				break;
				
			case 3:
				name = "Cursed Armor";
				health = 30;
				weakness = 0;
				difficultyVal = 0;
				enemySeed = 3;
				break;
				
			case 4:
				name = "Elemental Nexus";
				health = 35;
				weakness = 0;
				difficultyVal = 0;
				enemySeed = 4;
				break;
				
			case 5:
				name = "Demon Lord";
				health = 45;
				weakness = 3;
				difficultyVal = 9;
				enemySeed = 5;
				break;
		}	
		
		//set the booleans to false
		atkDown = false;
		defDown = false;
	}
	
	//methods
	
	//REMINDER: Things Enemies must account for:
	//1) Having their attack debuffed when taking turn
	//2) Having their defense debuffed when taking damage
	
	
	public abstract void takeTurn(); //holds enemy combat behavior
	
	public abstract void takeDamage(int damage); //allows the enemy to take damage. Abstract in case an enemy has unique HP behavior.
	
	public abstract void takeElementalDamage(int damage, int elementType); //check for an elemental weakness/resistance, change the damage number accordingly, pass to takeDamage.
	
	public abstract String[] scanForInfo();
	
	public void debuffAttack()
	{
		atkDown = true;
		
		combatMessage = (name + " is weakened for 1 round!");
		System.out.println(combatMessage);
		combat.combatLogMessage(combatMessage);
	}
	
	public void debuffDefense()
	{
		defDown = true;
		
		combatMessage = (name + " is vulnerable for 1 round!");
		System.out.println(combatMessage);
		combat.combatLogMessage(combatMessage);
	}
	
	//getters and setters
	public void setTarget(PlayerCharacter hero)
	{
		target = hero;
	}
	
	public void setCombatManager(CombatManager inCom)
	{
		combat = inCom;
	}

}
