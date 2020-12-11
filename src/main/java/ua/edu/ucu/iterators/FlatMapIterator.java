package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.stream.AsIntStream;

import java.util.Iterator;

public class FlatMapIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private IntToIntStreamFunction function;
    private Iterator<Integer> temp = null;

    public FlatMapIterator(Iterator<Integer> i, IntToIntStreamFunction f) {
        iterator = i;
        function = f;
    }

    @Override
    public boolean hasNext() {
        if (temp != null && temp.hasNext()) {
            return true;
        }
        if (iterator.hasNext()) {
            int[] array = function.applyAsIntStream(iterator.next())
                                  .toArray();
            temp = new MyIterator(AsIntStream.convert(array));
            return true;
        }
        return false;
    }

    @Override
    public Integer next() {
        return temp.next();
    }
}
