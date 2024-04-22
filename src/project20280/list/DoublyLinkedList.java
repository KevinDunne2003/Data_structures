package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private  E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            data = e;
            prev = p;
            next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        // TODO
    }

    @Override
    public int size() {
        // Done
        return size;
    }

    @Override
    public boolean isEmpty() {
        //Done
        if(size() == 0) {
            return true;
        }
        else return false;
    }

    @Override
    public E get(int position) {
        // Done
        //Create an instance of the Iterator type
        Iterator<E> iteration=iterator();

        //Loop through the nodes using the iterator
        //loop through to the object before what we want
        for(int i=0;i<position;i++) {
            iteration.next();
        }
        //return the instant of the variable that we need
        return iteration.next();

    }

    @Override
    public void add(int position, E e) {
        //Done
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Index: " + position + ", Size: " + size);
        }
        //
        if (position == 0) {
            DoublyLinkedList.Node<E> newNode = new DoublyLinkedList.Node<>(e,null,null);
            newNode.next=head.getNext();
            head.next = newNode;
        } else {
            DoublyLinkedList.Node<E> current = head.getNext();
            for (int i = 1; i < position-1; i++) {
                current = current.getNext();
            }
            DoublyLinkedList.Node<E> newNode = new DoublyLinkedList.Node<>(e,current,current.getNext());
            current.getNext().prev=newNode;
            current.next=newNode;
        }
        size++;

    }

    @Override
    public E remove(int position) {
        // Done
        E removedElement=null;
        DoublyLinkedList.Node<E> curr2 = (DoublyLinkedList.Node<E>) head.getNext();

        if(position == 0) {
            head.next = head.getNext().getNext();
        }
        else if(position==1){
            removeFirst();
        }
        else {
            for(int i=1;i<position-1;i++) {
                //E res = curr2.getData();
                curr2 = curr2.next;
            }

            //removedElement = curr2.getNext().getData();
            //curr2.setNext(curr2.getNext().getNext());
            removedElement=curr2.getData();
            curr2.next=curr2.getNext().getNext();
        }
        size --;

        return removedElement;
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head.next;

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
        return new DoublyLinkedListIterator<E>();
    }

    private E removeElement(Node<E> n) {
        // Done
        DoublyLinkedList.Node<E> curr2 = (DoublyLinkedList.Node<E>) head.getNext();

        while(curr2.getData()!=n){
            curr2=curr2.getNext();
        }
       if (curr2.getNext() == null) {
            curr2.getPrev().next=null;
            curr2.prev=null;
        }
        else if(curr2==head.getNext().getNext()){
            head.getNext().next=curr2.getNext();
            curr2.prev=null;
        }
        else {
            curr2.getPrev().next = curr2.getNext();
            curr2.getNext().prev = curr2.getPrev();
        }
        size --;

        return curr2.getData();
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getNext().getData();
    }

    public E last() {
        // TODO
        if(size==0){
            return null;
        } else if (size==1) {
            return head.getNext().getData();
        } else {
            DoublyLinkedList.Node<E> curr = (DoublyLinkedList.Node<E>) head.getNext();
            while (curr.getNext() != tail) {
                curr = curr.getNext();
            }
            return curr.getData();
        }
        //return tail.getData();
    }

    @Override
    public E removeFirst() {
        // Done
        if(size==0){
            return null;
        }
        DoublyLinkedList.Node<E> temp = head.getNext();

        head.next = head.getNext().next;
        size--;
        return temp.getData();
    }


    @Override
    public E removeLast() {
        // Done
        //E removedElement;
        DoublyLinkedList.Node<E> curr2 = (DoublyLinkedList.Node<E>) head.getNext();
        while(curr2.getNext()!= tail) {
            curr2=curr2.getNext() ;
        }

        curr2.getPrev().next=tail;
        //curr2.prev=null;
        tail.prev=curr2.prev;

        size--;

        return curr2.getData();
    }

    @Override
    public void addLast(E e) {
        // Done

        DoublyLinkedList.Node<E> newest = new DoublyLinkedList.Node<E>(e, null,null);
        DoublyLinkedList.Node<E> curr = head.getNext();

        if(curr == null) {
            head.next = newest;
            newest.next=tail;
        }
        else {
           /* while(curr.getNext() != tail) {
                curr = curr.getNext();
            }*/

            newest.prev = tail.prev;
            tail.prev.next = newest;
            tail.prev = newest;
            newest.next=tail;

            /*curr.next= newest;
            newest.prev=curr;
            newest.next=tail;*/
        }

        size++;
    }

    @Override
    public void addFirst(E e) {
        // Done
        DoublyLinkedList.Node<E> newNode = new DoublyLinkedList.Node<E>(e,null,null);

        if (head.getNext() == null){
            head.next=newNode;
            newNode.next=tail;
        }
        else {
            newNode.next=head.getNext();
            head.getNext().prev=newNode;
            head.next = newNode;
        }
        size++;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.getNext();
        while (curr != tail) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    //As I was unsure whether my head and nodes were to be dummy nodes or not the original toString does not work
    //I found a lot of different sources saying contradicting things
    //The data structure still works and I assumed it was ok.
    //I plan on going back and changing this but im not sure if I have time to
    //I changed the conditions to see if currr=!tail to curr.getNext()
    public String toString2() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr.getNext() != tail) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr.getNext() != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public E first2() {
        if (isEmpty()) {
            return null;
        }
        return head.getNext().getData();
    }

    public E removeFirst2() {
        // Done
        if(size==0){
            return null;
        }
        DoublyLinkedList.Node<E> temp = head.getNext();

        head.next = head.getNext().next;
        size--;
        return temp.getData();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}