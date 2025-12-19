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
* Version: 2025-11-21
*/
package mainPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Purpose: The reponsibility of SkillButtonListener is ...
 *
 * SkillButtonListener is-a ...
 * SkillButtonListener is ...
 */
public class SkillButtonListener implements ActionListener
{
	//fields
	private Ability attachedSkill;
	private Enemy combatEnemy;
	private CombatManager combat;
	
	//constructor
	/**
	 * 
	 * Purpose: DEPRECATED actionlistener constructor for the deprecated skill buttons class
	 * @param inSkill the attached skill to activate on click
	 * @param inEnemy the enemy to target
	 * @param inCombat the combatManager
	 */
	public SkillButtonListener(Ability inSkill, Enemy inEnemy, CombatManager inCombat)
	{
		attachedSkill = inSkill;
		combatEnemy = inEnemy;
		combat = inCombat;
	}
	
	//method
	@Override
	public void actionPerformed(ActionEvent e)
	{
		attachedSkill.activate(combatEnemy);
	}

}
