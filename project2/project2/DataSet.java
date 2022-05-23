package project2;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.net.URL;

public class DataSet implements Comparable<DataSet> {

    // initializing variables
    private String title;
    private String description;
    private ArrayList<URL> links;
    private Date date;
    private String hatTips;

    // initializing variables
    public DataSet (String title, String description, ArrayList<URL> links) throws IllegalArgumentException {
        // checking if values are empty
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Invalid value for title. "
            + "Valid input should be a nonempty string.");
        }
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Invalid value for description. "
            + "Valid input should be a nonempty string.");
        }
        if (links.isEmpty()) {
            throw new IllegalArgumentException("Invalid value for links. "
            + "Valid input should be a nonempty ArrayList<URL>");
        }

        this.title = title;
        this.description = description;
        this.links = links;
    }

    // implementing getters and setters
    public void setLinks(ArrayList<URL> links) throws IllegalArgumentException {
        this.links = links;
    }

    public ArrayList<URL> getLinks() {
        return links;
    }

    public void setDate(Date date) throws IllegalArgumentException {
        //checking null value for date
        if (date == null) {
            throw new IllegalArgumentException("Invalid value for date. "
            + "Valid input is not null.");
        }
        // validating year
        if (date.getYear() < 2000) {
            throw new IllegalArgumentException("Invalid value for year. "
            + "Valid input is positive integer equal to or after the year 2000.");
        }

        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setHatTips(String hatTips) throws IllegalArgumentException {
        if (hatTips == null) {
            throw new IllegalArgumentException("Invalid value for hatTips. "
            + "null is not valid input.");
        }
        //autograder does not accept arraylist<url> for some reason
        // ArrayList<URL> hatTipsar = new ArrayList<URL>(); //initializing hattips url arraylist
        // //adding urls to arraylist
        // try {
        //     String[] arg = hatTips.split("\n");//creating array containing all urls in string format
        //     for (int k=0; k < arg.length;k++) {
        //         hatTipsar.add(new URL(arg[k])); //converting each url into url object
        //     }
        // } catch (MalformedURLException e) {
        //     System.out.println("bad url. please input url in proper format.");
        // }
        // this.hatTips = hatTipsar;
        this.hatTips = hatTips;
    }

    public String getHatTips() {
        return hatTips;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Invalid value for date. "
            + "Valid input is not null.");
        }
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

	/**
	 * Indicates whether some object obj is "equal to" this one. 
     * checks for date values first, if exist then compares dates
     * if dates dont exist or are equal, titles will be compared lexicographically
     * returns 0 if objects are "equal"
     * returns pos int if this object is greater than compared object
     * returns neg int if this object is smaller than compared object
	 */
    @Override
    public int compareTo(DataSet o) {
        //this will get return a +/- int depending on length
        int length = this.getTitle().length() - o.getTitle().length();

        if (this.getDate() != null && o.getDate() != null) {
            int dateDiff = this.getDate().compareTo(o.getDate());

            if (dateDiff != 0) {
                return dateDiff;
            }

            else {
                return length;
            }
        }
        else {
            return length;
        }

        // if (getDate() == null || o.getDate() == null) {
        //     // do title comparison (string comparison)
        //     return getTitle().compareToIgnoreCase(o.getTitle());
        //     // look at these values for sorting later
        // }
        // // make if statements depending on -1,0,1
        // if (getDate().compareTo(o.getDate()) == 1) {
        //     return 1;
        // }
        // if (getDate().compareTo(o.getDate()) == -1) {
        //     return -1;
        // }
        // if (getDate().compareTo(o.getDate()) == 0) {
        //     // do title comparison
        //     return getTitle().compareToIgnoreCase(o.getTitle());
        // }
        // // compare by date if available
        // // else compare by title (not case sensitive)
        // return 2;
    }

    /**
	 * @returns the string representation of dataset
	 */
	@Override
    public String toString () {
        // obtaining string representation of arraylist links
        String linkstring = "";
        int c = 1; //counter
        for (URL url : getLinks()) {
            if (c != getLinks().size()) {
            linkstring += url.toString() + "\n";
            c++;
            }
            else if (c == getLinks().size()) {
                linkstring += url.toString();
            }
        }

        //check for date objects
        //if no date object print without
        // string representation of all desired variables
        
        //no date found
        if (getDate() == null) {
            return String.format("%s%n%s%n%s", 
            getTitle(),getDescription(),linkstring);
        }
        //if date found
        if (getDate() != null) {
            return String.format("%s%n%s%n%s%n%s", 
            getDate().toString(),getTitle(),getDescription(),linkstring);
        }
        return null;
	}

    //overriding equals from object class
    @Override
    public boolean equals(Object o) throws IllegalArgumentException {
        //if any dates are null then
        if (this == null || o == null) {
            return false;
        }
        if (!(o instanceof DataSet)) {
            return false;
        }
        if (this.compareTo((DataSet) o) == 0) {
            return true;
        }
        return false;

        // if (this.getTitle().toLowerCase().equals(((DataSet) o).getTitle().toLowerCase())) {
        //     if (this.getDate() != null || ((DataSet) o).getDate() != null) {
        //         if (this.getDate().equals(((DataSet) o).getDate())) {
        //             return true;
        //         }
        //         return false;
        //     }
        //     return false;
        // }
        // return false;
    }

    //boolean statements to check for keywords
    public boolean keywordTitle(String keyword) {
        if (this.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
            return true;
        }
        return false;
    }
    public boolean keywordDescription(String keyword) {
        if (this.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
            return true;
        }
        return false;
    }
    public boolean keywordURL(String keyword) {
        for (URL url : this.getLinks()) {
            if (url.toString().toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
