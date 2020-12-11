package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

import java.util.Iterator;

public class FilterIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private IntPredicate predicate;
    private Integer nextInt;

    public FilterIterator(Iterator<Integer> i, IntPredicate p) {
        iterator = i;
        predicate = p;
    }

    @Override
    public boolean hasNext() {
        nextInt = iterator.next();
        while (iterator.hasNext() && !predicate.test(nextInt))
            nextInt = iterator.next();
        return nextInt != null;
    }

    @Override
    public Integer next() {
        return nextInt;
    }
}
