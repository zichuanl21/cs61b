package synthesizer;

import synthesizer.AbstractBoundedQueue;
import java.util.Iterator;

/* Invariants
1. first defines the index of the first item.
2. last defines the index of next item to be added.
3. plusOne(last) gives the index of the next item to be added.
4. The fillCount will always be <= capacity.
*/

/**
 * Something like a queue implemented using a circular array.
 * @author Arjun Nair
 */
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.first = 0;
        this.last = 0;
        this.capacity = capacity;
        this.fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Makes sure that last is always within the array index.
     * @param index The index 1 is being added to.
     */
    protected int plusOne(int index) {
        return (index + 1) % this.capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        this.rb[this.last] = x;
        this.last = plusOne(this.last);
        this.fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        T firstItem = this.rb[this.first];
        this.rb[this.first] = null;
        this.first = plusOne(this.first);
        this.fillCount -= 1;

        return firstItem;
    }

    /**
     * Return oldest item, but doesn't remove it.
     */
    public T peek() {
        return this.rb[this.first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}