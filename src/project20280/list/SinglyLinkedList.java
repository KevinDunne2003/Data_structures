package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;// head node of the list (or null if empty)
   

    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        // Done
        return size;
    }

    //@Override
    public boolean isEmpty() {
        //Done
        if(size() == 0) {
        	   return true;
        }
        else return false;
     
    }

    @Override
    public E get(int position) {
        // TODO
    	
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
        // TODO
    	
    	//newNode = new Node<E>(e,head);
    	
    	//Returns the node which we want to add the node after
    	//e=get(position);

        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Index: " + position + ", Size: " + size);
        }
        Node<E> newNode = new Node<>(e, null);
        if (position == 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node<E> current = head;
            for (int i = 1; i < position; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        size++;
    	
    }


    @Override
    public void addFirst(E e) {
    	Node<E> newNode = new Node<E>(e,null);
    	
    	if (head == null){
    		newNode.next=null;
    		head=newNode;
    		System.out.println("Test head");
    	}
    	else {
    		newNode.next=head;
    		head = newNode;
    		System.out.println("test");
    	}
        //head = new Node<E>(e,head);
       
    	 size++;
    }

    @Override
    public void addLast(E e) {
    	
    	Node<E> newest = new Node<E>(e, null); 
    	Node<E> curr = head;
        
    	if(curr == null) {
    		head = newest;
    	}
    	else {
	    	while(curr.getNext() != null) {
	    		curr = curr.getNext();
	    	}
	    curr.next= newest;
    	}
    	
    	size++;
    }

//NOT SURE IF THIS WORKS
    @Override
    public E remove(int position) {


    	//Create an instance of the Iterator type
    	//Iterator<E> iteration=iterator();
    	
    	E removedElement=null;
    	Node<E> curr2 = (Node<E>) head;
    	
    	if(position == 0) {
    		head = head.getNext();
    	}
        else if(position-1==size){
            for(int i=0;i<position;i++) {
                //E res = curr2.getElement();
                curr2 = curr2.next;
            }
            curr2.setNext(null);
            removedElement=curr2.getElement();

        }
    	else {
    		for(int i=1;i<position;i++) {
    			//E res = curr2.getElement();
    			 curr2 = curr2.next;
    		}

            //removedElement = curr2.getNext().getElement();
            //curr2.setNext(curr2.getNext().getNext());
            removedElement=curr2.getElement();
            curr2.setNext(curr2.getNext().getNext());
    		
    	}
        size --;
    	
    	return removedElement;


    		
       	//E prevPosition= get(position-1);
    	//E nextPosition = get(position+1);
    	//E currPosition = get(position);
       	
    	//prevPosition.next = nextPosition;
       // return null;
    }

    @Override
    public E removeFirst() {
        // TODO
        if(size==0){
            return null;
        }
        Node<E> temp = head;

        head = head.next;
        size--;
        return temp.getElement();
    }

    @Override
    public E removeLast() {
        // TODO
    	
    	//E removedElement;
    	Node<E> curr2 = (Node<E>) head;
    	
    	
    	while(curr2.getNext()!= null) {
    		curr2=curr2.getNext() ;
    		}

        size--;

        return curr2.element;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
       // ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
       // ll.remove(5);
        System.out.println(ll);

//        for(Integer i : ll) {
//            System.out.println(i);
//        }
        /*
        ll.addFirst(-100);
        ll.addLast(+100);
        System.out.println(ll);

        ll.removeFirst();
        ll.removeLast();
        System.out.println(ll);

        // Removes the item in the specified index
        ll.remove(2);
        System.out.println(ll);
        
         */
    }
}
