package project2;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class DataIsPlural {
    public static void main(String[] args) throws MalformedURLException, FileNotFoundException,IllegalArgumentException {
        //@JoannaKlukowska code from ColorConverter.java modified for the DataIsPlural class
        
        //verify that the command line argument exists 
		if (args.length == 0 ) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}

        //file search exception
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new FileNotFoundException("file not found. please make sure it is in the right place and correctly named.");
        }
        if (!file.canRead()){
			System.err.println("Error: the file " + file.getAbsolutePath()+
			" cannot be opened for reading.\n");
            System.exit(1);
        }

        //open the file for reading 
		Scanner sc = null; 
        
        //parse csv
        try {
			sc = new Scanner (file);
		} catch (FileNotFoundException e) {
			System.err.println("Error: the file " + file.getAbsolutePath()+
			" cannot be opened for reading.\n");
            System.exit(1);
		}
        
        // sc = new Scanner(new File("data_is_plural.csv"));
        CSV csv = new CSV(sc);
        DataSetList ds = new DataSetList();
        DataSet temp;

        //storing csv data in datasetlist
        for (int i = 0; i < csv.getNumOfRows(); i++ ) {
            ArrayList<String> thisrow = csv.getNextRow(); //this row contains whole row elements
            
            //converting string into url
            if (i > 0) {//not converting headers
                ArrayList<URL> url = new ArrayList<URL>(); //initializing links arraylist
                //adding urls to arraylist
                try {
                    String[] arg = thisrow.get(4).split("\n");//creating array containing all urls in string format
                    for (int k=0; k < arg.length;k++) {
                        url.add(new URL(arg[k])); //converting each url into url object
                    }
                } catch (MalformedURLException e) {
                    System.out.println("bad url. please input url in proper format.");
                }
            
                //storing title, description, and links into dataset constructor
                temp = new DataSet(thisrow.get(2),thisrow.get(3), url);
                //storing date
                ArrayList<Integer> d = new ArrayList<Integer>();
                d = parseDate(thisrow.get(0));
                Date date = new Date(d.get(0), d.get(1), d.get(2));
                temp.setDate(date);
                //storing hattips
                if (thisrow.size() > 5) {
                    temp.setHatTips(thisrow.get(5));
                }
                // storing dataset constructor into datasetlist
                ds.add(temp);
            }
        }

        welcome(); //welcome statement

        //interactive mode: 
		
		Scanner userInput  = new Scanner (System.in ); 
		String userValue = "";

        //initalizing datasetlists
        DataSetList d = new DataSetList();
        DataSetList d2 = new DataSetList();
        DataSetList temp2 = new DataSetList();
        DataSetList temp3 = new DataSetList();
        DataSetList temp4 = new DataSetList();
        // DataSetList chris = new DataSetList();
        //separating uservalues into strings to search by and keyword strings
        ArrayList<String> searchby = new ArrayList<String>();
        ArrayList<String> keyword = new ArrayList<String>();

        ArrayList<String> words = new ArrayList<String>();
        words.add("title");
        words.add("description");
        words.add("url");
        int m;
		
        outerloop: //for breaking out of for loop exception
		do {
			System.out.println("Enter query or \"quit\" to stop:" );
            //erasing datasets for next query
            d = new DataSetList();
            d2 = new DataSetList();
            temp2.clear();
            temp3.clear();
            temp4.clear();
			//get value of from the user 
			userValue = userInput.nextLine().toLowerCase();
            if (userValue == null) {
                System.err.println("This is not a valid query. Please try again.");
                continue;
            }
			if (!userValue.equalsIgnoreCase("quit")) { 
                String[] uv = userValue.split(" ");
                //maxing search queries to 2
                if (uv.length > 4 || uv.length < 2 || uv.length == 3) {
                    System.err.println("This is not a valid query. Please try again.");
                    continue;
                }
                //separating uservalues into strings to search by and keyword strings
                searchby = new ArrayList<String>();
                keyword = new ArrayList<String>();
                for (int l=0; l < uv.length; l++) {
                    if (l % 2 == 0) {
                        searchby.add(uv[l]);
                    }
                    else {
                        keyword.add(uv[l]);
                    }
                }

                //throwing error if searchby arraylist contains words other than title, description, url
                for (int n=0; n < searchby.size(); n++) {
                    if (!(searchby.get(n).equals(words.get(0)) || searchby.get(n).toString().equals(words.get(1)) || searchby.get(n).toString().equals(words.get(2)))) {
                        System.err.println("This is not a valid query. Please try again.");
                        continue outerloop; //will continue the do loop (ask for new input) but terminate this for loop as well
                    }
                }

                //initiating search query for 1 entry
                m = 0;
                if (m == 0) {
                    if (searchby.get(m).equals(words.get(0))) { //if searching by title
                        d = ds.getByTitle(keyword.get(m));
                    }
                    if (searchby.get(m).equals(words.get(1))) {//if searching by description
                        d = ds.getByDescription(keyword.get(m));
                    }
                    if (searchby.get(m).equals(words.get(2))) {//if searching by url
                        d = ds.getByURL(keyword.get(m));
                    }
                }
                m = 1;

                //initiating search query for 2 entry
                if (searchby.size() == 2) {
                    if (searchby.get(m).equals(words.get(0))) { //if searching by title
                        d2 = ds.getByTitle(keyword.get(m));
                    }
                    if (searchby.get(m).equals(words.get(1))) {//if searching by description
                        d2 = ds.getByDescription(keyword.get(m));
                    }
                    if (searchby.get(m).equals(words.get(2))) {//if searching by url
                        d2 = ds.getByURL(keyword.get(m));
                    }

                    //fix nullpointer in isempty conditions

                    //removing dataset that does not match both searches queries   
                    if (!(d == null) && !(d2 == null)) { //if both queries are not empty
                        for (DataSet chry : d) { //iterating through first query results
                            for (DataSet chri : d2) { //iterating through second query results
                                if (chry.equals(chri)) { //if first query results is equal to second query results
                                    temp2.add(chri); //then this equal result is added to array
                                }
                            }
                        }
                        //temp3 will contain datasetlist without duplicates
                        //after for loop, temp3 is same as temp2
                        for (DataSet temper : temp2) {
                            temp3.add(temper);
                        }
                        //duplicate fixer -- iterates through datasetlist d and erases duplicates
                        // Traverse through the first list 
                        for (DataSet element : temp3) { 
                            // If this element is not present in newList 
                            // then add it 
                            if (!temp4.contains(element)) { 
                                temp4.add(element); 
                            } 
                        } 
                        Collections.sort(temp4);
                    }
                }

                //1 query output
                if (searchby.size() == 1) {
                    //no matches
                    if (d == null) {
                        System.out.println("No matches found. Try again.");
                    }
                    else {
                        //printing out results and date
                        for (DataSet dataset : d) {
                            System.out.printf("%s%n%n-----%n", dataset.toString());
                        }
                    }
                }
                //2 query output
                if (searchby.size() == 2) {
                    //no matches
                    if (temp4 == null || d2 == null || d == null || temp4.isEmpty() || d2.isEmpty() || d.isEmpty()) {
                        System.out.println("No matches found. Try again.");
                    }
                    else {
                        //printing out results and date
                        for (DataSet dataset : temp4) {
                            System.out.printf("%s%n%n-----%n", dataset.toString());
                        }
                    }
                }
			}
			
		} while (!userValue.equalsIgnoreCase("quit"));     
		
		userInput.close();

    }

    //prints welcome message
    public static void welcome() {
        System.out.println("Welcome the Data Is Plural data explorer!\n");
        System.out.println("You can use the following queries to search through the data:");
        System.out.format("\t%s%n\t%s%n\t%s%n%s%n\t%s\t%s%n%n",
        "title KEYWORD",
        "description KEYWORD",
        "url KEYWORD",
        "You can combine up to two queries to narrow down the results, for example:",
        "title KEYWORD1",
        "url KEYWORD2"
        );
        System.out.println();
    }

    // this method converts csv date into a Date object
    public static ArrayList<Integer> parseDate(String str) {
        //splitting date by year, month, day
        String[] arrstr = str.split("\\.");

        //converting array into int arraylist
        ArrayList<Integer> arr = new ArrayList<Integer> ();
        for (int i=0;i<arrstr.length; i++) {
            arr.add(Integer.valueOf(arrstr[i]));
        }
        return arr;
    }
}
