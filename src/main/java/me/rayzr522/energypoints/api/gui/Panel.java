package me.rayzr522.energypoints.api.gui;

import java.util.List;

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
}
