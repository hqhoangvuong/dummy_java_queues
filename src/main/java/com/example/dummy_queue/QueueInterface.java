package com.example.dummy_queue;

public interface QueueInterface<T> {
    /**
     * Adds a new entry to the back of this queue.
     * 
     * @param newEntry An object to be added.
     */
    public void enqueue(T newEntry);

    /**
     * Removes and returns the entry at the front of this queue.
     * 
     * @return The object at the front of the queue.
     * @throws EmptyQueueException if the queue is empty before the operation.
     */
    public T dequeue() throws Exception;

    /**
     * Retrieves the entry at the front of this queue.
     * 
     * @return The object at the front of the queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    public T getFront() throws Exception;

    /**
     * Detects whether this queue is empty.
     * 
     * @return True if the queue is empty, or false otherwise.
     */
    public boolean isEmpty();

    /** Removes all entries from this queue. */
    public void clear();

    public void splice(QueueInterface<T> anotherQueue) throws Exception;
}
