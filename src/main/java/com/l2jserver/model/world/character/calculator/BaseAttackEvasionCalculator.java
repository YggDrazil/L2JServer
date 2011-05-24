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
package com.l2jserver.model.world.character.calculator;

import org.apache.commons.math.util.FastMath;

import com.l2jserver.model.world.L2Character;
import com.l2jserver.util.calculator.AbstractFunction;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class BaseAttackEvasionCalculator extends CharacterCalculator {
	@SuppressWarnings("unchecked")
	public BaseAttackEvasionCalculator() {
		super(new AbstractFunction<CharacterCalculatorContext>(0x000) {
			@Override
			public void calculate(CharacterCalculatorContext ctx) {
				ctx.result = ctx.character.getTemplate().getEvasionChance();
			}
		}, new AbstractFunction<CharacterCalculatorContext>(0x100) {
			@Override
			public void calculate(CharacterCalculatorContext ctx) {
				final L2Character c = ctx.character;
				final int level = c.getLevel();

				ctx.result += FastMath.sqrt(c.getStats().getDexterity()) * 6;
				ctx.result += level;
				if (level > 77)
					ctx.result += (level - 77) + 1;
				if (level > 69)
					ctx.result += (level - 69);
			}
		});
	}
}
