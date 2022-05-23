package project3;

import java.util.Iterator;

/** 
 * this DoublyLinkedList class is a generic class that stores 
 * and implements a doubly linked list with no null elements
 * this class is used as storage for the StackOfSpaces and QueueOfSpaces 
 * implementation for the set of spaces algorithm
 * @author laurelxiang
*/

public class DoublyLinkedList<E> implements Iterable<E> {
    
    /** 
     * internal Node class 
    this class creates 2 nodes for the doublylinked list,
    prev and next
    these 2 nodes are initiated by the class and can be used
    to access a singular datapoint, data.
    */
    private class Node<E> { 
        public E data; 
        public Node<E> prev;
        public Node<E> next; 
        //create a node with a given data element
        //new node points to null
        public Node (E data) {
            if (data == null ) 
                throw new NullPointerException("list does not allow null elements");
            this.data = data;
            this.next = null; 
            this.prev = null;
        }
    }

    //initiating linkedlist variables
    private Node<E> head;
    private Node<E> tail; 
    private int size; 

    //initializing linkedlist variables with constructor
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0; 
    }

    /** 
     * add(E e) adds element at the end of linked list
     * @param e is element to be added
     * @return true if element has been modified as a result of this, 
     * @return false if no element has been added.
     * @throws ClassCastException if e is not an instance of the doublylinkedlist
    */
    public boolean add(E e) throws ClassCastException {
        if (e == null) {
            return false;
        }
        if (e instanceof DoublyLinkedList) {
            throw new ClassCastException("element class differs from linkedlist class. make sure type of element added matches type of linkedlist");
        }

        //create a new node 
        Node<E> n = new Node<E>(e);

        if (size == 0) {     //add to an empty list   
            head = n;
            tail = n;
        }         
        else {   //add at the end of the list 
            Node<E> tmp = tail;
            tail.next = n;
            tail = tail.next;
            tail.prev = tmp;
            tail.next = null;
        } 
        size++;
        return true;
    }

    /** 
    * Adds an element 'e' at position pos, shifts elements starting at pos by
    * one position to the right (higher position values). 
    * @param e is the element to be added to list
    * @param pos is the position at which element is to be added to list
    * @return true, if element has been added
    * @return false if element not added
    * @throws ClassCastException if element class differs from linkedlist class
    * @throws IndexOutOfBoundsException if pos < 0 or pos > size
    */ 
    public boolean add (E e, int pos) throws ClassCastException, IndexOutOfBoundsException {

        // error checking
        if (e == null) {
            return false;
        }
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException("invalid position given");
        }
        if (e instanceof DoublyLinkedList) {
            throw new ClassCastException("element class differs from linkedlist class. make sure type of element added matches type of linkedlist");
        }
        
        //create a new node and start the counter
        int counter = 0;
        Node<E> n = new Node<E>(e);
        
        if (size == 0) {     //add to an empty list   
            head = n;
            tail = n;
        }         
        else if (pos == 0) { //add at the start of the list
            Node<E> current = head; //current is c
            n.next = head;
            head = n;
            n.next = current;
            current.prev = n;
            n.prev = null;
        } else if (pos == size) {   //add at the end of the list 
            Node<E> tmp = tail;
            tail.next = n;
            tail = tail.next;
            tail.prev = tmp;
            tail.next = null;
        } else {             //add in the middle (general case)
            Node<E> current = head;
            while (counter < pos-1) {  
                current = current.next;
                counter++;
            }
            Node<E> tmp = current;
            n.next = current.next;
            current.next = n;
            n.prev = tmp;
         }
        
         size++;
         return true;
    }

    /** 
     * Remove a particular object o in the linked list
     * @param o is object to be removed from list
     * @return true if object is removed from linkedlist
     * @return false if object not removed from linkedlist
     * @throws ClassCastException if type of object o is incompatible with linkedlist
     * @throws NullPointerException if specified element to be removed is null
    */
    public boolean remove(Object o) throws ClassCastException, NullPointerException{
        Node<E> trav = head;
        //checking if same class
        if (o instanceof DoublyLinkedList) {
            throw new ClassCastException("element class differs from linkedlist class. make sure type of element added matches type of linkedlist");
        }
        // handling null
        if (o == null) {
            throw new NullPointerException("null can not be removed from doublylinkedlist. remove method failed");
            // return false;
        }

        int counter = 0; 
        // removing object
        for (trav = head; trav != null; trav = trav.next) {
            if (o.equals(trav.data)) {
                if (counter == 0) {
                    //start object - first object in linkedlist
                    head = head.next;
                    if (isEmpty()) {
                        tail = null;
                    }
                    else if (size == 1) { //if there is only 1 object in linkedlist
                        head = null;
                    }
                    else {
                        // head = head.next;
                        head.prev = null;
                    }
                }
                else if (counter == (size - 1)) {
                    //end object
                    trav.prev.next = trav.next;
                    trav.data = null;
                    trav = trav.prev = trav.next = null;
                    // reset tail if removing last node 
                    if (counter == size - 1){
                        tail = trav;
                    }
                }
                else {
                    //middle objects
                    trav.prev.next = trav.next;
                    trav.next.prev = trav.prev;
                    trav.data = null;
                    trav = trav.prev = trav.next = null;
                }
                size--;
                return true;
            }
            counter++;
        }
        return false;
    }   


    /** 
     * remove(int post) removes element at specified position pos of this linked list
     * @param pos is the position at which to remove from in list
     * @returns the element removed from list
     * @returns null if empty
     * @throws IndexOutOfBoundsException if pos < 0 || pos > size() - 1
    */
    public E remove (int pos) throws IndexOutOfBoundsException {
        //validating parameter pos
        if (pos < 0 || pos > size-1){ 
            throw new IndexOutOfBoundsException("invalid position given: ");
        }

        //remove from position 0 
        if(pos == 0){
            //start object - first object in linkedlist
            E tmp = head.data;
            head = head.next;
            if (isEmpty()) {
                tail = null;
            }
            else if (size == 1) { //if there is only 1 object in linkedlist
                // tmp = head.data;
                head = null;
            }
            else {
                // tmp = head.data;
                // head = head.next;
                head.prev = null;
            }

           size--;
           return tmp;
        }
        //remove from all other positions 
        Node<E> current = head;
        int stopPos = pos-1;
        int counter = 0; 
        //iterating to specified position
        while(counter < stopPos){
            current = current.next;
            counter++;
        }
        //changing node pointers
        E tmp = current.next.data;
        current.next = current.next.next;
        if (current.next != null) {
            current.next.prev = current;
        }

        // reset tail if removing last node 
        if (pos == size - 1){
            tail = current;
        }

        size--;
        return tmp; 
    }

    /** 
     * this method gets the # of elements, size, of the linkedlist
     * @returns # of elements, size, of the linkedlist
    */
    public int size() {
        return size;
    }

    /** 
     * clear() removes all elements from list, clearing it 
     * method does not return anything
    */
    public void clear() {
        Node<E> trav = head;
        while (trav != null) {
          Node<E> next = trav.next;
          trav.prev = trav.next = null;
          trav.data = null;
          trav = next;
        }
        head = tail = trav = null;
        size = 0;
    }

    /** 
     * Find the index of a particular element in the linked list
     * @param o object to find index of
     * @return the index of the first occurence of specified element
     * @return -1 if element not found
    */
    public int indexOf(Object o) {
        int index = 0;
        Node<E> trav = head;

        // searching for null object
        if (o == null) {
            for (; trav != null; trav = trav.next, index++) {
                if (trav.data == null) {
                    return index;
                }
            }
            // searching for non null object
        } else {
            for (; trav != null; trav = trav.next, index++) {
                if (o.equals(trav.data)) {
                    return index;
                }
            }
        }
        //nothing found return neg integer
        return -1;
    }

    /** 
     * contains(Object o) checks if a value is contained within the linked list
     * @param o is object to be checked if it is in list
     * @returns true if object is in list at least once
     * @returns false otherwise
    */
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /** 
     * equals (Object o) does comparing specified object with current list
     * comparison of instance, size, and corresponding pairs in the two lists
     * @param o is object to be compared to
     * @return true if they are equal as outlined in previous line
     * @return false if not equal (does not meet above conditions)
    */
    public boolean equals( Object o ) {
        if (this == o )  {
            return true;
        }
        if (o == null )  {
            return false;
        }
        if (!(o instanceof DoublyLinkedList<?>)) {  
            return false;
        }
        DoublyLinkedList<?> other = (DoublyLinkedList<?> ) o;
        if (this.size != other.size) {
            return false; 
        }
        //iterating through list to see if corresponding elements are equal
        Iterator<E> thisItr = this.iterator(); 
        Iterator<?> otherItr = other.iterator(); 
        while (thisItr.hasNext() && otherItr.hasNext() ) {
            if ( !(thisItr.next().equals(otherItr.next() ) ) ) {
                return false; 
            }
        }
        return true;  
    }

    /**
     * method will get the element at specified position in list
     * @param pos index at which to retrieve element from list
     * @return element at specified index if position exists
     * @return null if position doesn't exist
     */
    public E get(int pos) {
        if (pos < 0) {
            return null;
        }
        if (pos >= size) {
            return null;
        }
        //initiating counter
        int counter = 0;
        //iterating through list to get to proper index
        Iterator<E> thisItr = this.iterator(); 
        while (counter < pos) {
            thisItr.next();
            counter++;
        }
        return thisItr.next();
    }

    /**
     * isEmpty() checks if list contains no elements
     * @return true if list has no elements
     * @return false if list has elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * toString() converts the list into a single readable string object
     * @return readable string representation of list
     */
    public String toString() {
        //iterating through list
        //converting then adding list elements to string si
        Iterator<E> thisItr = this.iterator(); 
        String si = "[";
        while (thisItr.hasNext()) {
            E temp = thisItr.next();
            si = si.concat(String.valueOf(temp));
            if (thisItr.hasNext()) {
                si = si.concat(", ");
            }
        }
        si = si.concat("]");
        return si;
    }

    /**
     * reverseToString() converts the list into a single readable string object
     * this method was created for n running time when printing out stack and queue
     * @return readable string representation of list in reverse order
     */
    public String reverseToString() {
        //iterating through list
        //converting then adding list elements to string si
        Iterator<E> thisItr = this.iterator(); 
        String si = "";
        String s;
        int counter = 0;
        while (thisItr.hasNext()) {
            E temp = thisItr.next();
            s = String.valueOf(temp);
            if (counter != 0) {
                s = s.concat(", ");
            }
            si = s.concat(si);
            counter++;
        }
        si = "[".concat(si);
        si = si.concat("]");
        return si;
    }

    /**
     * implementation of Iterable<E> interface
     * inner class implementing an iterator for this list 
     */
    private class Itr implements Iterator<E> {
        private Node<E> current = head;
        public boolean hasNext() {
            if (current != null) {
                return true;
            }
            return false;
        }
        public E next() {
            E tmp = current.data;
            current = current.next;
            return tmp;
        }
    }
    
    /**
     * calling iterator class for use in linkedlist file
    */
    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }
}
