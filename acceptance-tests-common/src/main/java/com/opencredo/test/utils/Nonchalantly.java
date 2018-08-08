package com.opencredo.test.utils;


public interface Nonchalantly<T> {
    static <T, E extends Throwable> T invoke(Nonchalantly<T> f) throws E {
        try {
            return f.run();
        } catch (Throwable e) {
            throw (E) e;
        }
    }

    T run() throws Throwable;
}