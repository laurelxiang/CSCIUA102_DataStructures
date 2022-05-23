package project2;
import java.util.ArrayList;
import java.net.URL;
import java.util.Collections;

public class DataSetList extends ArrayList<DataSet> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //empty constructor
    public DataSetList() {}

    //searching keyword by title
    public DataSetList getByTitle(String keyword) throws IllegalArgumentException {
        DataSetList results = new DataSetList(); 
        // checking if values are empty
        if (keyword.isEmpty() || keyword == null) {
            throw new IllegalArgumentException("Invalid value for keyword. "
            + "Valid input should be a nonempty string (search for something).");
        }

        //checking datasetlist for keyword
        for (DataSet ds : this) {
            if (ds.keywordTitle(keyword)) {
                results.add(ds);
            } 
        }
        
        //returning results with no elements
        if (results.isEmpty()) {
            return null;
        }
        //returning results with elements
        //sorting results
        Collections.sort(results);
        return results;
    }

    // searching keyword by description
    public DataSetList getByDescription ( String keyword ) throws IllegalArgumentException {
        DataSetList results = new DataSetList(); 
        // checking if values are empty
        if (keyword.isEmpty() || keyword == null) {
            throw new IllegalArgumentException("Invalid value for keyword. "
            + "Valid input should be a nonempty string (search for something).");
        }

        //checking datasetlist for keyword
        for (DataSet ds : getDataSetList()) {
            if (ds.keywordDescription(keyword)) {
                results.add(ds);
            } 
        }

        //returning results with no elements
        if (results.isEmpty()) {
            return null;
        }
        //returning results with elements
        //sorting results
        Collections.sort(results);
        return results;
    }

    // searching keyword by URL
    public DataSetList getByURL ( String keyword ) throws IllegalArgumentException {
        DataSetList results = new DataSetList(); 
        // checking if values are empty
        if (keyword.isEmpty() || keyword == null) {
            throw new IllegalArgumentException("Invalid value for keyword. "
            + "Valid input should be a nonempty string (search for something).");
        }

        //checking datasetlist for keyword
        for (DataSet ds : getDataSetList()) {
            if (ds.keywordURL(keyword)) {
                results.add(ds);
            }
        }

        //returning results with no elements
        if (results.isEmpty()) {
            return null;
        }
        //returning results with elements
        //sorting results
        Collections.sort(results);
        return results;
    }

    //getter
    public DataSetList getDataSetList() {
        return this;
    }

    //adding dataset obj to datasetlist using parent class arraylist
    public boolean add(DataSet ds) {
        return super.add(ds);
    }

    //checking is empty dataset obj to datasetlist using parent class arraylist
    public boolean isEmpty() {
        return super.isEmpty();
    }

    //clear is empty dataset obj to datasetlist using parent class arraylist
    public void clear() {
        super.clear();
    }



    // @Override sort method
    // //sorting method of results obtained from keyword search
    // public DataSetList sort(DataSetList results) {
    //     DataSetList sorted = new DataSetList();
    //     //iterating through results to place into new sorted array
    //     for (int i=0; i < results.size(); i++) {
    //         DataSet temp = results.get(i);
    //         //iterating through sorted to place temp into correct position
    //         //adding first element
    //         if (sorted.size() < 1) {
    //             // System.out.println("hi first");
    //             sorted.add(temp);
    //         }
    //         else if (sorted.size() == 1) {
    //             // System.out.println("hi first");
    //             int compare = temp.compareTo(sorted.get(0));
    //             if (compare < 0) {
    //                 sorted.add(0,temp);
    //                 // System.out.println("cpmare < 0");
    //             }
    //             if (compare >= 0) {
    //                 sorted.add(temp);
    //                 // System.out.println("cpmare >= 0");
    //             }
    //         }
    //         //adding other elements lexicographically
    //         else if (sorted.size() >= 2) {
    //             // System.out.println("hi");
    //             for (int j=0; j < sorted.size();j++) {
    //                 int compare = temp.compareTo(sorted.get(j));
    //                 // System.out.println(compare);
    //                 // if (j == (sorted.size() - 1) && compare > 0) {
    //                 //     // System.out.println("hi2c");
    //                 //     sorted.add(temp);
    //                 // }
    //                 if (compare > 0) {
    //                     if (j == (sorted.size() - 1)) {
    //                         sorted.add(j, temp);
    //                         break;
    //                     }
    //                     // sorted.add((j),temp); --> doesn't capture case if later elements are also smaller than temp
    //                     continue;
    //                 }
    //                 if (compare <= 0) {
    //                     // System.out.println("hi c< 0");
    //                     sorted.add(j, temp);
    //                     break;
    //                 }
    //             }
    //         }
    //     }
    //     //fixing last index to 2 index position error
    //     if (sorted.size() > 1) {
    //         DataSet delete = sorted.get(sorted.size() - 1);
    //         sorted.remove(sorted.size() - 1);
    //         sorted.add(1,delete);
    //     }
    //     return sorted;
    // }
}
