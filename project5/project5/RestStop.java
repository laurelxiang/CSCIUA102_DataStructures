package project5;

import java.util.Comparator;

/**
 * RestStop represent a single rest stop as an object. It should be capable of 
 * storing the label 
 * of the rest stop along with a list of the supplies that a hiker can collect 
 * at this rest-stop and a list of obstacles that a hiker may encounter at this 
 * rest-stop.
 * @author laurel
 */

public class RestStop implements Comparable<RestStop> {

    //initializing storage representation
    private String label;
    private int food;
    private int raft;
    private int axe;
    private int fallentree;
    private int river;

    //initializing rest stop constructor
    RestStop() {
        
    }

    RestStop(String label, int food, int raft, int axe, int fallentree, int river) {
        this.label = label;
        this.food = food;
        this.raft = raft;
        this.axe = axe;
        this.fallentree = fallentree;
        this.river = river;
    }

    //implementing getters and setters
    public int getRiver() {
        return river;
    }

    public void setRiver(int river) {
        this.river = river;
    }

    public int getFallentree() {
        return fallentree;
    }

    public void setFallentree(int fallentree) {
        this.fallentree = fallentree;
    }

    public int getAxe() {
        return axe;
    }

    public void setAxe(int axe) {
        this.axe = axe;
    }

    public int getRaft() {
        return raft;
    }

    public void setRaft(int raft) {
        this.raft = raft;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    @Override
    public int compare(RestStop o1, RestStop o2) {
        return o1.getLabel().compareTo(o2.getLabel());
    }

    public int compareTo(RestStop rs) {
        return this.getLabel().compareTo(rs.getLabel());
    }
}
