package br.com.mangarosa.datastructures.interfaces.impl;

import br.com.mangarosa.datastructures.interfaces.Queue;
import br.com.mangarosa.datastructures.interfaces.QueueNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T extends Comparable<T>> implements Queue<T> {
    private QueueNode<T> first;
    private QueueNode<T> last;
    private int size;

    public LinkedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public void enqueue(T value) {
        QueueNode<T> oldLast = last;
        last = new QueueNode<>(value);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.setNext(last);
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty queue");
        }
        T value = first.getValue();
        first = first.getNext();
        size--;
        if (isEmpty()) {
            last = null;
        }
        return value;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty queue");
        }
        return first.getValue();
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T[] toArray() {
        T[] array = (T[]) new Comparable[size];
        int index = 0;
        for (QueueNode<T> current = first; current != null; current = current.getNext()) {
            array[index++] = current.getValue();
        }
        return array;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private QueueNode<T> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = current.getValue();
                current = current.getNext();
                return value;
            }
        };
    }
}
