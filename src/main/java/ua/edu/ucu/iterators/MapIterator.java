package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

import java.util.Iterator;

public class MapIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private IntUnaryOperator operator;

    public MapIterator(Iterator<Integer> i, IntUnaryOperator o) {
        iterator = i;
        operator = o;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Integer next() {
        return operator.apply(iterator.next());
    }
}
