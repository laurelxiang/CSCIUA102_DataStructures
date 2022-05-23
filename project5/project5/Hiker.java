package project5;

/**
 * Hiker represents a hiker traveling down the mountain. 
 * Keeps track of all the supplies that the hiker has in their possession
 * and updates as it goes thru.
 * @author laurel
 */

public class Hiker {
    private int food;
    private int raft;
    private int axe;


    Hiker() {
        this.food = 0;
        this.raft = 0;
        this.axe = 0;
    }

    //add methods will add number of items to supply tracker
    public void addAxe(int add) {
        this.axe += add;
    }

    public void addRaft(int add) {
        this.raft += add;
    }

    public void addFood(int add) {
        this.food += add;
    }

    /**
     * removeAxe, removeRaft,removeFood methods will check if there is enough supply to pass obstacle
     * @return true if obstacle is successfully passed
     * @return false if obstacle can not be passed
     */

    public boolean removeAxe() {
        if (axe == 0) {
            return false;
        }
        if (axe > 0) {
            axe -= 1;
            return true;
        }
        return false;
    }

    public boolean removeRaft() {
        if (raft == 0) {
            return false;
        }
        if (raft > 0) {
            raft -= 1;
            return true;
        }
        return false;
    }

    public boolean removeFood() {
        if (food == 0) {
            return false;
        }
        if (food > 0) {
            food -= 1;
            return true;
        }
        return false;
    }


    //implementing getters and setters
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
}
