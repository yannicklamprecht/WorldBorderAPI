package com.github.yannicklamprecht.worldborder.api;


/**
 * WorldBorderAction represents the different actions that can be performed on a world border.
 */
public enum WorldBorderAction {
    /**
     * Initializes the world border.
     */
    INITIALIZE,
    /**
     * Lerp the size of the world border.
     */
    LERP_SIZE,
    /**
     * Lerp the center of the world border.
     */
    SET_CENTER,
    /**
     * Sets the size of the world border.
     */
    SET_SIZE,
    /**
     * Sets the damage buffer of the world border.
     */
    SET_WARNING_BLOCKS,
    /**
     * Sets the warning time of the world border.
     */
    SET_WARNING_TIME
}
