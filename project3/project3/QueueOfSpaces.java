package project3;

/** 
 * this class implements the possible locations interface using
a stack (LIFO) implementation with doublylinkedlist 
as storage
represents all possible locations in queue form for an algorithm
that explores a maze.
traditional method queue in a queue implementation is called add in this class, 
and dequeue is called remove
@author laurelxiang 
*/

public class QueueOfSpaces implements PossibleLocations {

    //declaring queue as a doublylinkedlist
    DoublyLinkedList<Location> queue;

    //initializing queue as a doublylinkedlist
    public QueueOfSpaces() {
        this.queue = new DoublyLinkedList<Location>();
    }

    /** 
     * add(Location s) adds Location s to the end of the queue
     * Location s will be added to the start of the doublylinkedlist
     * no return value
    */
    @Override
    public void add(Location s) throws NullPointerException {
        if (s == null) {
            throw new NullPointerException("can not add null location s to doublylinkedlist");
        }
        queue.add(s);
    }

    /**
     * Remove the next object from this collection.
     * Since this is a queue, the next object removed is the first list element
     * @return the next object, or null if set is empty
     */
    @Override
    public Location remove() {
        return queue.remove(0);
    }

    /** isEmpty() checks if the queue is empty
    @return true if there are no elements stored in the doublylinkedlist
    @return false if there are elements in doublylinkedlist
     */
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /** 
     * toString() converts the list into a single readable string object
     * order of list is order in which list elements are removed
     * @return readable string representation of queue
    */
    @Override
    public String toString() {
        return queue.reverseToString();
    }
}
