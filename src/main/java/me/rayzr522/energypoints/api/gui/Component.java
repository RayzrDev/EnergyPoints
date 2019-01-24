package me.rayzr522.energypoints.api.gui;

import me.rayzr522.energypoints.api.gui.event.ClickEvent;

public interface Component {
    /**
     * @return The bounds of this component.
     */
    Bounds getBounds();

    /**
     * @return The parent panel of this component, if one is set.
     */
    Panel getParent();

    /**
     * @param parent The new parent panel to set for this component.
     */
    void setParent(Panel parent);

    /**
     * Renders this component to a render context.
     *
     * @param renderContext The context to render to.
     */
    void render(RenderContext renderContext);

    /**
     * Called when a player clicks this component.
     *
     * @param e The click event.
     */
    void onClick(ClickEvent e);
}
