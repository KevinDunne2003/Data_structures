package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;


/*
    Circularly linked list implementation
    Has tail set to as null
    head is the first value
    Different from my previous implementations
 */

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private  Node<E> tail = new Node<>(null, null);
    private  int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        // Done
        Node<E> current = tail.getNext();
        for (int j = 0; j < i; j++) {
            current = current.getNext();
        }
        return current.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int position, E e) {
        // Done
        Node<E> newNode = new Node<>(e, null);
        if (position == 0) {
            newNode.setNext(tail);
            tail.setNext(newNode);
        } else {
            Node<E> current = tail.getNext();
            for (int i = 1; i < position; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        size++;
    }

    @Override
    public E remove(int i) {
        Node<E> current = tail;
        for (int j = 0; j < i; j++) {
            current = current.getNext();
        }
        E removedData = current.getNext().getData();
        if (i == 0) {
            if (size == 1) {
                tail = null;
            } else {
                tail.setNext(current.getNext().getNext());
            }
        } else {
            Node<E> prev = tail;
            for (int j = 0; j < i - 1; j++) {
                prev = prev.getNext();
            }
            prev.setNext(current.getNext().getNext());
        }
        size--;
        return removedData;

    }

    public void rotate() {
        Node<E> current = tail.getNext();
        Node<E> first = tail.getNext();
        while(current.getNext()!=tail){
            current=current.getNext();
        }
        tail.setNext(tail.getNext().getNext());
        current.setNext(first);
        first.setNext(tail);
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        // Done
        if (size == 0) {
            return null;
        }
        E removedData = tail.getNext().getData();
        if (size == 1) {
            tail.setNext(null);
        } else {
            tail.setNext(tail.getNext().getNext());
        }
        size--;
        return removedData;
    }

    @Override
    public E removeLast() {
        // Done
        if (size == 0) {
            return null;
        }
        E removedData;
        if (size == 1) {
            removedData=tail.getNext().getData();
            tail.setNext(null);
        } else {
            Node<E> current = tail.getNext();
           while(current.getNext().getNext()!=tail){
               current=current.getNext();
           }
            removedData = current.getNext().getData();
            current.setNext(tail);
        }
        size--;
        return removedData;
    }

    @Override
    public void addFirst(E e) {
        // Done
        Node<E> newNode = new Node<>(e,null);
        if (size == 0) {
            tail.setNext(newNode);
            newNode.setNext(tail);
        } else {
            newNode.setNext(tail.getNext());
            tail.setNext(newNode);
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        // Done
        Node<E> newNode = new Node<>(e,null);
        if(size==0){
            tail.setNext(newNode);
            newNode.setNext(tail);
        }
        else{
            Node<E> current = tail.getNext();
            while(current.getNext()!=tail){
                current=current.getNext();
            }
            current.setNext(newNode);
            newNode.setNext(tail);
        }
        size ++;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr.getNext() != tail) {
                sb.append(", ");
            }
        } while (curr.getNext() != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
