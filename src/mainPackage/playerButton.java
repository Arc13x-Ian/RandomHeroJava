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
* Version: 2025-11-07
*/
package mainPackage;

import javax.swing.JButton;

/**
 * Purpose: The reponsibility of playerButton is to be the action button on the GUI, 
 *
 * playerButton is-a ...
 * playerButton is ...
 */
public class playerButton extends JButton
{
	//fields
	
	//constructor
	public playerButton(int seed, PlayerCharacter player, CombatManager combat)
	{		
		switch (seed)
		{
			case 0: //attack case
				this.setText("ATTACK");
				break;
				
			case 1: //focus case
				this.setText("FOCUS");
				break;
				
			case 2: //skills case
				this.setText("SKILLS");
				break;
				
			case 3:
				this.setText("INFO");
				break;
		}
		
		this.addActionListener(new playerButtonListener(seed, player, combat));
	}
}
