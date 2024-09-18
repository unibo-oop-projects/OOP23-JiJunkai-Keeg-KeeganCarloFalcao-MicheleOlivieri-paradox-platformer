package com.project.paradoxplatformer.utils;

/**
 * A contract to make a class suitable for beieng An Immutable Object by providing its defensive
 * copy. <p>Such interface is a {@code FunctionalInterface} allowing it to build easy and readable lambda 
 * expressions.</p>
 * @param <C> the type of object to make immutable
 */

@FunctionalInterface
public interface Immutable<C> {
    /**
     * Provides the defensive copy within the current object. However, it is up to implementation
     * responsabilty not to return the same instance (using this) as it would break the 
     * immutability clause.
     * @return the deep copy of the object
     */
    C defensiveCopy();
}
