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

import javax.swing.JButton;

/**
 * Purpose: The reponsibility of SkillButton is ...
 *
 * SkillButton is-a ...
 * SkillButton is ...
 */
public class SkillButton extends JButton
{
	//fields
	private Ability attachedSkill;
	private Enemy combatEnemy;
	private CombatManager combat;
	//constructors
	
	/**
	 * 
	 * Purpose: DEPRECATED constructor for a button class for skills
	 * @param inSkill the skill attached to the button
	 * @param inCombat the combat manager
	 */
	public SkillButton(Ability inSkill, CombatManager inCombat)
	{
		attachedSkill = inSkill;
		combat = inCombat;
		
		combatEnemy = combat.getEnemy();
		
		this.setText(attachedSkill.getName());
	}
}
