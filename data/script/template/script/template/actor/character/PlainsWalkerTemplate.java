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
import com.l2jserver.model.id.template.provider.CharacterTemplateIDProvider;
import com.l2jserver.model.world.L2Character;
import com.l2jserver.model.world.character.CharacterClass;
import com.l2jserver.util.dimensional.Point;

public class PlainsWalkerTemplate extends ElvenScoutTemplate {
	@Inject
	public PlainsWalkerTemplate(CharacterTemplateIDProvider provider) {
		super(provider.createID(CharacterClass.PLAINS_WALKER.id), CharacterClass.PLAINS_WALKER,	Point.fromXYZ(45978, 41196, -3440));
		
		this.hpBase = 1024.200;
		this.hpAdd = 46.800;
		this.hpMultiplier = 0.37;
		this.mpBase = 359.100;
		this.mpAdd = 19.500;
		this.mpMultiplier = 0.14;
		this.cpBase = 563.310;
		this.cpAdd = 25.740;
		this.cpMultiplier = 0.22;
		this.minimumLevel = 40;
		
		// ATTRIBUTES
		attributes.intelligence = 23;
		attributes.strength = 36;
		attributes.concentration = 36;
		attributes.mentality = 26;
		attributes.dexterity = 35;
		attributes.witness = 14;
		attributes.physicalAttack = 4;
		attributes.magicalAttack = 6;
		attributes.physicalDefense = 80;
		attributes.magicalDefense = 41;
		attributes.attackSpeed = 300;
		attributes.castSpeed = 333;
		attributes.accuracy = 36;
		attributes.criticalChance = 46;
		attributes.evasionChance = 36;
		attributes.runSpeed = 125;
		attributes.walkSpeed = 125;
		attributes.maxWeigth = 73000;
		attributes.craft = false;
		
		this.maleCollisionRadius = 7.5;
		this.maleCollisionHeight = 24.0;
		this.femaleCollisionRadius = 7.5;
		this.femaleCollisionHeight = 23.0;
	}
	
	protected PlainsWalkerTemplate(CharacterTemplateID id,
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
