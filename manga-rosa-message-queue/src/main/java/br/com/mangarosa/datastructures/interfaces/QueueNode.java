package br.com.mangarosa.datastructures.interfaces;


public class QueueNode<T> {
    T value;
    QueueNode<T> next;

    public QueueNode(T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setNext(QueueNode<T> next) {
        this.next = next;
    }

    public QueueNode<T> getNext() {
        return next;
    }
}