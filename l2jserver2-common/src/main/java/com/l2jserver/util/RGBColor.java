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
package com.l2jserver.util;

/**
 * An RED-GREEN-BLUE color
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class RGBColor {
	/**
	 * The red value
	 */
	private final byte red;
	/**
	 * The green value
	 */
	private final byte green;
	/**
	 * The blue value
	 */
	private final byte blue;

	/**
	 * Creates a new RGB (red-green-blue) color
	 * 
	 * @param r
	 *            the red byte
	 * @param g
	 *            the green byte
	 * @param b
	 *            the blue byte
	 */
	protected RGBColor(byte r, byte g, byte b) {
		this.red = r;
		this.green = g;
		this.blue = b;
	}

	/**
	 * @return the red
	 */
	public byte getRed() {
		return red;
	}

	/**
	 * @return the green
	 */
	public byte getGreen() {
		return green;
	}

	/**
	 * @return the blue
	 */
	public byte getBlue() {
		return blue;
	}

	/**
	 * Converts to an byte array
	 * 
	 * @return an byte array of this color
	 */
	public byte[] toByteArray() {
		return new byte[] { red, green, blue };
	}

	/**
	 * Convers this color into an integer
	 * 
	 * @return the color integer
	 */
	public int toInteger() {
		return (red >> 24) + (green >> 16) + (blue >> 8);
	}

	/**
	 * Creates an {@link RGBColor} from an byte array
	 * 
	 * @param rgb
	 *            the RGB byte array
	 * @return the {@link RGBColor}
	 */
	public static RGBColor fromByteArray(byte[] rgb) {
		return new RGBColor(rgb[0], rgb[1], rgb[2]);
	}

	/**
	 * @param color
	 *            the color integer
	 * @return the {@link RGBColor}
	 */
	public static RGBColor fromInteger(int color) {
		return new RGBColor((byte) (color << 0), (byte) (color << 8),
				(byte) (color << 16));
	}
}
