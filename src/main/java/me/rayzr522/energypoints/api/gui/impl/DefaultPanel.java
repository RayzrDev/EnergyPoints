package me.rayzr522.energypoints.api.gui.impl;

import me.rayzr522.energypoints.api.gui.*;
import me.rayzr522.energypoints.api.gui.event.ClickEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DefaultPanel implements Panel {
    private final Map<Point, Component> children = new HashMap<>();
    private final Bounds bounds;
    private Panel parent;

    /**
     * Creates a new grid panel with the given bounds.
     *
     * @param bounds The bounds of the panel.
     */
    public DefaultPanel(Bounds bounds) {
        this.bounds = bounds;
    }

    @Override
    public RenderContext getRenderContext() {
        if (getParent() == null) {
            throw new IllegalStateException("Attempted to get render context before being set as child!");
        }

        return new OffsetRenderContext(getParent().getRenderContext(), getBounds().getX(), getBounds().getY());
    }

    @Override
    public void queueRender() {
        render(getRenderContext());
    }

    @Override
    public List<Component> getChildren() {
        return children;
    }

    public void addChild(Bounds bounds, Component child) {
        children.add(child);
    }

    public void removeChild(Component child) {
        children.remove(child);
    }

    public Optional<Component> getChildAt(int x, int y) {
        return children.stream()
                .filter(child -> child.getBounds().isWithinBounds(x, y))
                // Get last element of stream
                .reduce((a, b) -> b);
    }

    @Override
    public Bounds getBounds() {
        return bounds;
    }

    @Override
    public Panel getParent() {
        return parent;
    }

    @Override
    public void setParent(Panel parent) {
        this.parent = parent;
    }

    @Override
    public void render(RenderContext renderContext) {
        children.forEach(child -> child.render(renderContext));
    }

    @Override
    public void onClick(ClickEvent e) {
        int relativeOffsetX = 0;
        int relativeOffsetY = 0;

        Panel next = this;
        while ((next = next.getParent()) != null) {
            relativeOffsetX += next.getBounds().getX();
            relativeOffsetY += next.getBounds().getY();
        }

        getChildAt(e.getX() - relativeOffsetX, e.getY() - relativeOffsetY).ifPresent(child -> child.onClick(e));
    }
}
