package edu.csula.datascience.acquisition;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A mock implementation of collector for testing
 */
public class MockCollector implements Collector<S, M> {
    @Override
    public Collection<S> mungee(Collection<M> src) {
        // in your example, you might need to check src.hasNext() first
        return src
            .stream()
            .filter(data -> data.getContent() != null)
            .map(S::build)
            .collect(Collectors.toList());
    }

    @Override
    public void save(Collection<S> data) {
    }

	
}
