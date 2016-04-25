package edu.csula.datascience.acquisition;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * A mock source to provide data
 */
public class MockSource implements Source<M> {
    int index = 0;

    @Override
    public boolean hasNext() {
        return index < 1;
    }

    @Override
    public Collection<M> next() {
        return Lists.newArrayList(
            new M("1", null),
            new M("2", "content2"),
            new M("3", "content3")
        );
    }
}
