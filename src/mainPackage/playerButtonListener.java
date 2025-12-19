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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * Purpose: The reponsibility of playerButtonListener is ...
 *
 * playerButtonListener is-a ...
 * playerButtonListener is ...
 */
public class playerButtonListener implements ActionListener
{
	private int seed;
	private PlayerCharacter player;
	private CombatManager combat;
	private GameScreenGUI screen;

	/**
	 * Purpose: The general constructor for the button listener for all playerButtons- has different actions performed based on the button's "Seed"
	 * @param inSeed the tracker so it knows what it does when the button is pressed.
	 * @param inPlayer the playerCharacter object, needed for activating playerCharacter methods
	 * @param inCombat the combatManager, needed for knowing what the current enemy is
	 * @param inScreen the GameScreenGUI, needed to send the Skills info to the GUI so it can generate the skill buttons.
	 */
	public playerButtonListener(int inSeed, PlayerCharacter inPlayer, CombatManager inCombat, GameScreenGUI inScreen)
	{
		seed = inSeed;
		player = inPlayer;
		combat = inCombat;
		screen = inScreen;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (seed)
		{
			case 0: //attack case
				player.attack(combat.getEnemy());
				break;
				
			case 1: //focus case
				player.focus();
				break;
				
			case 2: //skills case
				Ability[] knownAbilities = player.listSkills();
				
				//What we NEED to do is create either a new widget, a new window, or a JOptionPane
				//that lists the skills with buttons, then calls player.activateSkill(x) where x is the skill to activate.
				if (knownAbilities.length > 0 && player.getMana() > 0)
				{
					for (int i = 0; i < knownAbilities.length; i++)
					{
						System.out.println(knownAbilities[i].getName());
					}		
					screen.generateSkillMenu(knownAbilities);
				}
				else if(player.getMana() <= 0) //no mana, no skills using
				{
					JOptionPane.showMessageDialog(null, "Out of Mana! Can't use skills...", " ", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					System.out.println("No Known Skills!");
				}
				break;
				
			case 3: //info case
				player.scan();
				break;
		}
	}

}
