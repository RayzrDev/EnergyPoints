package me.rayzr522.energypoints.api.gui;

import java.util.List;
import java.util.Optional;

public interface Panel extends Component {
    /**
     * @return The context to render to.
     */
    RenderContext getRenderContext();

    /**
     * Queues a render of the panel.
     */
    void queueRender();

    /**
     * @return The child components of this panel.
     */
    List<Component> getChildren();

    /**
     * Adds a child component.
     *
     * @param child The child component to add.
     */
    void addChild(Component child);

    /**
     * Removes a child component.
     *
     * @param child The child component to remove.
     */
    void removeChild(Component child);

    /**
     * Attempts to get the child component at the given relative location.
     *
     * @param x The X location of the child.
     * @param y The Y location of the child.
     * @return The component, if found.
     */
    Optional<Component> getChildAt(int x, int y);
}
