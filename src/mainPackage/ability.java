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
public class ability
{
	//fields
	private String skillName;
	private int effectVal;
	private int mpCost;
	private int element; //0 = non elemental, 1 = fire, 2 = ice
	private boolean isPassive;
	private int seed;
	
	
	//constructor
	public void ability(int inSeed)
	{
		switch(inSeed)
		{
			case 0: //fire 1
				skillName = "Fire I";
				effectVal = 10;
				mpCost = 4;
				element = 1;
				seed = inSeed;
				
			case 1: //regen
				skillName = "Regen";
				effectVal = 4;
				mpCost = 8;
				element = 0;
				seed = inSeed;
				
			case 2: //double strike
				skillName = "Double Strike";
				effectVal = 10;
				mpCost = 2;
				element = 0;
				seed = inSeed;
				
		}
	}
	
	//methods
	
	public boolean getPassive()
	{
		return isPassive;
	}

}
