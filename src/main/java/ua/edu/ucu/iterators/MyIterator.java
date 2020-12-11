package ua.edu.ucu.iterators;

import java.util.Iterator;

public class MyIterator implements Iterator<Integer> {
    private int curPos = 0;
    private Integer[] integers;

    public MyIterator(Integer[] i) {
        integers = i.clone();
    }

    @Override
    public boolean hasNext() {
        return curPos < integers.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        }
        return integers[curPos++];
    }
}
