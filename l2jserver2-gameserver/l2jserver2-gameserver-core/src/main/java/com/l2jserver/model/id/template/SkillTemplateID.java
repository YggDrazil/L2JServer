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
package com.l2jserver.model.id.template;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.l2jserver.model.id.TemplateID;
import com.l2jserver.model.template.SkillTemplate;
import com.l2jserver.service.game.template.TemplateService;

/**
 * An {@link TemplateID} instance representing an {@link SkillTemplate} object
 * 
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 */
public class SkillTemplateID extends TemplateID<SkillTemplate, Integer> {
	/**
	 * The template service
	 */
	private final TemplateService templateService;

	/**
	 * @param id
	 *            the raw id
	 * @param templateService
	 *            the template service
	 */
	@Inject
	public SkillTemplateID(@Assisted int id, TemplateService templateService) {
		super(id);
		this.templateService = templateService;
	}

	@Override
	public SkillTemplate loadTemplate() {
		return templateService.getTemplate(this);
	}
}
