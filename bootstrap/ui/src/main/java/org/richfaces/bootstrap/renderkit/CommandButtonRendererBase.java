package org.richfaces.bootstrap.renderkit;

import org.richfaces.bootstrap.component.AbstractCommandButton;
import org.richfaces.component.Mode;
import org.richfaces.renderkit.AjaxCommandRendererBase;
import org.richfaces.renderkit.util.HandlersChain;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * Base class for the commandButton renderer
 *
 * @author <a href="http://community.jboss.org/people/bleathem">Brian Leathem</a>
 */
@ResourceDependencies({
        @ResourceDependency(library = "org.richfaces", name = "ajax.reslib"),
        @ResourceDependency(library = "bootstrap/css", name = "bootstrap.css"),
        @ResourceDependency(library = "bootstrap/js", name = "bootstrap.js"),
        @ResourceDependency(library = "font-awesome/css", name = "font-awesome.css")})
public class CommandButtonRendererBase extends org.richfaces.renderkit.html.CommandButtonRendererBase {
    public static final String RENDERER_TYPE = "org.richfaces.bootstrap.CommandButtonRenderer";

    // A workaround for RF-11668
    public AbstractCommandButton castComponent(UIComponent component) {
        return (AbstractCommandButton) component;
    }

    @Override
    public void doDecode(FacesContext context, UIComponent component) {
        AbstractCommandButton commandButton = (AbstractCommandButton) component;
        if (commandButton != null) {
            if (!Mode.client.equals(commandButton.getMode())) {
                super.doDecode(context, component);
            }
        }
    }

    @Override
    public String getOnClick(FacesContext context, UIComponent component) {
        AbstractCommandButton commandButton = (AbstractCommandButton) component;
        Mode submitMode = commandButton.getMode();
        StringBuffer onClick = new StringBuffer();

        if (!commandButton.isDisabled()) {
            HandlersChain handlersChain = new HandlersChain(context, component);

            handlersChain.addInlineHandlerFromAttribute("onclick");
            handlersChain.addBehaviors("click", "action");

            switch (submitMode) {
                case ajax:
                    handlersChain.addAjaxSubmitFunction();
                    break;
                case server:
                    handlersChain.addInlineHandlerAsValue("RichFaces.submitForm(event.form, event.itemId)");
            }

            String handlerScript = handlersChain.toScript();

            if (handlerScript != null) {
                onClick.append(handlerScript);
            }

            if (!"reset".equals(component.getAttributes().get("type"))) {
                onClick.append(";return false;");
            }
        } else {
            onClick.append("return false;");
        }

        return onClick.toString();
    }
}
