package com.eightfold.transformer.merger;

import com.eightfold.transformer.constants.SourceType;

import java.util.*;

public class MergePolicy {

    private static final Map<SourceType, Integer> PRIORITY = new HashMap<>();

    static {
        PRIORITY.put(SourceType.RECRUITER_CSV, 1);
        PRIORITY.put(SourceType.RESUME, 2);
    }

    private MergePolicy() {
    }

    /**
     * Returns true if sourceA has higher priority than sourceB.
     */
    public static boolean isHigherPriority(SourceType sourceA,
                                           SourceType sourceB) {

        return PRIORITY.get(sourceA) < PRIORITY.get(sourceB);
    }

    /**
     * Generic merge for Strings.
     */
    public static String mergeString(String first,
                                     String second) {

        if (first != null && !first.isBlank()) {
            return first;
        }

        return second;
    }

    /**
     * Merge list without duplicates.
     */
    public static <T> List<T> mergeList(List<T> first,
                                        List<T> second) {

        LinkedHashSet<T> merged = new LinkedHashSet<>();

        if (first != null) {
            merged.addAll(first);
        }

        if (second != null) {
            merged.addAll(second);
        }

        return new ArrayList<>(merged);
    }

}