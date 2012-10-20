package abbot.collection.util.range;

import com.google.common.collect.Range;

import java.util.Comparator;

public class RangeComparators {

    private static <T extends Comparable> int compareLower(Range<T> range1, Range<T> range2){
        if (range1.hasLowerBound()) {
            if (range2.hasLowerBound())
                return range1.lowerEndpoint().compareTo(range2.lowerEndpoint());
            else
                return 1;
        } else {
            if (range2.hasLowerBound())
                return -1;
            else
                return 0;
        }
    }

    private static <T extends Comparable> int compareUpper(Range<T> range1, Range<T> range2){
        if (range1.hasUpperBound()) {
            if (range2.hasUpperBound())
                return range1.upperEndpoint().compareTo(range2.upperEndpoint());
            else
                return -1;
        } else {
            if (range2.hasUpperBound())
                return 1;
            else
                return 0;
        }
    }

    /**
     * Compares only the lower bound of the ranges, ignoring the upper bound.
     * @param <T>
     * @return -1 (less), 0 (equal), 1 (greater)
     */
    public static <T extends Comparable> Comparator<Range<T>> lowerOnlyComparator() {
        return new Comparator<Range<T>>() {
            @Override
            public int compare(Range<T> range1, Range<T> range2) {
                return compareLower(range1, range2);
            }
        };
    }

    /**
     * Compares only the upper bound of the ranges, ignoring the lower bound.
     * @param <T>
     * @return -1 (less), 0 (equal), 1 (greater)
     */
    public static <T extends Comparable> Comparator<Range<T>> upperOnlyComparator() {
        return new Comparator<Range<T>>() {
            @Override
            public int compare(Range<T> range1, Range<T> range2) {
                return compareUpper(range1, range2);
            }
        };
    }

    /**
     * Compares the lower bound of the ranges and if equal then uses the upper bound.
     * @param <T>
     * @return -1 (less), 0 (equal), 1 (greater)
     */
    public static <T extends Comparable> Comparator<Range<T>> lowerBiasedComparator() {
        return new Comparator<Range<T>>() {
            @Override
            public int compare(Range<T> range1, Range<T> range2) {
                int initial = compareLower(range1, range2);
                if (initial != 0)
                    return initial;

                return compareUpper(range1, range2);
            }
        };
    }

    /**
     * Compares the upper bound of the ranges and if equal then uses the lower bound.
     * @param <T>
     * @return -1 (less), 0 (equal), 1 (greater)
     */
    public static <T extends Comparable> Comparator<Range<T>> upperBiasedComparator() {
        return new Comparator<Range<T>>() {
            @Override
            public int compare(Range<T> range1, Range<T> range2) {
                int initial = compareUpper(range1, range2);
                if (initial != 0)
                    return initial;

                return compareLower(range1, range2);
            }
        };
    }

}
