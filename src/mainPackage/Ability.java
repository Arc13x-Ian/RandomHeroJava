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

/**
 * Purpose: The reponsibility of ability is to be the master class for all abilities the player might obtain or use
 *
 * ability is-a ...
 * ability is ...
 */
public class Ability
{
	//fields
	protected String skillName;
	protected int effectVal;
	protected int mpCost;
	protected int element; //0 = non elemental, 1 = fire, 2 = ice
	protected boolean isPassive;
	protected int seed;
	protected PlayerCharacter player;
	
	
	
	//constructor
	/**
	 * 
	 * Purpose: General Constructor for abilities- maintains ability information for easy reference later on
	 * @param inSeed the "seed" of the ability, which determines its statistics
	 * @param hero the player character, for abilities that affect the player
	 */
	public Ability(int inSeed, PlayerCharacter hero)
	{
		player = hero;
		switch(inSeed)
		{
			//with 9 levels, meaning we hit level up 8 times, we need 10 skills to pick from!
			
			//we'll start with 5 actually. For safety. So, 7 skills.
			case 0: //fire 1
				skillName = "Fire bolt";
				effectVal = 10;
				mpCost = 4;
				element = 1;
				seed = inSeed;
				isPassive = false;
				break;
				
			case 1: //regen
				skillName = "Cure";
				effectVal = 15;
				mpCost = 5;
				element = 0;
				seed = inSeed;
				isPassive = false;
				break;
				
			case 2: //double strike
				skillName = "Lifesteal Strike";
				effectVal = 10;
				mpCost = 3;
				element = 0;
				seed = inSeed;
				isPassive = false;
				break;
				
			case 3: //Attack Break
				skillName = "ATK Break";
				effectVal = 5;
				mpCost = 3;
				element = 0;
				seed = inSeed;
				isPassive = false;
				break;

			case 4: //Defense Break
				skillName = "DEF Break";
				effectVal = 5;
				mpCost = 3;
				element = 0;
				seed = inSeed;
				isPassive = false;
				break;
				
			case 5: //Focusing Strike
				skillName = "Focusing Strike";
				effectVal = 4;
				mpCost = 0;
				element = 0;
				seed = inSeed;
				isPassive = false;
				break;
				
			case 6: //Holy Blade
				skillName = "Holy Blade";
				effectVal = 6;
				mpCost = 3;
				element = 3;
				seed = inSeed;
				isPassive = false;
				break;
				
			case 7: //Frost Lance
				skillName = "Frost Lance";
				effectVal = 10;
				mpCost = 4;
				element = 2;
				seed = inSeed;
				isPassive = false;
				break;
				
//			case 8: 
//				skillName = "";
//				effectVal = 0;
//				mpCost = 0;
//				element = 0;
//				seed = inSeed;
//				isPassive = false;
//				break;
//				
//			case 9: 
//				skillName = "";
//				effectVal = 0;
//				mpCost = 0;
//				element = 0;
//				seed = inSeed;
//				isPassive = false;
//				break;
//				
//			case 10: 
//				skillName = "";
//				effectVal = 0;
//				mpCost = 0;
//				element = 0;
//				seed = inSeed;
//				isPassive = false;
//				break;

		}
	}
	
	//methods
	
	/**
	 * 
	 * Purpose: This method will be different for every child ability class, and will bear each skill's functionality within it.
	 * @param target the enemy that is currently in combat (which may or may not be used for the ability's effects).
	 */
	public void activate(Enemy target)
	{
		//OVERRIDE THIS
	}
	
	/**
	 * 
	 * Purpose: check if the ability is passive or not. DEPRECATED.
	 * @return true if passive, false if active
	 */
	public boolean getPassive()
	{
		return isPassive;
	}
	
	/**
	 * 
	 * Purpose: getter for the skill name
	 * @return the skillName value
	 */
	public String getName()
	{
		return skillName;
	}
	
	
	@Override
	public String toString()
	{
		if (this.isPassive)
		{
			return "Passive Bonus: " + skillName;
		}
		else
		{
			return "Skill: " + skillName;
		}
	}

}
