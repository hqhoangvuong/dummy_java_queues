package com.example.dummy_queue;

// Suppose that we want to add a method to a class of queues that will splice two queues together. This method adds to the end of a queue all items that are in a second queue. The header of the method could be as follows:

// public void splice(QueueInterface<T> anotherQueue)

// Write this method in such a way that it will work in any class that implements QueueInterface.

// *****************************************************************************************************
public final class ArrayQueue<T> implements QueueInterface<T> {
    // Data
    private T[] queue;
    private int frontIndex;
    private int backIndex;
    private boolean integrityOK;
    private static final int DEFAULT_CAPACITY = 3;
    private static final int MAX_CAPACITY = 1000;

    // Constructor
    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayQueue(int initialCapacity) {
        integrityOK = false;
        checkCapacity(initialCapacity);
        T[] tempQueue = (T[]) new Object[initialCapacity + 1];
        queue = tempQueue;
        frontIndex = 0;
        backIndex = initialCapacity;
        integrityOK = true;
    }

    // Methods
    public void enqueue(T newEntry) {
        checkIntegrity();
        ensureCapacity();
        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = newEntry;
    }

    public T getFront() throws Exception {
        checkIntegrity();
        if (isEmpty()) {
            throw new Exception("Nothing to peak here!");
        } else {
            return queue[frontIndex];
        }
    }

    public T dequeue() throws Exception {
        checkIntegrity();
        if (isEmpty()) {
            throw new Exception("Nothing left to poop!");
        } else {
            T front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;
            return front;
        }
    }

    private void ensureCapacity() {
        // check it to see if array is full
        if (frontIndex == ((backIndex + 2) % queue.length)) {
            // Double the size of the array
            T[] oldQueue = queue;// make oldQueue to point to queue too
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            checkCapacity(newSize); // this should be less than that max capacity
            integrityOK = false;
            // create an array
            T[] tempQueue = (T[]) new Object[newSize];// unused location
            queue = tempQueue;// now queue has been created
            for (int index = 0; index < oldSize - 1; index++) {
                // we only need to copy the cells that contain value
                queue[index] = oldQueue[frontIndex];
                frontIndex = (frontIndex + 1) % oldQueue.length;
                // make sure frontIndex is increamented by one fro the size of queue
            }
            frontIndex = 0;
            backIndex = oldSize - 2;
            integrityOK = true;
        }
    }

    public boolean isEmpty() {
        checkIntegrity();
        return frontIndex == ((backIndex + 1) % queue.length);
    }

    public void clear() {
        checkIntegrity();
        if (!isEmpty()) {
            for (int index = frontIndex; index != backIndex; index = ((index + 1) % queue.length)) {
                queue[index] = null;
            }
            queue[backIndex] = null;
        }
        frontIndex = 0;
        backIndex = queue.length - 1;
    }

    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("Max Capacity Is Reached!");
        }
    }

    private void checkIntegrity() {
        if (!integrityOK) {
            throw new SecurityException("ArrayStack object is corrupt");
        }
    }

    // Splice two queues together
    public void splice(QueueInterface<T> anotherQueue) throws Exception{
        while (anotherQueue.isEmpty() == false) {
            T secondPoop;
            try {
                secondPoop = anotherQueue.dequeue();
                checkIntegrity();
                ensureCapacity();
                backIndex = (backIndex + 1) % queue.length;
                queue[backIndex] = secondPoop;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}