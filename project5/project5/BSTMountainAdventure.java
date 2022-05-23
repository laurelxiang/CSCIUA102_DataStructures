package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * BSTMountainAdventure does parsing and validating the command line arguments, 
 * reading and parsing the input file, producing any error messages, handling any exceptions 
 * thrown by other classes, and producing output.
 * @author laurel
 */

public class BSTMountainAdventure {

    public static void main(String[] args) throws FileNotFoundException {
        
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

        //initializing variables for input file reading
        String line = "";
        String[] lineparts = {};

        //initializing BST
        BSTMountain mountain = new BSTMountain();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                //reading line
                line = sc.nextLine();
                lineparts = line.split(" ");
                for (String part : lineparts) {
                    part.trim();
                }

                //creating RestStop object
                RestStop reststop = lineToRestStop(lineparts);
                mountain.add(reststop); //adding to BST mountain

            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        // BSTMountain<RestStop> mountain = new BSTMountain<>(new RestStop() );
    }

    /**
     * lineToRestStop takes in the input line from file
     * and reads the line in order to determine what is at the RestStop
     * @param lineparts a String array that represents each input line to be converted
     */
    private static RestStop lineToRestStop(String[] lineparts) {
        //setting variables to 0
        int food = 0;
        int raft = 0;
        int axe = 0;
        int fallentree = 0;
        int river = 0;

        //seting label
        String label = lineparts[0];

        //calculating supply amounts
        for (int i=1; i < lineparts.length; i++) {
            if (lineparts[i].equals("food")) {
                food++;
            }
            if (lineparts[i].equals("axe")) {
                axe++;
            }
            if (lineparts[i].equals("fallentree")) {
                fallentree++;
            }
            if (lineparts[i].equals("raft")) {
                raft++;
            }
            if (lineparts[i].equals("river")) {
                river++;
            }
        }

        return new RestStop(label, food, raft, axe, fallentree, river);
    }



}
