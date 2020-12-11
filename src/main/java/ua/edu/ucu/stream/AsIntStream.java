package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.MyIterator;
import ua.edu.ucu.iterators.FilterIterator;
import ua.edu.ucu.iterators.MapIterator;
import ua.edu.ucu.iterators.FlatMapIterator;

import java.util.ArrayList;
import java.util.Iterator;

public class AsIntStream implements IntStream {
    private Iterator<Integer> iterator;

    private AsIntStream(Iterator<Integer> i) {
        iterator = i;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(new MyIterator(convert(values)));
    }

    public static Integer[] convert(int... values) {
        Integer[] newValues = new Integer[values.length];
        for (int i = 0; i < values.length; i++) {
            newValues[i] = values[i];
        }
        return newValues;
    }

    @Override
    public Double average() {
        Double mySum = 0.0;
        int counter = 0;
        for (Iterator<Integer> it = iterator; it.hasNext(); ) {
            mySum += it.next();
            counter++;
        }
        return mySum / counter;  // counter will be implicitly converted to
                                 // Double
    }

    @Override
    public Integer max() {
        Integer i;
        Integer myMax = 0;
        for (Iterator<Integer> it = iterator; it.hasNext(); ) {
            if ((i = it.next()) > myMax) {
                myMax = i;
            }
        }
        return myMax;
    }

    @Override
    public Integer min() {
        Integer i;
        Integer myMin = 0;
        for (Iterator<Integer> it = iterator; it.hasNext(); ) {
            if ((i = it.next()) < myMin) {
                myMin = i;
            }
        }
        return myMin;
    }

    @Override
    public long count() {
        long counter = 0;
        for (Iterator<Integer> it = iterator; it.hasNext(); it.next()) {
            counter++;
        }
        return counter;
    }

    @Override
    public Integer sum() {
        Integer mySum = 0;
        for (Iterator<Integer> it = iterator; it.hasNext(); ) {
            mySum += it.next();
        }
        return mySum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(iterator, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        for (Iterator<Integer> it = iterator; it.hasNext(); ) {
            action.accept(it.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(iterator, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(iterator, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        for (Iterator<Integer> it = iterator; it.hasNext(); ) {
            identity = op.apply(identity, it.next());
        }
        return identity;
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> list = new ArrayList<>();
        for (Iterator<Integer> it = iterator; it.hasNext(); ) {
            list.add(it.next());
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

}
