/*
 * This file is part of l2jserver2 <l2jserver2.com>.
 *
 * l2jserver2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * l2jserver2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with l2jserver2.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.model.world.actor.calculator;

import com.l2jserver.model.template.ActorTemplate;
import com.l2jserver.model.world.Actor;
import com.l2jserver.model.world.actor.stat.BaseStats;
import com.l2jserver.model.world.actor.stat.StatType;

/**
 * Calculates the character base magical attack
 * 
 * <pre>
 * ctx.result *= (((100.0 - 11 + c.getLevel()) / 100.0) &circ; 2)
 * 		* (BaseStats.INT.calculateBonus(c.getStats().getIntelligence()) &circ; 2);
 * </pre>
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class MagicalAttackBonusCalculator extends ActorFormula {
	/**
	 * Creates a new instance
	 */
	public MagicalAttackBonusCalculator() {
		super(0x200, StatType.MAGIC_ATTACK);
	}

	@Override
	protected double calculate(Actor a, ActorTemplate t, double value) {
		return value
				* Math.pow(((100.0 - 11 + a.getLevel()) / 100.0), 2)
				* Math.pow(BaseStats.INT.calculateBonus(a.getStats()
						.getIntelligence()), 2);
	}
}
