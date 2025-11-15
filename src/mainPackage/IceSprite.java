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
* Version: 2025-11-14
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
		else
		{
			target.takeDamage(5);
		}
	}

	@Override
	public void takeDamage(int damage)
	{
		if(!hardened)
		{
			health -= damage;
			System.out.println("Ice Sprite takes " + damage + " dmg!");
			
			if (health <= 10)
			{
				System.out.println("Ice Sprite hardens its body, becoming sturdier!");
			}
		}
		else
		{
			int reduceDamage =(int) ((int) damage * 0.75);
			
			health -= reduceDamage;
			
			System.out.println("Ice Sprite takes a reduced " + reduceDamage + " dmg!");
		}
	}

	@Override
	public String scanForInfo()
	{
		return "Ice Sprite: A living mass of ice. At low health, raises its defenses. It may have a weakness...";
	}

}
