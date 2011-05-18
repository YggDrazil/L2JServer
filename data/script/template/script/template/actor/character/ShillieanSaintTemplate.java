/*
 * This file is part of l2jserver <l2jserver.com>.
 *
 * l2jserver is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver.  If not, see <http://www.gnu.org/licenses/>.
 */
package script.template.actor.character;

import com.google.inject.Inject;
import com.l2jserver.model.id.template.CharacterTemplateID;
import com.l2jserver.model.id.template.factory.CharacterTemplateIDFactory;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.util.dimensional.Point;

public class ShillieanSaintTemplate extends ShillienElderTemplate {
	@Inject
	public ShillieanSaintTemplate(CharacterTemplateIDFactory factory) {
		super(factory.createID(CharacterClass.SHILLIEAN_SAINT.id), CharacterClass.SHILLIEAN_SAINT,	Point.fromXYZ(28295, 11063, -4224));
		// ATTRIBUTES
		attributes.intelligence = 44;
		attributes.strength = 23;
		attributes.concentration = 24;
		attributes.mentality = 37;
		attributes.dexterity = 23;
		attributes.witness = 19;
		attributes.physicalAttack = 3;
		attributes.magicalAttack = 6;
		attributes.physicalDefense = 54;
		attributes.magicalDefense = 41;
		attributes.attackSpeed = 300;
		attributes.castSpeed = 333;
		attributes.accuracy = 29;
		attributes.criticalChance = 41;
		attributes.evasionChance = 29;
		attributes.moveSpeed = 122;
		attributes.maxWeigth = 61000;
		attributes.craft = false;
	}
	
	protected ShillieanSaintTemplate(CharacterTemplateID id,
			CharacterClass characterClass, Point spawnLocation) {
		super(id, characterClass, spawnLocation);
	}

	@Override
	public L2Character create() {
		final L2Character character = super.create();
		// TODO register skills
		return character;
	}
}
