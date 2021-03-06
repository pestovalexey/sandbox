/**
 * JBoss, Home of Professional Open Source
 * Copyright , Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.richfaces.bootstrap.component;

import org.richfaces.bootstrap.renderkit.AccordionRendererBase;
import org.richfaces.cdk.annotations.*;
import org.richfaces.component.attribute.EventsMouseProps;

import javax.faces.component.UIPanel;

/**
 * Base class for the accordion component
 *
 * @author <a href="http://community.jboss.org/people/bleathem">Brian Leathem</a>
 */
@JsfComponent(
        type = AbstractAccordion.COMPONENT_TYPE,
        family = AbstractAccordion.COMPONENT_FAMILY,
        renderer = @JsfRenderer(type = AccordionRendererBase.RENDERER_TYPE),
        tag = @Tag(name="accordion"),
        attributes = "events-mouse-props.xml")
public abstract class AbstractAccordion extends UIPanel implements EventsMouseProps {
    public static final String COMPONENT_FAMILY = "org.richfaces.bootstrap.Accordion";
    public static final String COMPONENT_TYPE = "org.richfaces.bootstrap.Accordion";

    /**
     * Javascript code executed when a pointer button is pressed down over this element.
     */
    @Attribute(events = @EventName(value = "show"))
    public abstract String getOnshow();

    /**
     * Javascript code executed when this component is hidden.
     */
    @Attribute(events = @EventName(value = "hide"))
    public abstract String getOnhide();


}
