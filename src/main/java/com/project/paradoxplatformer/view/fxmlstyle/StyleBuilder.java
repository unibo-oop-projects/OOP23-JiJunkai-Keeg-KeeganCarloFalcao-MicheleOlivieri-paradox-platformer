
package com.project.paradoxplatformer.view.fxmlstyle;

@FunctionalInterface
public interface StyleBuilder<T> {
    void applyStyle(T element);
}
