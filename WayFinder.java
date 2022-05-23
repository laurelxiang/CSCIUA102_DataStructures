package project4;

import java.util.ArrayList;

/**
 * WayFinder class is a program that takes in a numerical puzzle as its command line argument,
 * calculates and displays all possible solutions to the puzzle or determines that no such solutions exist.
 * This class uses recursion to solve the puzzle.
 * @author laurelxiang
 * @version 17-04-2021
 */

public class WayFinder {

    //declaring variables used in recursive solution
    private int[] sequence;
    private ArrayList<String> pathdirection;
    private ArrayList<Integer> pathlocation;
    private int numsoln;

    //initializing variables used in recursive solution
    WayFinder(int[] sequence, ArrayList<String> pathdirection, ArrayList<Integer> pathlocation, int numsoln) {
        this.sequence = sequence;
        this.pathdirection = pathdirection;
        this.pathlocation = pathlocation;
        this.numsoln = numsoln;
    }
    
    //running solution using command line argument
    public static void main(String[] args) {
        int[] sequence = arrayConversion(args);
        WayFinder wayfinder = new WayFinder(sequence, new ArrayList<String>(), new ArrayList<Integer>(), 0);
        wayfinder.solver(sequence, wayfinder.getPathDirection(), wayfinder.getPathLocation(), "R", 0);

        //print num of solutions with different message depending on # of solutions
        String print = "";
        if (wayfinder.getNumSoln() == 0) {
            print = "No way through this puzzle.";
        }
        if (wayfinder.getNumSoln() == 1) {
            print = String.format("There is %d way through the puzzle.\n", wayfinder.getNumSoln());
        }
        if (wayfinder.getNumSoln() > 1) {
            print = String.format("There are %d ways through the puzzle.\n", wayfinder.getNumSoln());
        }
        System.out.println(print);
    }

    /**
     * arrayConversion(String[] args) converts command line input into an array
     * @param args args is command line argument to be converted into an array
     * @returns integer array containing puzzle to solve
     */
    private static int[] arrayConversion(String[] args) {
        //initiating arraylist storage
        int[] storage = new int[args.length];

        //checking if command line argument is valid
        if (args.length == 0 || args.length == 1) {
            System.err.println("ERROR: Program expects two command arguments.");
            System.exit(1);
        }
        int counter = 0;
        //checking if all arguments are integers 
        for (String arg : args) {
            int temp = Integer.valueOf(arg);
            //checking if input is valid
            if (temp < 0) {
                System.err.println("ERROR: the puzzle values have to be positive integers.");
                System.exit(1);
            }
            if (temp > 99) {
                System.err.println("ERROR: Values out of range. Range is 0-99. ");
                System.exit(1);
            }
            //adding input to arraylist storage if integer
            storage[counter] = temp; 
            counter++;
        }
        //checking if last input is 0
        if (storage[storage.length - 1] != 0) {
            System.err.println("ERROR: the last value in the puzzle has to be zero.");
            System.exit(1);
        }

        return storage;
    }

    /**
     * solver(WayFinder puzzle, int location) solves the puzzle
     * by mapping out every possible path and storing it in soln
     * @params sequence sequence contains the maze to solve
     * @params pathdirection pathdirection is the arraylist that stores the left/right as solver travels through sequence
     * @params pathlocation pathlocation is the arraylist that stores the indexes as solver travels through sequence
     * @params direction is the current left/right direction that the solver has decided to go in
     * @params location is the current index that the solver has travelled to
     * @returns void, will print out solutions
     */
    @SuppressWarnings("unchecked")
    private void solver(int[] sequence, ArrayList<String> pathdirection, ArrayList<Integer> pathlocation, String direction, int location) throws ClassCastException {
        //solution not found - location is not in sequence indexes
        if (location >= sequence.length || location < 0) {
            return;
        } 

        //solution not found - infinite solution
        //checks if pathlocation contains 2 of the same index
        for (int i=0; i < pathlocation.size(); i++) {
            for (int j=i+1; j < pathlocation.size(); j++) {
                if (pathlocation.get(i).equals(pathlocation.get(j))) {
                    return;
                }
            }
        }

        //checking if current step solved puzzle
        if (location == sequence.length - 1) {
            //increasing number of solutions by 1
            setNumSoln(getNumSoln() + 1);
            //print solution (path)
            System.out.println(solnToString(sequence,pathdirection,pathlocation));
            return;
        }

        //next step - right direction
        if (location + sequence[location] < sequence.length) {
            //can go right
            //cloning current solution
            ArrayList<Integer> newpathlocation = (ArrayList<Integer>) pathlocation.clone();
            ArrayList<String> newpathdirection = (ArrayList<String>) pathdirection.clone();
            //adding current index and direction to cloned solution
            newpathlocation.add(location);
            newpathdirection.add("R");
            //recursive call
            solver(sequence, newpathdirection, newpathlocation, "R", location + sequence[location]);
        }

        //next step - left direction
        if (location - sequence[location] > 0) {
            //can go left
            //cloning current solutino
            ArrayList<Integer> newpathlocation = (ArrayList<Integer>) pathlocation.clone();
            ArrayList<String> newpathdirection = (ArrayList<String>) pathdirection.clone();
            //adding current index and direction to cloned solution
            newpathlocation.add(location);
            newpathdirection.add("L");
            //recursive call
            solver(sequence, newpathdirection, newpathlocation, "L", location - sequence[location]);
        }
    }

    /**
     * solnToString() converts the soln array into a readable string
     * @params sequence sequence is the puzzle with the arguments
     * @params pathdirection pathdirection is an arraylist containing 
     *          all the indexes for the solution to the puzzle
     * @params pathlocation pathlocation is an arraylist containing
     *          all the directions (left/right) for the solution to the puzzle
     * @return readable string according to project4 specs
     */
    private String solnToString(int[] sequence, ArrayList<String> pathdirection, ArrayList<Integer> pathlocation) {
        String output = "";
        String temp;
        //iterating through solution indexes
        for (int i=0; i < pathlocation.size(); i++) {
            temp = "[";
            //iterating through puzzle sequence
            for (int j=0; j < sequence.length; j++) {
                //if index is location of solution print special case
                if (pathlocation.get(i).equals(j)) {
                    //if initial index (first line printed out per solution)
                    if (i == 0) {
                        if (sequence[j] >= 10) {
                            temp = temp + String.format("%s%s,", sequence[j], pathdirection.get(i));
                        }
                        if (sequence[j] < 10) {
                            temp = temp + String.format(" %s%s,", sequence[j], pathdirection.get(i));
                        }
                    }
                    //if nonfinal index
                    if (i != 0) {
                        if (sequence[j] >= 10) {
                            temp = temp + String.format(" %s%s,", sequence[j], pathdirection.get(i));
                        }
                        if (sequence[j] < 10) {
                            temp = temp + String.format("  %s%s,", sequence[j], pathdirection.get(i));
                        }
                    }
                }
                //if first index of puzzle sequence
                else if (j == 0) {
                    if (sequence[j] >= 10) {
                        temp = temp + String.format(" %s ,", sequence[j]);
                    }
                    if (sequence[j] < 10) {
                        temp = temp + String.format(" %s ,", sequence[j]);
                    }
                }
                //if last index of puzzle sequence
                else if (j == sequence.length - 1) {
                    if (sequence[j] >= 10) {
                        temp = temp + String.format(" %s ", sequence[j]);
                        break;
                    }
                    if (sequence[j] < 10) {
                        temp = temp + String.format("  %s ", sequence[j]);
                        break;
                    }
                }
                //if non-solution index of puzzle sequence
                else {
                    if (sequence[j] >= 10) {
                        temp = temp + String.format(" %s ,", sequence[j]);
                    }
                    if (sequence[j] < 10) {
                        temp = temp + String.format("  %s ,", sequence[j]);
                    }
                }
            }
            temp = temp + "]\n";
            output = output + temp;
        }
        return output;
    }

        /**  
     * getter and setter for sequence,numsoln,pathdirection,pathlocation
    */
    // private void setSequence(int[] sequence) {
    //     this.sequence = sequence;
    // }
    // private int[] getSequence() {
    //     return sequence;
    // }
    private void setNumSoln(int numsoln) {
        this.numsoln = numsoln;
    }
    private int getNumSoln() {
        return numsoln;
    }
    private ArrayList<String> getPathDirection() {
        return pathdirection;
    }

    // private void setPathDirection(ArrayList<String> pathdirection) {
    //     this.pathdirection = pathdirection;
    // }

    private ArrayList<Integer> getPathLocation() {
        return pathlocation;
    }

    // private void setPathLocation(ArrayList<Integer> pathlocation) {
    //     this.pathlocation = pathlocation;
    // }
}
