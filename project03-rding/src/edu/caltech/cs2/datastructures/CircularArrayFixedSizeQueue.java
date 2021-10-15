package edu.caltech.cs2.datastructures;

import edu.caltech.cs2.interfaces.IFixedSizeQueue;

import java.util.Iterator;

public class CircularArrayFixedSizeQueue<E> implements IFixedSizeQueue<E> {
    private E[] data;
    private int size;
    private int front;

    // back = (front + this.size - 1) % (this.capacity - 1)
    // new front = new front % (this.capacity - 1)

    public CircularArrayFixedSizeQueue(int initialCapacity) {
        this.data = (E[])new Object[initialCapacity];
        this.size = 0;
        this.front = 0;
    }

    @Override
    public boolean isFull() {
        return (this.size == this.data.length);
    }

    @Override
    public int capacity() {
        return this.data.length;
    }

    @Override
    public boolean enqueue(E e) {
        if (isFull()) {
            return false;
        }
        int back = (front + this.size) % (this.capacity());
        this.data[back] = e;
        this.size++;
        return true;
    }

    @Override
    public E dequeue() {
        if (this.size == 0) {
            return null;
        }
        E originalData = data[this.front];
        this.data[this.front] = null;
        this.front = (this.front + 1) % (this.capacity());
        this.size--;
        return originalData;
    }

    @Override
    public E peek() {
        return this.data[this.front];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularArrayFixedSizeQueueIterator();
    }

    public class CircularArrayFixedSizeQueueIterator implements Iterator<E> {
        private int idx;

        public CircularArrayFixedSizeQueueIterator() {
            this.idx = CircularArrayFixedSizeQueue.this.front - 1;
        }

        public boolean hasNext() {
            return this.idx < CircularArrayFixedSizeQueue.this.size - 1;
        }

        public E next() {
            this.idx++;
            return CircularArrayFixedSizeQueue.this.data[this.idx];
        }
    }

    public String toString() {
        if (this.size == 0) {
            return "[]";
        }

        String result = "[";
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] != null) {
                result += this.data[i] + ", ";
            }
        }

        result = result.substring(0, result.length() - 2);
        return result + "]";
    }
}
