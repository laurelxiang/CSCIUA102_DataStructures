package project3;

public class Test {
    public static void main (String[] args) {

        //testing stack and queue implementations
        QueueOfSpaces queue = new QueueOfSpaces();
        StackOfSpaces stack = new StackOfSpaces();

        Location temp = new Location(0,0);
        Location temp1 = new Location(1,1);
        Location temp2 = new Location(2,2);

        stack.add(temp);
        stack.add(temp1);
        stack.add(temp2);
        System.out.print("stack: ");
        System.out.println(stack.toString());

        System.out.println(stack.stack.size());
        stack.remove();
        stack.remove();
        System.out.println(stack.isEmpty());
        stack.remove();
        System.out.println(stack.stack.size());
        System.out.print("stack1: ");
        System.out.println(stack.toString());

        queue.add(temp);
        queue.add(temp1);
        queue.add(temp2);
        System.out.print("queue: ");
        System.out.println(queue.toString());
        
        System.out.println(queue.queue.size());
        queue.remove();
        queue.remove();
        queue.remove();
        System.out.println(queue.queue.size());
        System.out.println(queue.isEmpty());
        System.out.print("queue1: ");
        System.out.println(queue.toString());


    }
}
