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
	private String name;
	private int weakness; //elemental weaknesses. 0 = none, 1 = fire, 2 = ice,
	private int health;
	private int difficultyVal; //maybe? If we're not doing sequential floors and we're doing random enemies, we'll want a diffValue to randomize
	private int enemySeed; //juuust in case, we can define enemies by their seed and use a lot of switch cases
	
	//enemy key: 0 = goblin, 1 = ice sprite, 99 = demon
	
	//Superconstructor
	public Enemy(int inSeed)
	{
		switch(inSeed)
		{
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
				
			case 99:
				name = "Great Demon";
				health = 99;
				weakness = 0;
				difficultyVal = 10;
				enemySeed = 1;
				break;
		}	
	}
	
	public abstract void takeTurn();

}
