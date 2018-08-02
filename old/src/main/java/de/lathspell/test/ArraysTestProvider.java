package de.lathspell.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArraysTestProvider {

    /**
     * Get a Java primitives array.
     */
    public static int[] getIntArray() {
        int[] ia = new int[4];
        ia[ia.length - 1] = 4;
        return ia;
    }

    /**
     * Get a Java Object array.
     */
    public static Integer[] getIntegerArray() {
        Integer[] iia = new Integer[]{0, 0, 0, 0}; // default is { null, null, null, null }
        iia[iia.length - 1] = 4;
        return iia;
    }

    /**
     * Get a Java Collection array.
     */
    public static List<Integer> getCollectionArray() {
        ArrayList<Integer> ci = new ArrayList<>();
        Collections.addAll(ci, 0, 0, 0, 0);
        ci.set(ci.size() - 1, 4);
        return ci;
    }

}
