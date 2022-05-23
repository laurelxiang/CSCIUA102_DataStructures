package project3;

/** 
 * this class implements the possible locations interface using
 * a stack (LIFO) implementation with doublylinkedlist as storage
 * represents all possible locations in stack form for an algorithm
that explores a maze.
traditional method push in a stack implementation is called add in this class, 
and pop is called remove
@author laurelxiang 
*/ 

public class StackOfSpaces implements PossibleLocations {

    //declaring stack as a doublylinkedlist
    DoublyLinkedList<Location> stack;

    //initializing stack as a doublylinkedlist
    public StackOfSpaces() {
        this.stack = new DoublyLinkedList<Location>();
    }

    /** 
     * add(Location s) adds Location s to the top of the stack
     * Location s will be added to the start of the doublylinkedlist
     * no return value
    */
    @Override
    public void add(Location s) throws NullPointerException {
        if (s == null) {
            throw new NullPointerException("can not add null location s to doublylinkedlist");
        }
        stack.add(s,0);
    }

    /** 
    remove() removes the element at the top of the stack
    the top of the stack refers to the element most recently added into stack
    the most recently added element will be the first element of the list
    @return the element removed, or null if empty
    */
    @Override
    public Location remove() {
        return stack.remove(0);
    }

    /** isEmpty() checks if the stack is empty
    @return true if there are no elements stored in the doublylinkedlist
    @return false if there are elements in doublylinkedlist
     */
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /** 
     * toString() converts the list into a single readable string object
     * order of list is order in which list elements are removed
     * @return readable string representation of stack
    */
    @Override
    public String toString() {
        return stack.reverseToString();
    }
    
}
