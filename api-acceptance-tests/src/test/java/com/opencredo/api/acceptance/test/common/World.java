package com.opencredo.api.acceptance.test.common;

import java.util.HashMap;

/**
 * This class allows for sharing variables across multiple
 * step definitions within a single test scenario.
 * The world should be included in each stepdef file using
 * dependency injection (spring)
 */
public class World {
    public HashMap<String, String> sharedState = new HashMap<>();
}
