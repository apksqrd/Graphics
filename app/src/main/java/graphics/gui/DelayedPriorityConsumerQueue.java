package graphics.gui;

import java.util.PriorityQueue;
import java.util.function.Consumer;

/**
 * Runs the highest priority first.
 */
public class DelayedPriorityConsumerQueue<T> {
    private class PrioritizedConsumer implements Comparable<PrioritizedConsumer> {
        Consumer<T> consumer;
        double priority;

        public PrioritizedConsumer(Consumer<T> consumer, double priority) {
            this.consumer = consumer;
            this.priority = priority;
        }

        @Override
        public int compareTo(PrioritizedConsumer o) {
            return -Double.compare(this.priority, o.priority);
        }
    }

    private PriorityQueue<PrioritizedConsumer> consumers;

    public DelayedPriorityConsumerQueue() {
        consumers = new PriorityQueue<>();
    }

    public void addConsumer(Consumer<T> consumer, double priority) {
        consumers.add(new PrioritizedConsumer(consumer, priority));
    }

    public void acceptAll(T input) {
        while (!consumers.isEmpty()) {
            // Don't use forEach, pq doesn't work like that
            consumers.poll().consumer.accept(input);
        }
    }
}
