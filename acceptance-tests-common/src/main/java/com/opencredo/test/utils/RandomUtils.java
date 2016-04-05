package com.opencredo.test.utils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class RandomUtils {
    protected static Random random = new Random(System.currentTimeMillis());
    public static String alphaCharacters = "abcdefghijklmnopqrstuvwxyz";
    public static String numericCharacters = "0123456789";

    public static synchronized int randomInt(final int min, final int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static synchronized String randomAlphaString(final int length) {
        return randomString(alphaCharacters, length);
    }

    public static synchronized String randomString(final String characters, final int length) {
        final char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length() - 1));
        }
        return new String(text);
    }

}
