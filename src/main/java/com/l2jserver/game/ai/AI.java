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
package com.l2jserver.game.ai;

import com.l2jserver.game.ai.desires.Desire;
import com.l2jserver.game.ai.desires.DesireQueue;
import com.l2jserver.model.world.Actor;

/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * @param <T>
 *            the {@link Actor} type for this {@link AI}
 */
public abstract class AI<T extends Actor> {
	protected DesireQueue desireQueue = new DesireQueue();
	protected final T creature;

	protected AI(T creature) {
		this.creature = creature;
	}

	protected void handleDesire(Desire desire) {
		desire.handleDesire(this);
	}
}
